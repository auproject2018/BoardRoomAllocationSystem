package com.boardroomproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boardroomproject.model.Room;
import com.boardroomproject.services.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	 @RequestMapping(value = "/", method = RequestMethod.POST)
	    public void createRoom(@RequestBody Room room) {
	       roomService.createRoom(room);
	    }
	 
	 @RequestMapping(value = "/{rId}", method = RequestMethod.DELETE)
	    public ResponseEntity<Integer> deleteRoom(@PathVariable("rId") int rId) {
	       
	       roomService.deleteRoom(rId);
	        return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
	    }
	 
	 @RequestMapping(value = "/{lId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Room> >getRoomByLocation(@PathVariable("lId") int lId) {
	       List<Room> rooms = roomService.getRoomByLocation(lId);
	        if (rooms.isEmpty()) {
	            return new ResponseEntity<List<Room> >(HttpStatus.NOT_FOUND);
	        }
	       return new ResponseEntity<List<Room> >(rooms, HttpStatus.OK);
	        
	    }
}
