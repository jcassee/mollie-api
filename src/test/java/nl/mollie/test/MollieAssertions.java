package nl.mollie.test;

import org.apache.http.HttpRequest;
import org.apache.http.entity.ContentType;
import org.assertj.core.api.Assertions;

public class MollieAssertions extends Assertions {

	public static ContentTypeAssert assertThat(ContentType actual) {
		return new ContentTypeAssert(actual);
	}

	public static RequestAssert assertThat(HttpRequest actual) {
		return new RequestAssert(actual);
	}
}
