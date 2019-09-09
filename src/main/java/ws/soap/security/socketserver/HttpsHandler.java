package ws.soap.security.socketserver;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpsHandler implements HttpHandler {

	private final IService iService;

	public HttpsHandler(IService forName) {
		iService = forName;
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException{
		System.out.println(arg0.getRequestMethod());
		if ("GET".equals(arg0.getRequestMethod())) {
			iService.get(arg0);
		}

	}

}
