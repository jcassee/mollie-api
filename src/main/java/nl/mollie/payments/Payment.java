package nl.mollie.payments;

import static java.util.Collections.emptyMap;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nullable;

import org.pojomatic.annotations.AutoProperty;

import nl.mollie.PageableEntity;
import nl.mollie.util.ExtensibleValueObject;

@AutoProperty
public class Payment extends ExtensibleValueObject implements PageableEntity {
    public static final String OPEN = "open";
    public static final String CANCELLED = "cancelled";
    public static final String EXPIRED = "expired";
    public static final String FAILED = "failed";
    public static final String PENDING = "pending";
    public static final String PAID = "paid";
    public static final String PAIDOUT = "paidout";
    public static final String REFUNDED = "refunded";
    public static final String CHARGED_BACK = "charged_back";

    public static final String PAYMENT_URL = "paymentUrl";
    public static final String WEBHOOK_URL = "webhookUrl";
    public static final String REDIRECT_URL = "redirectUrl";
    public static final String SETTLEMENT = "settlement";
    public static final String REFUNDS = "refunds";

	String id;
    String mode;
    LocalDateTime createdDatetime;
    String status;
    LocalDateTime payedDatetime;
    LocalDateTime cancelledDatetime;
    LocalDateTime expiredDatetime;
    String expiryPeriod;
    BigDecimal amount;
    BigDecimal amountRefunded;
    BigDecimal amountRemaining;
    String description;
    String method;
    Map<String, Object> metadata;
    String locale;
    String profileId;
    String settlementId;
    String customerId;
    String recurringType;
    String mandateId;
    Map<String, String> links;
    Map<String, Object> details;

    public String getId() {
        return id;
    }

    public String getMode() {
        return mode;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public String getStatus() {
        return status;
    }

    @Nullable
    public LocalDateTime getPaidDatetime() {
        return payedDatetime;
    }

    @Nullable
    public LocalDateTime getCancelledDatetime() {
        return cancelledDatetime;
    }

    @Nullable
    public LocalDateTime getExpiredDatetime() {
        return expiredDatetime;
    }

    @Nullable
    public Duration getExpiryPeriod() {
        return expiryPeriod != null ? Duration.parse(expiryPeriod) : null;
    }

    @Nullable
    public String getExpiryPeriodAsString() {
        return expiryPeriod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Nullable
    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }

    @Nullable
    public BigDecimal getAmountRemaining() {
        return amountRemaining;
    }

    public String getDescription() {
        return description;
    }

    @Nullable
    public String getMethod() {
        return method;
    }

    public Map<String, Object> getMetadata() {
        return metadata != null ? metadata : emptyMap();
    }

    public Map<String, Object> getDetails() {
        return details != null ? details : emptyMap();
    }

    @Nullable
    public String getLocale() {
        return locale;
    }

    public String getProfileId() {
        return profileId;
    }

    @Nullable
    public String getSettlementId() {
        return settlementId;
    }

    @Nullable
    public String getCustomerId() {
        return customerId;
    }

    @Nullable
    public String getRecurringType() {
        return recurringType;
    }

    @Nullable
    public String getMandateId() {
        return mandateId;
    }

    public Map<String, String> getLinks() {
        return links != null ? links : emptyMap();
    }

    @Nullable
    public String getPaymentUrl() {
        return getLinks().get(PAYMENT_URL);
    }

    @Nullable
    public String getWebhookUrl() {
        return getLinks().get(WEBHOOK_URL);
    }

    @Nullable
    public String getRedirectUrl() {
        return getLinks().get(REDIRECT_URL);
    }

    @Nullable
    public String getSettlement() {
        return getLinks().get(SETTLEMENT);
    }

    @Nullable
    public String getRefunds() {
        return getLinks().get(REFUNDS);
    }
}
