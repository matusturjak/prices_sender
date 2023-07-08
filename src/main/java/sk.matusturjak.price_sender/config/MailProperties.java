package sk.matusturjak.price_sender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol;
    private Boolean propertiesMailSmtpAuth;
    private Boolean propertiesMailSmtpStarttlsEnable;
    private Boolean debug;
    private List<String> items;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Boolean getPropertiesMailSmtpAuth() {
        return propertiesMailSmtpAuth;
    }

    public void setPropertiesMailSmtpAuth(Boolean propertiesMailSmtpAuth) {
        this.propertiesMailSmtpAuth = propertiesMailSmtpAuth;
    }

    public Boolean getPropertiesMailSmtpStarttlsEnable() {
        return propertiesMailSmtpStarttlsEnable;
    }

    public void setPropertiesMailSmtpStarttlsEnable(Boolean propertiesMailSmtpStarttlsEnable) {
        this.propertiesMailSmtpStarttlsEnable = propertiesMailSmtpStarttlsEnable;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
