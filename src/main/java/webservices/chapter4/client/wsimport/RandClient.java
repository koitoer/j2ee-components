package webservices.chapter4.client.wsimport;

import java.util.List;

public class RandClient {

	public static void main(String[] args){
		// setup
		RandServiceImplService service = new RandServiceImplService();
		RandService port = service.getRandServiceImplPort();

		// sample calls
		System.out.println(port.next1());
		System.out.println("Call to number 4 random");
		List<Integer> nums = port.nextN(4);
		for (Integer num : nums)
			System.out.println(num);
	}
}