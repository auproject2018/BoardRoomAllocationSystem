package com.boardroomproject.services;

import java.util.List;

import com.boardroomproject.model.Location;

public interface ILocationService {

	public List<Location> getAllLocations();
	public Location getLocationById(int lId);
	public void addLocation(Location location);
	public void deleteLocation(int lId);
}
