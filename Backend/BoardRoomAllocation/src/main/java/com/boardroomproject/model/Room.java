package com.boardroomproject.model;

public class Room {

	private int rId;
	private String rName;
	private int lId;
	private char isArchived;
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public char getIsArchived() {
		return isArchived;
	}
	public void setIsArchived(char isArchived) {
		this.isArchived = isArchived;
	}
	
}
