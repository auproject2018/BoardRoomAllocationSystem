package com.boardroomproject.model;

public class Location {
	
	private int lId;
	private String lName;
	private char isArchived;
	
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public char getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(char isArchived) {
		this.isArchived = isArchived;
	}

}
