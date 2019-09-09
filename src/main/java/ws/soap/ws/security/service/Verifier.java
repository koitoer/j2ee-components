package ws.soap.ws.security.service;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;

/**
 * JAAS CallbackHandler to be used by the XWSSProcessor for Key and other
 * Security information retrieval
 * */
public class Verifier implements CallbackHandler {

	private static final String _username = "koitoer";
	private static final String _password = "passkoitoer";

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException{

		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof PasswordValidationCallback) {
				PasswordValidationCallback cb =
				        (PasswordValidationCallback) callbacks[i];
				if (cb.getRequest() instanceof PasswordValidationCallback.PlainTextPasswordRequest)
					cb.setValidator(new PlainTextPasswordVerifier());
			}
			else
				throw new UnsupportedCallbackException(null, "Not needed");
		}

	}

	private class PlainTextPasswordVerifier implements PasswordValidationCallback.PasswordValidator {

		@Override
		public boolean validate(PasswordValidationCallback.Request req)
		        throws PasswordValidationCallback.PasswordValidationException{
			PasswordValidationCallback.PlainTextPasswordRequest plain_pwd =
			        (PasswordValidationCallback.PlainTextPasswordRequest) req;
			return _username.equals(plain_pwd.getUsername())
			        && _password.equals(plain_pwd.getPassword());
		}
	}
}
