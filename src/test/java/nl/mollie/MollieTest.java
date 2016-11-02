package nl.mollie;

import static org.assertj.core.api.Assertions.contentOf;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.localserver.LocalServerTestBase;
import org.apache.http.protocol.HttpRequestHandler;
import org.assertj.core.api.exception.RuntimeIOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import nl.mollie.test.MollieSoftAssertions;

public abstract class MollieTest extends LocalServerTestBase {
	public static final String API_KEY = "1234567890";

	protected MollieClient client;

	@Rule
	public MollieSoftAssertions softly = new MollieSoftAssertions();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		client = Mollie.client(API_KEY);
	}

	@After
	@Override
	public void shutDown() throws Exception {
		if (this.httpclient != null) {
			this.httpclient.close();
		}
		if (this.server != null) {
			this.server.shutdown(0L, TimeUnit.SECONDS);
		}
	}

	@Override
	public HttpHost start() throws Exception {
		HttpHost host = super.start();
		client.setEndpoint(host.toURI());
		return host;
	}

	public void registerHandler(String url, HttpRequestHandler handler) {
		serverBootstrap.registerHandler(url, handler);
	}

	/**
	 * Read a file into a string. The complete resource name will be {@code classpath '.' filename}. For example,
	 * calling {@code some.package.SomeTest.readResource("test.txt")} will read the file
	 * {@code some/package/SomeTest.test.txt}.
	 *
	 * @param relativeName the relative filename
	 *
	 * @return the contents of the file
	 *
	 * @throws RuntimeIOException if an I/O error occurs
	 */
	protected String contentOfResource(String relativeName) {
		String className = getClass().getName();
		String resourceName = className.replace('.', File.separatorChar) + "." + relativeName;
		URL url = getClass().getClassLoader().getResource(resourceName);
		if (url == null) throw new AssertionError("File not found: " + resourceName);
		return contentOf(url);
	}
}
