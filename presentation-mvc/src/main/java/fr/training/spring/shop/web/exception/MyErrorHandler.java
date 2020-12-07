package fr.training.spring.shop.web.exception;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class MyErrorHandler extends DefaultResponseErrorHandler {
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(response.getBody(), writer, Charset.forName("UTF-8"));
		String theString = writer.toString();
		throw new TechnicalException(theString);
	}
}