package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.model.User;


public class RoomRequestDaoImpl implements RoomRequestDao{
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	private static final String FORMAT = "dd/MM/yyyy";
	private static final Logger logger =
	        Logger.getLogger(RoomRequestDaoImpl.class.getName());
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	} 
	public void createRequest(RequestRoom requestRoom) {
		String createRequest = "insert into User (lId, rId, userId, dateOfBooking, purposeOfBooking, remarkByAdmin, approverAdminId, startTime, endTime, status, isArchived) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	     jdbcTemplateObject.update( createRequest, requestRoom.getlId(), requestRoom.getrId(), requestRoom.getUserId(), requestRoom.getDateOfBooking(), requestRoom.getPurposeOfBooking(), requestRoom.getRemarkByAdmin(), requestRoom.getApproverAdminId(), requestRoom.getStartTime(), requestRoom.getEndTime(), "PENDING","N");
	}

	public void acceptRequest(int requestId) {
		String accept = "update room set status = ? where id = ?";
	    jdbcTemplateObject.update(accept, "ACCEPTED", requestId);
	}

	public List<RequestRoom> getRequestByDateNLocation(int lId, Date date) {
		return jdbcTemplateObject.query("select * from user where lid = ? and date = ? isArchived = ?",new RowMapper<RequestRoom>(){  
	        public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	            RequestRoom r=new RequestRoom();    
	            r.setlId(Integer.parseInt(rs.getString(2)));  
	            r.setrId(Integer.parseInt(rs.getString(3)));  
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
	            return r;  
	        }  
	    },lId,date,"N");
	}

	public List<RequestRoom> getRequestByUser(User user) {
		return jdbcTemplateObject.query("select * from user where username = ? isArchived = ?",new RowMapper<RequestRoom>(){  
	        public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	            RequestRoom r=new RequestRoom();    
	            r.setlId(Integer.parseInt(rs.getString(2)));  
	            r.setrId(Integer.parseInt(rs.getString(3)));  
	            r.setUserId(Integer.parseInt(rs.getString(4)));
	            try {
					r.setDateOfBooking(new SimpleDateFormat(FORMAT).parse(rs.getString(5)));
					r.setStartTime(new SimpleDateFormat(FORMAT).parse(rs.getString(9)));
		            r.setEndTime(new SimpleDateFormat(FORMAT).parse(rs.getString(10)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	            r.setPurposeOfBooking(rs.getString(6));
	            r.setRemarkByAdmin(rs.getString(7));
	            r.setApproverAdminId(Integer.parseInt(rs.getString(8)));
	            return r;  
	        }  
	    },user.getUserName(),"N");
	}
	public void rejectRequest(String remarkByAdmin, String requestId) {
		String request = "update room set status = ? and remarkByAdmin = ? where id = ?";
	    jdbcTemplateObject.update(request, "REJECTED", remarkByAdmin,requestId);
	}
	@Override
	public void rejectRequest(String remarkByAdmin, int requestId) {
		// TODO Auto-generated method stub
		
	}
}
