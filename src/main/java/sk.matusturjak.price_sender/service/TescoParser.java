package sk.matusturjak.price_sender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import sk.matusturjak.price_sender.config.TescoPromotionsProperties;
import sk.matusturjak.price_sender.config.TescoShopProperties;
import sk.matusturjak.price_sender.config.rabbitmq.RabbitConfig;
import sk.matusturjak.price_sender.config.rabbitmq.RoutingKey;
import sk.matusturjak.price_sender.dto.ItemDto;
import sk.matusturjak.price_sender.model.tesco.Department;
import sk.matusturjak.price_sender.model.tesco.SuperDepartment;
import sk.matusturjak.common_module.tesco.TescoItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Component
public class TescoParser {

    private final TescoPromotionsProperties tescoPromotionsProperties;
    private final TescoShopProperties tescoShopProperties;
    private final RabbitTemplate rabbitTemplate;

    public void getItemsWithClubcard() throws IOException {

        for (SuperDepartment superDepartment : tescoPromotionsProperties.getSuperDepartments()) {
            for (Department department : superDepartment.getDepartments()) {
                String url = tescoPromotionsProperties.getBaseUrl() +
                        "?sortBy=title-ascending&viewAll=sortBy%2Csuperdepartment=" +
                        superDepartment.getId() + "&department=" + department.getId() + "&count=1000";

                Connection connection = Jsoup.connect(url)
                        .timeout(30000)
                        .maxBodySize(0)
                        .userAgent("Opera");
                Document doc = connection.get();

                Elements ul = doc.getElementsByClass("product-list grid");

                if (Objects.isNull(ul) || Objects.isNull(ul.first()))
                    break;

                ul.first().children().forEach(children -> {
                    if (children.is("li")) {
                        Element divDetailWrapper = children.getElementsByClass("product-details--wrapper").first();

                        if (Objects.isNull(divDetailWrapper))
                            return;

                        Element picture = children.getElementsByClass("product-image__container").first();
                        String pictureURL = Objects.isNull(picture) ?
                                " " :
                                picture.getElementsByTag("img").first().attr("src");

                        String productNameText = divDetailWrapper.getElementsByTag("span").text();
                        String clubcardOfferText = divDetailWrapper.getElementsByClass("offer-text").text();
                        String validToText = divDetailWrapper.getElementsByClass("dates").text();
                        validToText = validToText.replace("\u00a0","");

                        String productName = StringUtils.substringBefore(productNameText, " Více z kategorie");

                        Date validTo = null;
                        double clubcardPrice = 0d;
                        double normalPrice = 0d;
                        ItemDto itemDto = null;
                        try {
                            SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
                            validTo = parser.parse(StringUtils.substringAfter(validToText, "do "));

                            clubcardPrice = Double.parseDouble(StringUtils.substringBetween(clubcardOfferText,"S Clubcard "," Kč"));
                            normalPrice = Double.parseDouble(StringUtils.substringBetween(clubcardOfferText,"běžná cena ", " Kč"));

                            itemDto = new ItemDto(productName, normalPrice, clubcardPrice, validTo, pictureURL, superDepartment.getName(), department.getName());
                        } catch (Exception e) {
                            log.error("TescoParser:getItemsWithClubcard ERRor message - {}", e.getMessage());
//                            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RoutingKey.TESCO_ITEM_STORE_ERROR.getName(), itemDto);
                            return;
                        }

                        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RoutingKey.TESCO_ITEM_STORE.getName(), itemDto);
                    }
                });
            }
        }
    }

    public void getItems() throws IOException {
        for (String superDepartment : tescoShopProperties.getSuperdepartments()) {
            log.info("TescoParser:getItems START of parsing super depratment - {}", superDepartment);
            for (int i = 1; i < 200; i++) {
                String url = tescoShopProperties.getBaseUrl() + superDepartment + "/all?count=48&page=" + i;

                Connection connection = Jsoup.connect(url)
                        .timeout(30000)
                        .maxBodySize(0)
                        .userAgent("Opera");

                Document doc = null;
                try {
                    doc = connection.get();
                } catch (HttpStatusException ex) {
                    break;
                }

                Elements ul = doc.getElementsByClass("product-list grid");

                if (Objects.isNull(ul) || Objects.isNull(ul.first()))
                    break;

                ul.first().children().forEach(children -> {
                    try {
                        if (children.is("li")) {
                            Element divDetailWrapper = children.getElementsByClass("product-details--wrapper").first();

                            if (Objects.isNull(divDetailWrapper))
                                return;

                            Element picture = children.getElementsByClass("product-image__container").first();
                            String pictureURL = Objects.isNull(picture) ?
                                    " " :
                                    picture.getElementsByTag("img").first().attr("src");

                            String productNameText = divDetailWrapper.getElementsByTag("span").text();
                            String productName = StringUtils.substringBefore(productNameText, " Více z kategorie");

                            Elements strong = divDetailWrapper.getElementsByTag("strong");
                            if (!strong.isEmpty() && strong.text().equals("Clubcard cena")) {
                                String clubcardOfferText = divDetailWrapper.getElementsByClass("offer-text").text();
                                String validToText = divDetailWrapper.getElementsByClass("dates").text();
                                validToText = validToText.replace("\u00a0","");

                                Date validTo = null;
                                double clubcardPrice = 0d;
                                double normalPrice = 0d;

                                SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
                                validTo = parser.parse(StringUtils.substringAfter(validToText, "do "));

                                clubcardPrice = Double.parseDouble(StringUtils.substringBetween(clubcardOfferText,"S Clubcard "," Kč"));
                                normalPrice = Double.parseDouble(StringUtils.substringBetween(clubcardOfferText,"běžná cena ", " Kč"));

                                TescoItem tescoItem = TescoItem.builder()
                                                .name(productName)
                                                .normalCost(normalPrice)
                                                .actualCost(clubcardPrice)
                                                .validTo(validTo)
                                                .pictureURL(pictureURL)
                                                .superDepartment(superDepartment)
                                                .department(null)
                                                .build();
                                log.info("{}", tescoItem);
//                                rabbitTemplate.convertAndSend("fanout.exchange", "items", tescoItem);
                            } else {
                                String priceText = divDetailWrapper.getElementsByClass("beans-price__text").text();
                                double price = Double.parseDouble(StringUtils.substringBefore(priceText, " Kč")
                                        .replace(",",".")
                                        .replace(" ","")
                                );

                                TescoItem tescoItem = TescoItem.builder()
                                                .name(productName)
                                                .normalCost(price)
                                                .actualCost(-1)
                                                .validTo(null)
                                                .pictureURL(pictureURL)
                                                .superDepartment(superDepartment)
                                                .department(null)
                                                .build();
                                log.info("{}", tescoItem);
//                                rabbitTemplate.convertAndSend("fanout.exchange", "items", tescoItem);
                            }
                        }
                    } catch (Exception ex) {
                        log.warn("item not send, exception - {}", ex.getMessage());
                    }
                });
            }
            log.info("TescoParser:getItems END of parsing super depratment - {}", superDepartment);
        }
    }
}
