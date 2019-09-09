package ws.soap.echo;

import javax.xml.ws.Endpoint;

public class EchoPublisher {

	public static void main(String[] args){
		String url = "http://localhost:9999/echo";
		Endpoint endpoint = Endpoint.publish(url, new EchoServiceImpl());
		System.out.println("Endpoit is published " + url);
	}

}
