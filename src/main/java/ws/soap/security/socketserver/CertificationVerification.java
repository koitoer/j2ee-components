package ws.soap.security.socketserver;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CertificationVerification {

	public static void main(String[] args) throws IOException, Exception{
		URL url = new URL("https://gmail.com");

		System.out.println("Start SSL factory and trust manager");
		// In some scenarios is needed to have a trust manager
		SSLContext sslCtx = SSLContext.getInstance("TLS");
		TrustManager[] trustMgr = getTrustMgr();
		sslCtx.init(null, // Key manager
		        trustMgr, // Trust manager
		        new SecureRandom()); // Random number generator
		HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());

		System.out.println("Start real Connection");
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		connection.connect();
		System.out.println("Connection is established with code " + connection.getResponseCode());
		System.out.println(connection.getResponseMessage());
		System.out.println(connection.getCipherSuite());
		for (Certificate cert : connection.getServerCertificates()) {
			System.out.println("Certificate List");
			System.out.println("Cert type : " + cert.getType());
			System.out.println("Cert clas : " + cert.getClass());
			System.out.println("Cert pkey algorithm : " + cert.getPublicKey().getAlgorithm());
			System.out.println("Cert pkey format    : " + cert.getPublicKey().getFormat());
			System.out.println("Cert pkey           : " + cert.getPublicKey());
		}
	}

	private static TrustManager[] getTrustMgr(){
		TrustManager[] trustManager = new TrustManager[] {
		        new X509TrustManager(){

			        @Override
			        public void checkClientTrusted(X509Certificate[] chain, String authType)
			                throws CertificateException{
				        System.out.println("checkClientTrusted");

			        }

			        @Override
			        public void checkServerTrusted(X509Certificate[] chain, String authType)
			                throws CertificateException{
				        System.out.println("checkServerTrusted");
				        System.out.println("checkServerTrusted authType : " + authType);
				        System.out.println("checkServerTrusted chain : " + chain.length);
				        System.out.println("checkServerTrusted chain : " + chain[0].getSubjectDN());

			        }

			        @Override
			        public X509Certificate[] getAcceptedIssuers(){
				        System.out.println("getAcceptedIssuers");
				        return null;
			        }
		        }

		};
		return trustManager;
	}

}
