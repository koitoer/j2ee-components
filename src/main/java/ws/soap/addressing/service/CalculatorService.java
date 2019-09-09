package ws.soap.addressing.service;

import javax.jws.WebService;

@WebService
public interface CalculatorService {

	public abstract int addition(int i, int o) throws AddNumbersException;
}
