package nl.stil4m.mollie.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class CreatePayment {

    private final Double amount;
    private final String description;
    private final String redirectUrl;
    private final String method;
    private final Map<String, Object> metadata;
    private final Map<String, Object> extraProperties;

    public CreatePayment(String method, Double amount, String description, String redirectUrl, Map<String, Object> metadata) {
        this.method = method;
        this.amount = amount;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.metadata = metadata;
        this.extraProperties = new HashMap<>();
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    @JsonAnyGetter
    public Map<String, Object> getExtraProperties() {
        return extraProperties;
    }

    public CreatePayment setProperty(String name, Object value) {
        extraProperties.put(name, value);
        return this;
    }
}
