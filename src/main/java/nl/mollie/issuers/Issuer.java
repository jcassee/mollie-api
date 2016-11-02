package nl.mollie.issuers;

import java.math.BigDecimal;

import nl.mollie.PageableEntity;
import nl.mollie.util.ExtensibleValueObject;

public class Issuer extends ExtensibleValueObject implements PageableEntity {

    private String id;
    private String description;
    private MethodAmount amount;
    private MethodImage image;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public MethodAmount getAmount() {
        return amount;
    }

    public MethodImage getImage() {
        return image;
    }

    public class MethodAmount {

        private BigDecimal minimum;
        private BigDecimal maximum;

        public BigDecimal getMinimum() {
            return minimum;
        }

        public BigDecimal getMaximum() {
            return maximum;
        }
    }

    public class MethodImage extends ExtensibleValueObject {

        private String normal;
        private String bigger;

        public String getNormal() {
            return normal;
        }

        public String getBigger() {
            return bigger;
        }
    }

}
