package com.boardroomproject.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.RoomRequestDao;
import com.boardroomproject.model.RequestRoom;

@Service
public class RequestRoomService implements IRequestRoomService {

	@Autowired
	RoomRequestDao roomRequestDao;

	@Override
	public void createRequest(RequestRoom requestRoom) {
		roomRequestDao.createRequest(requestRoom);
	}

	@Override
	public void acceptRequest(int requestId) {
		roomRequestDao.acceptRequest(requestId);
	}

	@Override
	public void rejectRequest(String remarkByAdmin, int requestId) {
		roomRequestDao.rejectRequest(remarkByAdmin, requestId);
	}

	@Override
	public List<RequestRoom> getRequestByDateNLocation(int lId, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestRoom> getRequestByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestRoom> getRequestByStatusNLocation(int lId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}