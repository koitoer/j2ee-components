package ws.soap.ws.security.service;

import java.util.LinkedList;
import java.util.List;

import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;

public class RemoteServiceWSS {

	public static void main(String[] args){
		Endpoint endpoint = Endpoint.create(new RemoteService());
		Binding binding = endpoint.getBinding();
		List<Handler> handlerChain = new LinkedList<Handler>();
		handlerChain.add(new RemoteServiceHandler());
		binding.setHandlerChain(handlerChain);
		endpoint.publish("http://localhost:9999/serviceRPC");
		System.out.println("http://localhost:9999/serviceRPC");
	}
}
