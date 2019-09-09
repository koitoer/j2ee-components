package ws.provider;

import javax.xml.ws.Endpoint;

/**
 * @author mauricio.mena
 * @since 18/09/2014
 *
 */
public class LaunchMyService {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final Endpoint endpoint = Endpoint.publish("http://localhost:9999/myService", new MyService());
		System.out.println(endpoint.getProperties());
		System.out.println("Published");
	}

}
