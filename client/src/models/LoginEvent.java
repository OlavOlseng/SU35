package models;

public class LoginEvent {
	private boolean loginSuccess;
	
	public LoginEvent(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
	
	public boolean getLoginSuccess() {
		return this.loginSuccess;
	}
}
