package com.boardroomproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.LocationDao;
import com.boardroomproject.model.Location;

@Service
public class LocationService implements ILocationService {
	
	@Autowired
	LocationDao locationDao;
	
	@Override
	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

	@Override
	public Location getLocationById(int lId) {
		return locationDao.getLocationById(lId);
	}

	@Override
	public void addLocation(Location location) {
		locationDao.addLocation(location);
	}

	@Override
	public void deleteLocation(int lId) {
		locationDao.deleteLocationById(lId);
	}

}