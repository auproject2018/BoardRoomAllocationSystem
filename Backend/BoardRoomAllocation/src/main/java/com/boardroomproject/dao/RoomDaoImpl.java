package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.Room;


public class RoomDaoImpl implements RoomDao{
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	private static final String FORMAT = "dd/mm/yyyy";
	private static final Logger logger =
	        Logger.getLogger(RoomDaoImpl.class.getName());
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	}  
	@Override
	public List<Room> getRoomByLocation(int lId) {
		return jdbcTemplateObject.query("select * from room where lId = ? and isArchived = ?",new RowMapper<Room>(){  
	        @Override
			public Room mapRow(ResultSet rs, int row) throws SQLException {  
	            Room r=new Room(); 
	            r.setrId(Integer.parseInt(rs.getString(1)));
	            r.setrName(rs.getString(2));
	            
	            r.setIsArchived(rs.getString(3).charAt(0));

	            r.setlId(Integer.parseInt(rs.getString(4)));
	            return r;  
	        }  
	    },lId,"N"); 
	}

	@Override
	public void createRoom(Room room) {
		String addRoom = "insert into room(rName,lId,isArchived) values (?, ?, ?)";
	     jdbcTemplateObject.update( addRoom, room.getrName(), room.getlId(), "N");
	}

	@Override
	public void deleteRoom(int rId) {
		String deleteLocation = "update room set isArchived = ? where rId = ?";
	    jdbcTemplateObject.update(deleteLocation, "Y", rId);
	}

	@Override
	public boolean getRoomAvaiblity(RequestRoom requestRoom) {
		List<RequestRoom> r1 = jdbcTemplateObject.query("select * from requestroom where isArchived = ? and dateOfBooking = ?",new RowMapper<RequestRoom>(){  
	        @Override
			public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	        	RequestRoom r=new RequestRoom();
	            try {
					r.setDateOfBooking(new SimpleDateFormat().parse(rs.getString(5)));
					r.setStartTime(new SimpleDateFormat().parse(rs.getString(9)));
			        r.setEndTime(new SimpleDateFormat().parse(rs.getString(10)));
				} catch (ParseException e) {
					logger.log(Level.WARNING,"Error",e);
				}
	            return r;  
	        }  
	    },"N",requestRoom.getDateOfBooking()); 
		
		boolean booked = true;
		for(RequestRoom rr :  r1){
			if(booked 
			 || rr.getStartTime().compareTo(requestRoom.getStartTime())<0 && rr.getStartTime().compareTo(requestRoom.getEndTime())<0
			 || rr.getEndTime().compareTo(requestRoom.getStartTime())<0 && rr.getEndTime().compareTo(requestRoom.getEndTime())<0){
				booked = false;
			}
		}
		return booked;
	}
	@Override
	public Room getRoomById(int rId) {
		String getRoom = "select rName from room where rId = ?";
		return jdbcTemplateObject.queryForObject(getRoom,new Object[] {rId},new BeanPropertyRowMapper<Room>(Room.class));
	}
	
}
