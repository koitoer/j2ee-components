package ws.soap.server.service.tempConvert.client;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.BindingProvider;

public class TempConvertClient {

	private static final String endpoint = "https://localhost:8443/jax-ws/soap-webservice/tempConverterWS";

	static {
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){

			@Override
			public boolean verify(String name, SSLSession session){
				return true;
			}
		});
		try {
			TrustManager[] trustMgr = new TrustManager[] {
			        new X509TrustManager(){

				        @Override
				        public X509Certificate[] getAcceptedIssuers(){
					        return null;
				        }

				        @Override
				        public void checkClientTrusted(X509Certificate[] cs,
				                String t){}

				        @Override
				        public void checkServerTrusted(X509Certificate[] cs,
				                String t){}
			        }
			};
			SSLContext sslCtx = SSLContext.getInstance("TLS");
			sslCtx.init(null, trustMgr, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslCtx.getSocketFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// To generate the classes as wsimport dont work over ssl use
	// C:\Users\Koitoer\Documents\workspace-sts\WebSecurity\build\classes>wsgen
	// -cp com.koitoer.webservices.service.TempConvert -wsdl
	// Then
	// C:\Users\Koitoer\Documents\workspace-sts\WebSecurity\build\classes>wsimport
	// -keep -p com.koitoer.webservices.service.client TempConvertService.wsdl
	public static void main(String args[]){

		String uname = "koitoer";
		String passwd = "koitoer";

		TempConvertService service = new TempConvertService();
		TempConvert port = service.getTempConvertPort();
		BindingProvider prov = (BindingProvider) port;

		// In case you want to use Digest passwd
		// String passwdHash = RealmBase.Digest(passwd, "SHA", null);

		prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, uname);
		prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, passwd);

		System.out.println("f2c(-40.1) = " + port.f2C(-40.1f));
		System.out.println("c2f(+32) = " + port.c2F(+32f));
		System.out.println("f2c(+98.7) = " + port.f2C(+98.7f));
	}
}
