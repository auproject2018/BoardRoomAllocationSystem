package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.boardroomproject.model.Location;


public class LocationDaoImpl implements LocationDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	}  
	@Override
	public List<Location> getAllLocations() {
		return jdbcTemplateObject.query("select * from location where isArchived = ?",new RowMapper<Location>(){  
	        @Override
			public Location mapRow(ResultSet rs, int row) throws SQLException {  
	            Location l=new Location(); 
	            l.setlId(Integer.parseInt(rs.getString(1)));
	            l.setlName(rs.getString(2));
	            return l;  
	        }  
	    },"N");  
	}

	@Override
	public Location getLocationById(int lId) {
		String getLocation = "select 1 from Location where lId = ? and isArchived = ?";
		return jdbcTemplateObject.queryForObject(getLocation, new Object[]{lId,"N"},new BeanPropertyRowMapper<Location>(Location.class));
	}

	@Override
	public void addLocation(Location location) {
		String addLocation = "insert into Location(lName,isArchived) values (?, ?)";
	     jdbcTemplateObject.update( addLocation, location.getlName(), "N");
		
	}

	@Override
	public void deleteLocationById(int lId) {
		String deleteLocation = "update location set isArchived = ? where lId = ?";
	    jdbcTemplateObject.update(deleteLocation, "Y", lId);
	}
}
