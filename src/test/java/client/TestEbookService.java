package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class TestEbookService {

	@Test
	public void testPOST(){
		Client client = ClientBuilder.newClient();
		System.out.println("*** Create a new Ebook ***");
		String xml = "<ebook>"
		        + "<title>Bill</title>"
		        + "<author>Burke</author>"
		        + "<edition>Burke</edition>"
		        + "<pageNumber>Burke</pageNumber>"
		        + "</ebook>";
		Response response = client.target("http://localhost:9090/jax-rs/services/ebook")
		        .request().post(Entity.xml(xml));
		if (response.getStatus() != 201)
			throw new RuntimeException("Failed to create");
		String location = response.getLocation().toString();
		System.out.println("Location: " + location);
		response.close();

		System.out.println("*** GET Created Customer **");
		String customer = client.target(location).request().get(String.class);
		System.out.println(customer);
	}
}
