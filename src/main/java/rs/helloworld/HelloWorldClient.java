package rs.helloworld;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HelloWorldClient {

	public static void main(String[] args){
		Client c = Client.create();
		String u = new String("http://localhost:8080/jax-rs/rest/helloworld");
		WebResource webResource = c.resource(u);
		String response = webResource.get(String.class);
		System.out.println(response);

		// Calling POST
		webResource = c.resource(u + "/smooth");
		MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
		map.add("min-color", "100");
		map.add("max-color", "blue");
		ClientResponse response2 = webResource.queryParams(map).accept(MediaType.APPLICATION_JSON)
		        .get(ClientResponse.class);
		System.out.println("Other response " + response2.getStatus());

		// Calling GET with Param
		webResource = c.resource(u);
		response = webResource.path("request").path("Mauricio_Mena").get(String.class);
		response = webResource.path("/request/Mauricio_Mena").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("username/a9").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("date").path("11").path("22").path("22").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("query").queryParam("from", "1").queryParam("to", "2")
		        .queryParam("orderBy", "asc").queryParam("orderBy", "desc").get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("matrix").path("2012").queryParam("author", "myAuthor")
		        .get(String.class);
		System.out.println(response);

		webResource = c.resource(u);
		response = webResource.path("matrix").path("2012;author=myAuthor;country=myCountry")
		        .get(String.class);
		System.out.println(response);
	}
}
