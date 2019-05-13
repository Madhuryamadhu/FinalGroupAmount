package com.Capgemini.ownProjs.AmountDeatils;

import java.util.Date;
import java.util.List;

public class AmountDeatilsBean {
    private String userMail;
    private String status;
    private String groupname;
    
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	private List<String> name;
	private List<String> amount;
	private List<String> reason;
	
	private String userId;
	private String messageHtml;
	private String namesComma;
	private String amountsComma;
	private String reasonsComma;
	private String perHeadAmount;
	private String totalPerson;
	private String totalAmount;
	
	private String pdfFullPath;
	private String pdfFileName;
	private String createdate;
	
	
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String date) {
		this.createdate = date;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getPdfFullPath() {
		return pdfFullPath;
	}
	public void setPdfFullPath(String pdfFullPath) {
		this.pdfFullPath = pdfFullPath;
	}
	public String getReasonsComma() {
		return reasonsComma;
	}
	public void setReasonsComma(String reasonsComma) {
		this.reasonsComma = reasonsComma;
	}
	public String getPerHeadAmount() {
		return perHeadAmount;
	}
	public void setPerHeadAmount(String perHeadAmount) {
		this.perHeadAmount = perHeadAmount;
	}
	public String getTotalPerson() {
		return totalPerson;
	}
	public void setTotalPerson(String totalPerson) {
		this.totalPerson = totalPerson;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getNamesComma() {
		return namesComma;
	}
	public void setNamesComma(String namesComma) {
		this.namesComma = namesComma;
	}
	public String getAmountsComma() {
		return amountsComma;
	}
	public void setAmountsComma(String amountsComma) {
		this.amountsComma = amountsComma;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMessageHtml() {
		return messageHtml;
	}
	public void setMessageHtml(String messageHtml) {
		this.messageHtml = messageHtml;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public List<String> getName() {
		return name;
	}
	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getAmount() {
		return amount;
	}
	public void setAmount(List<String> amount) {
		this.amount = amount;
	}

	public List<String> getReason() {
		return reason;
	}
	public void setReason(List<String> reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmountDeatilsBean [");
		if (userMail != null) {
			builder.append("userMail=");
			builder.append(userMail);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (groupname != null) {
			builder.append("groupname=");
			builder.append(groupname);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (amount != null) {
			builder.append("amount=");
			builder.append(amount);
			builder.append(", ");
		}
		if (reason != null) {
			builder.append("reason=");
			builder.append(reason);
			builder.append(", ");
		}
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
			builder.append(", ");
		}
		if (messageHtml != null) {
			builder.append("messageHtml=");
			builder.append(messageHtml);
			builder.append(", ");
		}
		if (namesComma != null) {
			builder.append("namesComma=");
			builder.append(namesComma);
			builder.append(", ");
		}
		if (amountsComma != null) {
			builder.append("amountsComma=");
			builder.append(amountsComma);
			builder.append(", ");
		}
		if (reasonsComma != null) {
			builder.append("reasonsComma=");
			builder.append(reasonsComma);
			builder.append(", ");
		}
		if (perHeadAmount != null) {
			builder.append("perHeadAmount=");
			builder.append(perHeadAmount);
			builder.append(", ");
		}
		if (totalPerson != null) {
			builder.append("totalPerson=");
			builder.append(totalPerson);
			builder.append(", ");
		}
		if (totalAmount != null) {
			builder.append("totalAmount=");
			builder.append(totalAmount);
			builder.append(", ");
		}
		if (pdfFullPath != null) {
			builder.append("pdfFullPath=");
			builder.append(pdfFullPath);
			builder.append(", ");
		}
		if (pdfFileName != null) {
			builder.append("pdfFileName=");
			builder.append(pdfFileName);
			builder.append(", ");
		}
		if (createdate != null) {
			builder.append("createdate=");
			builder.append(createdate);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
