package sk.matusturjak.price_sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "tesco.shop")
public class TescoShopProperties {

    private String baseUrl;

    private List<String> superdepartments;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<String> getSuperdepartments() {
        return superdepartments;
    }

    public void setSuperdepartments(List<String> superdepartments) {
        this.superdepartments = superdepartments;
    }
}
