package ss.udapi.sdk;

import ss.udapi.sdk.interfaces.Credentials;

public class CredentialsImpl implements Credentials{

	private String userName;
	private String password;
	
	public CredentialsImpl(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

}
