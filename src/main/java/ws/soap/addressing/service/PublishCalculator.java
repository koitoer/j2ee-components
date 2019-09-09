package ws.soap.addressing.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

import com.koitoer.jax.ws.soap.service.image.client.LoggingHandler;

public class PublishCalculator {

	public static void main(String[] args){
		String url = "http://localhost:9999/calculator";
		Endpoint endpoint = Endpoint.create(new CalculatorServiceImpl());
		List<Handler> myHandlers = new ArrayList<Handler>();
		myHandlers.add(new LoggingHandler());
		endpoint.getBinding().setHandlerChain(myHandlers);
		endpoint.publish(url);
		System.out.println(endpoint.getEndpointReference());
		System.out.println("Endpoit is published " + url);
	}
}
