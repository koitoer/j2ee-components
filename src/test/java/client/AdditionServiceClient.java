package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class AdditionServiceClient {

	@Test
	public void urlMethod() throws Exception{
		URL restURL = new URL("http://localhost:8080/jax-rs/rest/rs/add/5/6");
		InputStreamReader ins = new InputStreamReader(restURL.openStream());
		BufferedReader in = new BufferedReader(ins);
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
	}

	@Test
	public void urlConnectionMethod() throws Exception{
		URL restURL = new URL("http://localhost:8080/jax-rs/rest/rs/add/5/6");
		URLConnection connection = restURL.openConnection();
		connection.setDoInput(true);
		connection.setReadTimeout(10000);
		connection.connect();
		InputStreamReader ins = new InputStreamReader(connection.getInputStream());
		BufferedReader in = new BufferedReader(ins);
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
	}

	@Test
	public void httpUrlConnectionMethod() throws Exception{
		URL restURL = new URL("http://localhost:8080/jax-rs/rest/rs/add/5/6");
		HttpURLConnection connection = (HttpURLConnection) restURL.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.setReadTimeout(10000);
		connection.connect();
		InputStreamReader ins = new InputStreamReader(connection.getInputStream());
		BufferedReader in = new BufferedReader(ins);
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
	}

	@Test
	public void jaxrsClientMethod(){
		Client cl = Client.create();
		WebResource webResource = cl.resource("http://localhost:8080/jax-rs/rest/rs/add/5/6");
		String response = webResource.get(String.class);
		System.out.println(response);
	}

}
