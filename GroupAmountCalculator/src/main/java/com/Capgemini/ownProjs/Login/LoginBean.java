package com.Capgemini.ownProjs.Login;

public class LoginBean {

	private String loginmailstatus;
	private String loginmailandpasswordstatus;
	private String loginemail;
	private String loginpassword;


	public String getLoginmailstatus() {
		return loginmailstatus;
	}
	public void setLoginmailstatus(String loginmailstatus) {
		this.loginmailstatus = loginmailstatus;
	}
	public String getLoginmailandpasswordstatus() {
		return loginmailandpasswordstatus;
	}
	public void setLoginmailandpasswordstatus(String loginmailandpasswordstatus) {
		this.loginmailandpasswordstatus = loginmailandpasswordstatus;
	}
	public String getLoginemail() {
		return loginemail;
	}
	public void setLoginemail(String loginemail) {
		this.loginemail = loginemail;
	}
	public String getLoginpassword() {
		return loginpassword;
	}
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginBean [");
	
		if (loginmailstatus != null) {
			builder.append("loginmailstatus=");
			builder.append(loginmailstatus);
			builder.append(", ");
		}
		if (loginmailandpasswordstatus != null) {
			builder.append("loginmailandpasswordstatus=");
			builder.append(loginmailandpasswordstatus);
			builder.append(", ");
		}
		if (loginemail != null) {
			builder.append("loginemail=");
			builder.append(loginemail);
			builder.append(", ");
		}
		if (loginpassword != null) {
			builder.append("loginpassword=");
			builder.append(loginpassword);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
