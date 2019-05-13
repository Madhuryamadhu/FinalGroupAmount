package com.Capgemini.ownProjs.GroupDetails;

import java.util.HashMap;

public class GroupDetailsBean {

	private String status;
	private String userEmail;
	private String userId;
	private String groupName;
	private String groupMembers;
	private HashMap<String, String> dropDownMap;
	private String groupId;
	private String dropDownOptions;
	private String messageHtml;
	private String createDate;
	

	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public HashMap<String, String> getDropDownMap() {
		return dropDownMap;
	}
	public void setDropDownMap(HashMap<String, String> dropDownMap) {
		this.dropDownMap = dropDownMap;
	}
	public String getMessageHtml() {
		return messageHtml;
	}
	public void setMessageHtml(String messageHtml) {
		this.messageHtml = messageHtml;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDropDownOptions() {
		return dropDownOptions;
	}
	public void setDropDownOptions(String dropDownOptions) {
		this.dropDownOptions = dropDownOptions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(String groupMembers) {
		this.groupMembers = groupMembers;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupDetailsBean [");
		if (status != null) {
			builder.append("status=");
			builder.append(status);
			builder.append(", ");
		}
		if (userEmail != null) {
			builder.append("userEmail=");
			builder.append(userEmail);
			builder.append(", ");
		}
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
			builder.append(", ");
		}
		if (groupName != null) {
			builder.append("groupName=");
			builder.append(groupName);
			builder.append(", ");
		}
		if (groupMembers != null) {
			builder.append("groupMembers=");
			builder.append(groupMembers);
			builder.append(", ");
		}
		if (dropDownMap != null) {
			builder.append("dropDownMap=");
			builder.append(dropDownMap);
			builder.append(", ");
		}
		if (groupId != null) {
			builder.append("groupId=");
			builder.append(groupId);
			builder.append(", ");
		}
		if (dropDownOptions != null) {
			builder.append("dropDownOptions=");
			builder.append(dropDownOptions);
			builder.append(", ");
		}
		if (createDate != null) {
			builder.append("createDate=");
			builder.append(createDate);
			builder.append(", ");
		}
		if (messageHtml != null) {
			builder.append("messageHtml=");
			builder.append(messageHtml);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
}