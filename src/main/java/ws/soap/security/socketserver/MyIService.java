package ws.soap.security.socketserver;

import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class MyIService implements IService {

	@Override
	public void get(HttpExchange arg0){
		try {
			arg0.sendResponseHeaders(200, 0); // 0 == as many bytes as there are
			OutputStream out = arg0.getResponseBody();
			out.write("GETResponse".getBytes());
			out.close();
		} catch (Exception e) {
			System.err.println("Error in get MyIservice");
		}
	}

}
