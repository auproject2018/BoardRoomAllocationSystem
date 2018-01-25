package com.boardroomproject.model;

import java.util.Date;

public class RequestRoom {
	
	private int requestId;
	private int lId;
	private int rId;
	private int userId;
	private Date dateOfBooking;
	private String purposeOfBooking;
	private String remarkByAdmin;
	private int approverAdminId;
	private Date startTime;
	private Date endTime;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public int getApproverAdminId() {
		return approverAdminId;
	}
	public void setApproverAdminId(int approverAdminId) {
		this.approverAdminId = approverAdminId;
	}
	public String getRemarkByAdmin() {
		return remarkByAdmin;
	}
	public void setRemarkByAdmin(String remarkByAdmin) {
		this.remarkByAdmin = remarkByAdmin;
	}
	public String getPurposeOfBooking() {
		return purposeOfBooking;
	}
	public void setPurposeOfBooking(String purposeOfBooking) {
		this.purposeOfBooking = purposeOfBooking;
	} 

}
