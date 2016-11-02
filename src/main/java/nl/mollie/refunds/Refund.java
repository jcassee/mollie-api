package nl.mollie.refunds;

import java.time.ZonedDateTime;

import nl.mollie.PageableEntity;
import nl.mollie.payments.Payment;
import nl.mollie.util.ExtensibleValueObject;

public class Refund extends ExtensibleValueObject implements PageableEntity {

    private String id;
    private Payment payment;
    private Double amount;
    private String status;
    private ZonedDateTime refundedDatetime;

    public String getId() {
        return id;
    }

    public Payment getPayment() {
        return payment;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public ZonedDateTime getRefundedDatetime() {
        return refundedDatetime;
    }
}
