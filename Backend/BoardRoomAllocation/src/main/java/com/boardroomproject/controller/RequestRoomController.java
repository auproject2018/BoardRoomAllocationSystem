package com.boardroomproject.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.boardroomproject.model.RequestRoom;
import com.boardroomproject.services.RequestRoomService;

@RestController
@RequestMapping("/request")
public class RequestRoomController {

	@Autowired
	RequestRoomService requestRoomService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void createRequest(@RequestBody RequestRoom requestRoom) {
		requestRoomService.createRequest(requestRoom);

	}
	
	@RequestMapping(value = "/accept/{requestId}", method = RequestMethod.PUT)
	    public void acceptRequest(@PathVariable("requestId") int requestId) {
		 requestRoomService.acceptRequest(requestId);
	 }
	
	@RequestMapping(value = "/reject/{requestId}", method = RequestMethod.PUT)
    public void rejectRequest(@PathVariable("requestId") int requestId,@RequestBody String remarkByAdmin) {
	 requestRoomService.rejectRequest(remarkByAdmin, requestId);
	}
	
	 @RequestMapping(value = "/{lId}/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<RequestRoom>getRequestByDateNLocation(@PathVariable("lId") int lId,@PathVariable("date") Date date) {
	       return requestRoomService.getRequestByDateNLocation(lId, date);
	        
	    }
	 
	 @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<RequestRoom>getPendingRequest(@PathVariable("userId") int userId) {
	       return requestRoomService.getRequestByUser(userId);
	   }
}

