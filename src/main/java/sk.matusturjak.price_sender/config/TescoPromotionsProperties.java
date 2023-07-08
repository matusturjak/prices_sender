package sk.matusturjak.price_sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import sk.matusturjak.price_sender.model.tesco.SuperDepartment;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "tesco.promotions")
public class TescoPromotionsProperties {

    private String baseUrl;
    private List<SuperDepartment> superDepartments;

    public List<SuperDepartment> getSuperDepartments() {
        return superDepartments;
    }

    public void setSuperDepartments(List<SuperDepartment> superDepartments) {
        this.superDepartments = superDepartments;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
