/*
 * Copyright from Java Web Service Up and Running [Example 6.3]
 * */
package ws.soap.security.socketserver;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

public class HttpsPublisher {

	// KeyStore is created using this line:
	// keytool -genkey -keyalg RSA -keystore test.keystore

	private static final String uri = "/test";
	private static final int defaultPort = 3443;
	private static final int backlog = 12;
	private static final String keystore = "src/main/resources/truststore/test.keystore";
	private static final String keystorePassword = "pass123";
	private static final String serviceClassName = "com.koitoer.jax.ws.soap.security.socketserver.MyIService";

	public static void main(String arg[]){
		// URL on service is > https://localhost:3443/test

		HttpsServer server = new HttpsPublisher().getHttpsServer();
		server.createContext(uri);
		System.out.println("Server listening on port " + defaultPort);
		server.start();
	}

	private HttpsServer getHttpsServer(){
		HttpsServer server = null;
		try {
			InetSocketAddress inet = new InetSocketAddress(defaultPort);
			server = HttpsServer.create(inet, backlog);
			SSLContext sslCtx = SSLContext.getInstance("TLS");

			char[] password = keystorePassword.toCharArray();
			KeyStore ks = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(keystore);
			ks.load(fis, password);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, password);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ks);

			sslCtx.init(kmf.getKeyManagers(), // We use real key Manager
			        tmf.getTrustManagers(), // We use real trust Manager
			        new SecureRandom());

			// Create SSL engine and configure HTTPS to use it.
			final SSLEngine eng = sslCtx.createSSLEngine();
			server.setHttpsConfigurator(new HttpsConfigurator(sslCtx){

				@Override
				public void configure(HttpsParameters parms){
					parms.setCipherSuites(eng.getEnabledCipherSuites());
					parms.setProtocols(eng.getEnabledProtocols());
				}
			});
			server.setExecutor(null); // Default single thread

			IService iService = (IService) Class.forName(serviceClassName).newInstance();
			server.createContext(uri, new HttpsHandler(iService));

		} catch (Exception e) {
			System.err.println("Error on server");
			throw new RuntimeException(e);
		}
		return server;
	}
}
