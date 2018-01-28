package com.boardroomproject.services;

import java.util.List;

import com.boardroomproject.model.RequestRoom;

public interface IRequestRoomService {
	
	public void createRequest(RequestRoom requestRoom);
	public void acceptRequest(int requestId);
	public void rejectRequest(String remarkByAdmin, int requestId);
	public List<RequestRoom> getRequestByLocation(int lId);
	public List<RequestRoom> getRequestByUser(int userId);
}
