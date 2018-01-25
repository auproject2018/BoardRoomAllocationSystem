package com.boardroomproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.boardroomproject.dao.LocationDao;
import com.boardroomproject.model.Location;

@Service
public class LocationService implements ILocationService {
	
	@Autowired
	LocationDao locationDao;
	
	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

	public Location getLocationById(int lId) {
		return locationDao.getLocationById(lId);
	}

	public void addLocation(Location location) {
		locationDao.addLocation(location);
	}

	public void deleteLocation(int lId) {
		locationDao.deleteLocationById(lId);
	}

}