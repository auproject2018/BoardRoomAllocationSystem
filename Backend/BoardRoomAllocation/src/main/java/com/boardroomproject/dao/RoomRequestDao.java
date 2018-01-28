package com.boardroomproject.dao;

import java.util.List;

import com.boardroomproject.model.RequestRoom;

public interface RoomRequestDao {
	public void createRequest(RequestRoom requestRoom); 
	public void acceptRequest(int requestId);
	public void rejectRequest(int requestId);
	public List<RequestRoom> getRequestByLocation(int lId);
	 public List<RequestRoom> getRequestByUser(int userId);
}
