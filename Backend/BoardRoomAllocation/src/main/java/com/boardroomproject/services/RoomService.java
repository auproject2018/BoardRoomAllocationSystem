package com.boardroomproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.RoomDao;
import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.Room;

@Service
public class RoomService implements IRoomService {
	
	@Autowired
	RoomDao roomDao;

	@Override
	public List<Room> getRoomByLocation(int lId) {
		return roomDao.getRoomByLocation(lId);
	}

	@Override
	public void createRoom(Room room) {
		roomDao.createRoom(room);

	}

	@Override
	public void deleteRoom(int rId) {
		roomDao.deleteRoom(rId);

	}

	@Override
	public boolean getRoomAvaiblity(RequestRoom requestRoom) {
		return roomDao.getRoomAvaiblity(requestRoom);
	}

}