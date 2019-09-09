package ws.soap.security.socketserver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class HttpsPublisherClient {

	private static final String endpoint = "https://localhost:3443/test";
	private static final String keystore = "src/main/resources/truststore/test.keystore";
	private static final String keystorePassword = "pass123";

	public static void main(String arg[]){
		new HttpsPublisherClient().connect();
	}

	private void connect(){
		try {
			SSLContext sslCtx = SSLContext.getInstance("TLS");
			char[] password = keystorePassword.toCharArray();
			KeyStore ks = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(keystore);
			ks.load(fis, password);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ks);

			sslCtx.init(null, // not needed, not challenged
			        tmf.getTrustManagers(),
			        new SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
			URL url = new URL(endpoint);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setHostnameVerifier(new HostnameVerifier(){

				@Override
				public boolean verify(String arg0, SSLSession arg1){
					if ("localhost".equals(arg0)) {
						return true;
					}
					return false;
				}
			});

			// //////////////////////////////////////////// GET REQUEST
			conn.setRequestMethod("GET");
			conn.connect();
			byte[] buffer = new byte[512];
			InputStream in = conn.getInputStream();
			in.read(buffer);
			System.out.println(new String(buffer));

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
