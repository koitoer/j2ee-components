package webservices.chapter5.base64attach.server;

import javax.xml.ws.Endpoint;

import com.koitoer.webservices.chapter5.base64attach.server.SkiImageService;

public class SkiImagePublisherBase64 {

	public static void main(String[] args){
		System.out.println("URL: http://localhost:9876/ski");
		Endpoint.publish("http://localhost:9876/ski", new SkiImageService());
	}
}