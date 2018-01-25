package com.boardroomproject.dao;

import java.util.Date;
import java.util.List;

import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.User;

public interface RoomRequestDao {
	public void createRequest(RequestRoom requestRoom); 
	public void acceptRequest(int requestId);
	public void rejectRequest(String remarkByAdmin, int requestId);
	public List<RequestRoom> getRequestByDateNLocation(int lId, Date date);
	 public List<RequestRoom> getRequestByUser(User user);
}
