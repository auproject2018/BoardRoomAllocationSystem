package com.boardroomproject.dao;

import java.util.List;

import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.Room;

public interface RoomDao {
	public List<Room> getRoomByLocation(int lId); 
	public void createRoom(Room room); 
	public void deleteRoom(int rId); 
	public boolean getRoomAvaiblity(RequestRoom requestRoom);
}
