package ws.soap.echo.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.koitoer.jax.ws.soap.echo.handler.LogMessageHandler;

public class EchoClientHandler {

	public static void main(String[] args){
		EchoServiceImplService echoService = new EchoServiceImplService();
		HandlerResolver resolver = new HandlerResolver(){

			@Override
			public List<Handler> getHandlerChain(PortInfo portInfo){
				List<Handler> handlers = new ArrayList<Handler>();
				handlers.add(new LogMessageHandler());
				return handlers;
			}
		};
		echoService.setHandlerResolver(resolver);
		EchoService port = echoService.getEchoServiceImplPort();

		System.out.println(port.echo("Koitoer echo "));
		System.out.println("Can not continue until I get the response");
		System.out.println(port.echo("Koitoer echo "));

		System.out.println(port.echo("Koitoer echo "));
		System.out.println("Can not continue until I get the response");
		System.out.println(port.echo("Koitoer echo "));

	}

}
