package ss.udapi.sdk;

import java.net.URL;

import ss.udapi.sdk.interfaces.Credentials;
import ss.udapi.sdk.interfaces.Session;


public class SessionFactory {
	
	public static Session createSession(URL rootURL, Credentials credentials)  {
		return new SessionImpl(rootURL, credentials);
	}
	
}
