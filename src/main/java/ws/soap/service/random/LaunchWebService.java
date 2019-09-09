package ws.soap.service.random;

import javax.xml.ws.Endpoint;

public class LaunchWebService {

	public static void main(String[] args){
		final String url = "http://localhost:8888/rs";
		System.out.println("Publishing RandService at endpoint " + url);
		Endpoint.publish(url, new RandServiceImpl());
	}

}
