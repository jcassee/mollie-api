package nl.mollie.payments;

import static java.util.Collections.singletonMap;
import static nl.mollie.test.MollieAssertions.assertThat;
import static nl.mollie.test.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.junit.Test;

import nl.mollie.MollieTest;
import nl.mollie.test.MollieAssertions;

public class PaymentModuleTest extends MollieTest {

	@Test
	public void createPayment() throws Exception {
		BigDecimal amount = new BigDecimal("10.00");
		String description = "My first payment";
		String redirectUrl = "https://webshop.example.org/order/12345/";
		Map<String, Object> metadata = singletonMap("order_id", "12345");

		PaymentRequest paymentRequest = PaymentRequest.noMethod()
				.amount(amount)
				.description(description)
				.redirectUrl(redirectUrl)
				.metadata(metadata);

		registerHandler("/payments", (request, response, context) -> {
			MollieAssertions.assertThat(request).method().isEqualTo("POST");
			MollieAssertions.assertThat(request).contentType().isMimetype("application/json");
			MollieAssertions.assertThat(request).contentAsJson().isEqualTo(contentOfResource("createPaymentRequest.json"));
			response.setEntity(new StringEntity(contentOfResource("createPaymentResponse.json")));
		});
		start();

		Payment payment = client.payments().create(paymentRequest);
		softly.assertThat(payment)
				.hasAmount(amount)
				.hasDescription(description)
				.hasRedirectUrl(redirectUrl)
				.hasMetadata(metadata);
	}

	@Test
	public void getPayment() throws Exception {

		registerHandler("/payments/tr_7UhSN1zuXS", (request, response, context) -> {
			MollieAssertions.assertThat(request).method().isEqualTo("GET");
			response.setEntity(new StringEntity(contentOfResource("getPaymentResponse.json")));
		});
		start();

		Payment payment = client.payments().get("tr_7UhSN1zuXS");
		softly.assertThat(payment)
				.hasAmount(new BigDecimal("10.00"))
				.hasDescription("My first payment")
				.hasRedirectUrl("https://webshop.example.org/order/12345/")
				.hasMetadata(singletonMap("order_id", "12345"));
	}
}
