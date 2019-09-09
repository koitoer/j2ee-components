package webservices.chapter4.client.wsimport.async;

import java.util.List;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

public class RandClientAsync {

	public static void main(String[] args){
		RandServiceImplService service = new RandServiceImplService();
		RandServiceImpl port = service.getRandServiceImplPort();
		port.nextNAsync(4, new MyHandler());
		try {
			Thread.sleep(5000); // in production, do something useful!
		} catch (Exception e) {}
		System.out.println("\nmain is exiting...");
	}

	static class MyHandler implements AsyncHandler<NextNResponse> {

		@Override
		public void handleResponse(Response<NextNResponse> future){
			try {
				NextNResponse response = future.get();
				List<Integer> nums = response.getReturn();
				for (Integer num : nums)
					System.out.println(num);
			} catch (Exception e) {
				System.err.println(e);
			}
		}

	}
}