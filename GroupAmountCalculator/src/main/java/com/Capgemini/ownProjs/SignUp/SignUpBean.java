package com.Capgemini.ownProjs.SignUp;

public class SignUpBean {
    private String status;
	private String email;
	private String name;
	private String password;
	 private String emailstatus;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getEmailstatus() {
		return emailstatus;
	}
	public void setEmailstatus(String emailstatus) {
		this.emailstatus = emailstatus;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignUpBean [");
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (emailstatus != null) {
			builder.append("emailstatus=");
			builder.append(emailstatus);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
