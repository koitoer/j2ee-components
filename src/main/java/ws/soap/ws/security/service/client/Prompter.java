package ws.soap.ws.security.service.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import com.sun.xml.wss.impl.callback.PasswordCallback;
import com.sun.xml.wss.impl.callback.UsernameCallback;

public class Prompter implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException{
		try {
			for (int i = 0; i < callbacks.length; i++) {
				if (callbacks[i] instanceof UsernameCallback) {
					UsernameCallback cb = (UsernameCallback) callbacks[i];
					String username = "koitoer";
					if (username != null)
						cb.setUsername(username);
				}
				else if (callbacks[i] instanceof PasswordCallback) {
					PasswordCallback cb = (PasswordCallback) callbacks[i];
					String password = "passkoitoer";
					if (password != null)
						cb.setPassword(password);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
