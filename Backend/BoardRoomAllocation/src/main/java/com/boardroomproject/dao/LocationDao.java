package com.boardroomproject.dao;

import java.util.List;

import com.boardroomproject.model.Location;

public interface LocationDao {
	public List<Location> getAllLocations(); 
	public Location getLocationById(int lId); 
	public void addLocation(Location location); 
	public void deleteLocationById(int lId);
}
