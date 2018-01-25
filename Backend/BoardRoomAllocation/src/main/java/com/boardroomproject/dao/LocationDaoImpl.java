package com.boardroomproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.boardroomproject.model.Location;

@Repository("LocationDAO")
public class LocationDaoImpl implements LocationDao{
	private JdbcTemplate jdbcTemplateObject;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplateObject) {  
	    this.jdbcTemplateObject = jdbcTemplateObject;  
	}  
	public List<Location> getAllLocations() {
		return jdbcTemplateObject.query("select * from location where isArchived = ?",new RowMapper<Location>(){  
	        public Location mapRow(ResultSet rs, int row) throws SQLException {  
	            Location l=new Location(); 
	            l.setlId(Integer.parseInt(rs.getString(1)));
	            l.setlName(rs.getString(2));
	            return l;  
	        }  
	    },"N");  
	}

	public Location getLocationById(int lId) {
		String getLocation = "select 1 from Location where lid = ? and isArchived = ?";
		return jdbcTemplateObject.queryForObject(getLocation, new Object[]{lId,"N"},new BeanPropertyRowMapper<Location>(Location.class));
	}

	public void addLocation(Location location) {
		String addLocation = "insert into Location(lname,isArchived) values (?, ?)";
	     jdbcTemplateObject.update( addLocation, location.getlName(), "N");
		
	}

	public void deleteLocationById(int lId) {
		String deleteLocation = "update Student set isArchived = ? where id = ?";
	    jdbcTemplateObject.update(deleteLocation, "Y", lId);
	}
}
