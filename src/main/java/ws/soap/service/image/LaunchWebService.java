package ws.soap.service.image;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

public class LaunchWebService {

	public static void main(String[] args){
		final String url = "http://localhost:9999/imageService";
		System.out.println("Publishing ImageService at endpoint " + url);
		Endpoint end = Endpoint.create(new ImageServiceImpl());
		((SOAPBinding) end.getBinding()).setMTOMEnabled(true);
		end.publish(url);
	}

}
