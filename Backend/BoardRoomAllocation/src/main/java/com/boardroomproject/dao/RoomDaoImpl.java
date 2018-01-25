package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.Room;

@Repository("RoomDao")
public class RoomDaoImpl implements RoomDao{
	private JdbcTemplate jdbcTemplateObject;
	private static final String FORMAT = "dd/MM/yyyy";
	private static final Logger logger =
	        Logger.getLogger(RoomDaoImpl.class.getName());
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	}  
	public List<Room> getRoomByLocation(int lId) {
		return jdbcTemplateObject.query("select * from room where lid = ? and isArchived = ?",new RowMapper<Room>(){  
	        public Room mapRow(ResultSet rs, int row) throws SQLException {  
	            Room r=new Room(); 
	            r.setrId(Integer.parseInt(rs.getString(1)));
	            r.setrName(rs.getString(2));
	            r.setlId(Integer.parseInt(rs.getString(3)));
	            return r;  
	        }  
	    },lId,"N"); 
	}

	public void createRoom(Room room) {
		String addRoom = "insert into room(rname,lid,isArchived) values (?, ?, ?)";
	     jdbcTemplateObject.update( addRoom, room.getrName(), room.getlId(), "N");
	}

	public void deleteRoom(int rId) {
		String deleteLocation = "update room set isArchived = ? where id = ?";
	    jdbcTemplateObject.update(deleteLocation, "Y", rId);
	}

	public boolean getRoomAvaiblity(RequestRoom requestRoom) {
		List<RequestRoom> r1 = jdbcTemplateObject.query("select * from roomrequest where isArchived = ? and dateofbooking = ?",new RowMapper<RequestRoom>(){  
	        public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	            RequestRoom r=new RequestRoom(); 
	            r.setrId(Integer.parseInt(rs.getString(3)));
	            r.setlId(Integer.parseInt(rs.getString(2)));
	        	r.setUserId(Integer.parseInt(rs.getString(4)));
	        	try {
					r.setDateOfBooking(new SimpleDateFormat(FORMAT).parse(rs.getString(5)));
					r.setStartTime(new SimpleDateFormat(FORMAT).parse(rs.getString(9)));
		        	r.setEndTime(new SimpleDateFormat(FORMAT).parse(rs.getString(10)));
				} catch (ParseException e) {
					logger.log(Level.WARNING,"Error",e);
				}
	        	r.setPurposeOfBooking(rs.getString(6));
	        	r.setRemarkByAdmin(rs.getString(7));
	        	r.setApproverAdminId(Integer.parseInt(rs.getString(8)));
	        	r.setStatus(rs.getString(11));
	            return r;  
	        }  
	    },"N",requestRoom.getDateOfBooking()); 
		
		boolean booked = false;
		for(RequestRoom rr :  r1){
			if(!booked 
			 || rr.getStartTime().compareTo(requestRoom.getStartTime())<0 && rr.getStartTime().compareTo(requestRoom.getEndTime())<0
			 || rr.getEndTime().compareTo(requestRoom.getStartTime())<0 && rr.getEndTime().compareTo(requestRoom.getEndTime())<0){
				booked = true;
			}
		}
		return booked;
	}
	
}
