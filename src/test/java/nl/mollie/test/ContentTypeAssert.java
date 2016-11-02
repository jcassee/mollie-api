package nl.mollie.test;

import java.util.Objects;

import org.apache.http.entity.ContentType;
import org.assertj.core.api.AbstractAssert;

public class ContentTypeAssert extends AbstractAssert<ContentTypeAssert, ContentType> {

	public ContentTypeAssert(ContentType actual) {
		super(actual, ContentTypeAssert.class);
	}

	public void isMimetype(String mimeType) {
		String actualMimeType = actual.getMimeType();
		if (!Objects.equals(actualMimeType, mimeType)) {
			failWithMessage("Expected content type to be <%s> but was <%s>", mimeType, actualMimeType);
		}
	}
}
