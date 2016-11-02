package nl.mollie.test;

import org.apache.http.HttpRequest;
import org.apache.http.entity.ContentType;

public class MollieSoftAssertions extends JUnitSoftAssertions {

	public ContentTypeAssert assertThat(ContentType actual) {
		return proxy(ContentTypeAssert.class, ContentType.class, actual);
	}

	public RequestAssert assertThat(HttpRequest actual) {
		return proxy(RequestAssert.class, HttpRequest.class, actual);
	}
}
