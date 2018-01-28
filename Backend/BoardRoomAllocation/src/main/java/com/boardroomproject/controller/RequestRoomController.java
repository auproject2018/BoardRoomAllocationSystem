package com.boardroomproject.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.services.RequestRoomService;
import com.boardroomproject.services.RoomService;

@RestController
@RequestMapping("/request")
public class RequestRoomController {

	@Autowired
	RequestRoomService requestRoomService;
	
	@Autowired
	RoomService roomService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Void> createRequest(@RequestBody RequestRoom requestRoom) {
		
		if(roomService.getRoomAvaiblity(requestRoom)){
			requestRoomService.createRequest(requestRoom);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		else{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/accept/{requestId}", method = RequestMethod.PUT)
	    public void acceptRequest(@PathVariable("requestId") int requestId) {
		 requestRoomService.acceptRequest(requestId);
	 }
	
	@RequestMapping(value = "/reject/{requestId}", method = RequestMethod.PUT)
    public void rejectRequest(@PathVariable("requestId") int requestId) {
	 requestRoomService.rejectRequest(requestId);
	}
	
	 @RequestMapping(value = "/{lId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<RequestRoom>getRequestByLocation(@PathVariable("lId") int lId) {
	       return requestRoomService.getRequestByLocation(lId);
	        
	    }
	 
	 @RequestMapping(value = "user/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<RequestRoom>getRequestByUserId(@PathVariable("userId") int userId) {
	       return requestRoomService.getRequestByUser(userId);
	   }
}

