package nl.mollie.payments;

import java.math.BigDecimal;

import org.junit.Test;

public class PaymentRequestTest {
	@Test
	public void test() throws Exception {
		PaymentRequest request = PaymentRequest.noMethod()
				.amount(new BigDecimal("200.00"))
				.description("Test payment")
				.redirectUrl("http://example.com");
		PaymentRequest request2 = PaymentRequest.ideal()
				.amount(new BigDecimal("200.00"))
				.description("Test payment")
				.redirectUrl("http://example.com")
				.issuer("ING")
				.parameter("test", "test123");
		PaymentRequest request3 = PaymentRequest.belfius()
				.amount(new BigDecimal("10.00"));
		PaymentRequest request4 = PaymentRequest.creditCard();
	}
}
