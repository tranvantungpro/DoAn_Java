package Moudle;

import java.io.Serializable;

public class DangNhap implements Serializable
{
	private String UserName;
	private String Password;
	private String Server;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getServer() {
		return Server;
	}
	public void setServer(String server) {
		Server = server;
	}
}
