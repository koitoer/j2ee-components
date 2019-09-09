package ws.soap.echo.client;

import java.util.concurrent.Executor;

public class EchoClient {

	public static void main(String[] args){
		EchoServiceImplService echoService = new EchoServiceImplService();
		EchoService port = echoService.getEchoServiceImplPort();
		System.out.println(port.echo("Koitoer echo "));
		System.out.println("Can not continue until I get the response");
		System.out.println(port.echo("Koitoer echo "));

		Executor executor = new Executor(){

			@Override
			public void execute(Runnable command){
				System.out.println("Executor called");

			}
		};
		echoService.setExecutor(executor);

		System.out.println(port.echo("Koitoer echo "));
		System.out.println("Can not continue until I get the response");
		System.out.println(port.echo("Koitoer echo "));

	}

}
