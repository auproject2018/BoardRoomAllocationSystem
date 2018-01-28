package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.boardroomproject.model.RequestRoom;


public class RoomRequestDaoImpl implements RoomRequestDao{
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	private static final String FORMAT = "yyyy-mm-dd";
	private static final Logger logger =
	        Logger.getLogger(RoomRequestDaoImpl.class.getName());
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	} 
	@Override
	public void createRequest(RequestRoom requestRoom) {
		String createRequest = "insert into RequestRoom (lId, rId, userId, dateOfBooking, purposeOfBooking, remarkByAdmin, approverAdminId, startTime, endTime, status, isArchived) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	     jdbcTemplateObject.update( createRequest, requestRoom.getlId(), requestRoom.getrId(), requestRoom.getUserId(), requestRoom.getDateOfBooking(), requestRoom.getPurposeOfBooking(), requestRoom.getRemarkByAdmin(), requestRoom.getApproverAdminId(), requestRoom.getStartTime(), requestRoom.getEndTime(), "PENDING","N");
	}

	@Override
	public void acceptRequest(int requestId) {
		String accept = "update RequestRoom set status = ? where requestId = ?";
	    jdbcTemplateObject.update(accept, "ACCEPTED", requestId);
	}
	@Override
	public void rejectRequest(int requestId) {
		String request = "update RequestRoom set status = ? where requestId = ?";
	    jdbcTemplateObject.update(request, "REJECTED",requestId);
	}

	@Override
	public List<RequestRoom> getRequestByLocation(int lId) {
		return jdbcTemplateObject.query("select * from RequestRoom where lId = ? and isArchived = ? order by dateOfBooking Desc",new RowMapper<RequestRoom>(){  
	        @Override
			public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	            RequestRoom r=new RequestRoom();
	            r.setrId(Integer.parseInt(rs.getString(3)));
	            r.setlId(Integer.parseInt(rs.getString(2)));  
	           
	            r.setUserId(Integer.parseInt(rs.getString(4)));
	            r.setRequestId(Integer.parseInt(rs.getString(1)));
	            try {
					r.setDateOfBooking(new SimpleDateFormat(FORMAT).parse(rs.getString(5)));
					r.setStartTime(new SimpleDateFormat("hh:mm:ss").parse(rs.getString(9)));
			        r.setEndTime(new SimpleDateFormat("hh:mm:ss").parse(rs.getString(10)));
				} catch (Exception e) {
					logger.log(Level.WARNING,"Error",e);
				}
	            r.setPurposeOfBooking(rs.getString(7));
	            r.setRemarkByAdmin(rs.getString(6));
	            r.setApproverAdminId(Integer.parseInt(rs.getString(8)));
	            r.setStatus(rs.getString(11));
	            return r;  
	        }  
	    },lId,"N");
	}

	@Override
	public List<RequestRoom> getRequestByUser(int userId) {
		return jdbcTemplateObject.query("select * from RequestRoom where userId = ? and isArchived = ? ",new RowMapper<RequestRoom>(){  
	        @Override
			public RequestRoom mapRow(ResultSet rs, int row) throws SQLException {  
	        	RequestRoom r=new RequestRoom();
	            r.setrId(Integer.parseInt(rs.getString(1)));
	            r.setlId(Integer.parseInt(rs.getString(2)));  
	            r.setUserId(Integer.parseInt(rs.getString(4)));
	            try {
					r.setDateOfBooking(new SimpleDateFormat(FORMAT).parse(rs.getString(5)));
					r.setStartTime(new SimpleDateFormat("hh:mm:ss").parse(rs.getString(9)));
			        r.setEndTime(new SimpleDateFormat("hh:mm:ss").parse(rs.getString(10)));
				} catch (ParseException e) {
					logger.log(Level.WARNING,"Error",e);
				}
	            r.setPurposeOfBooking(rs.getString(7));
	            r.setRemarkByAdmin(rs.getString(6));
	            r.setApproverAdminId(Integer.parseInt(rs.getString(8)));
	            r.setStatus(rs.getString(11));
	            return r;   
	        }  
	    },userId,"N");
	}
	
}
