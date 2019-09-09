package rs.helloworld;

import javax.swing.*;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class FormClient {

	public static void main(String[] args){
		Client c = Client.create();
		String u = new String("http://localhost:8080/jax-rs/rest/form");
		WebResource webResource = c.resource(u);

		webResource = c.resource(u);
		String response = webResource.path("add").queryParam("name", "MyName")
		        .type(MediaType.APPLICATION_FORM_URLENCODED)
		        .post(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("get").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("getHeaders").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		byte[] responseBytes = webResource.path("getTxtFile").accept(MediaType.TEXT_PLAIN_TYPE)
		        .get(byte[].class);
		System.out.println(new String(responseBytes));

		responseBytes = webResource.path("getImageFile").accept("image/png")
		        .get(byte[].class);

		System.out.println(responseBytes.length);

		JFrame frame = new JFrame("Server Image received");
		frame.setSize(300, 300);
		JLabel label = new JLabel(new ImageIcon(responseBytes));
		frame.add(label);
		frame.setVisible(true);

	}
}
