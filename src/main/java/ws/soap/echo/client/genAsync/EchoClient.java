package ws.soap.echo.client.genAsync;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

public class EchoClient {

	public static void main(String[] args) throws InterruptedException{
		EchoServiceImplService echoService = new EchoServiceImplService();
		EchoService port = echoService.getEchoServiceImplPort();

		// Before call the service create the handler
		AsyncHandler<EchoResponse> handler = new AsyncHandler<EchoResponse>(){

			@Override
			public void handleResponse(Response<EchoResponse> res){
				try {
					System.out.println(res.get().getReturn() + " > "
					        + Thread.currentThread().getId());
				} catch (Exception e) {
					System.err.println("Something goes wrong with the handler");
				}
			}
		};

		System.out.println("Echo 1");
		port.echoAsync("Koitoer echo ", handler);
		System.out.println("Echo 2");
		port.echoAsync("Koitoer echo ", handler);
		System.out.println("Echo 3");
		port.echoAsync("Koitoer echo ", handler);
		System.out.println("Echo 4");
		port.echoAsync("Koitoer echo ", handler);

		System.out.println("Main thread is waiting " + Thread.currentThread());
		Thread.sleep(10000);
		System.out.println("Main thread is ending " + Thread.currentThread());

	}
}
