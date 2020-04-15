package com.hostelServices.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostelServices.dto.ResponseDto;
import com.hostelServices.excep.HostelNotFoundException;
import com.hostelServices.excep.UserNotFoundException;
import com.hostelServices.models.Hostel;
import com.hostelServices.models.RoomDetails;
import com.hostelServices.models.User;
import com.hostelServices.services.UserServices;

@RestController
@RequestMapping("/hostel/api")
public class HostelController {
	@Autowired
	UserServices userservices;

	@PostMapping(value="/login")
	private ResponseEntity<ResponseDto> userLogin(@RequestBody User user) throws UserNotFoundException {
		ResponseDto response = userservices.userLogin(user.getMailId(),user.getPassword());
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);

	}
	@PostMapping(value="/user")
	private ResponseEntity<User> UserDetails(@Valid @RequestBody User user) {
		User userResponse = userservices.addUserDetails(user);
		return new ResponseEntity<User>(userResponse, HttpStatus.OK);

	}
	@GetMapping("/user")
	public ResponseEntity<ResponseDto > getAllUser() throws UserNotFoundException  {
		List<User>  userResponse = userservices.getAllUser();
		
		return new ResponseEntity<ResponseDto>(
				new ResponseDto(new Timestamp(System.currentTimeMillis()), "success", userResponse),
				HttpStatus.OK);

	}
	
	@GetMapping("/{userId}/hostel")
	private ResponseEntity<ResponseDto> getHostelDetails(@PathVariable() long userId) throws UserNotFoundException, HostelNotFoundException {
		List<Hostel> response = userservices.getHostelDetails(userId);
		return new ResponseEntity<ResponseDto>(
				new ResponseDto(new Timestamp(System.currentTimeMillis()), "success", response),
				HttpStatus.OK);

	}
	
	
	@PostMapping("/{userId}/hostel")
	private ResponseEntity<Hostel> hotelDetailEntry(@Valid @RequestBody Hostel hostel,  @RequestHeader("Authorization") String token, @PathVariable long userId) throws Exception {
		Hostel response = userservices.hotelDetailEntry(token,userId,hostel);
		return new ResponseEntity<Hostel>(response, HttpStatus.ACCEPTED);

	}
	
	@PutMapping("/{userId}/hostel/{hostelId}")
	private ResponseEntity<Hostel> hotelDetailUpdate(@RequestBody Hostel hostel, @RequestHeader("Authorization") String token, 
			@PathVariable(name = "userId") long userId, @PathVariable(name = "hostelId") long hostelId) throws Exception {
		Hostel response = userservices.hotelDetailUpdate(token,userId,hostelId,hostel);
		return new ResponseEntity<Hostel>(response, HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("/{userId}/hostel/{hostelId}")
	private ResponseEntity<String> removeHostelDetails(@PathVariable(name = "userId") long userId, @PathVariable(name = "hostelId") long hostelId) throws UserNotFoundException, HostelNotFoundException {
		 userservices.removeHostelDetails(userId,hostelId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Hostel deleted");
	}
	
	@PostMapping("/{userId}/hostel/{hostelId}/roomdetails")
	private ResponseEntity<Hostel> hostelRoomDetailEntry(@Valid @RequestBody List<RoomDetails> roomDetails, @RequestHeader("Authorization") String token,
			@PathVariable(name = "userId") long userId, @PathVariable(name = "hostelId") long hostelId) throws Exception {
		
		Hostel response = userservices.hostelRoomDetailEntry(token,userId,hostelId,roomDetails);
				return new ResponseEntity<Hostel>(response, HttpStatus.ACCEPTED);

	}
	
	@PutMapping("/{userId}/hostel/{hostelId}/roomdetails/{roomId}")
	private ResponseEntity<Hostel> hostelRoomDetailUpdate(@RequestBody RoomDetails roomDetails, @RequestHeader("Authorization") String token,
			@PathVariable(name = "userId") long userId, @PathVariable(name = "hostelId") long hostelId,
			@PathVariable(name = "roomId") long roomId) throws Exception {
		Hostel response = userservices.hostelRoomDetailUpdate(token,userId,hostelId,roomId,roomDetails);
		return new ResponseEntity<Hostel>(response, HttpStatus.ACCEPTED);

	}	

	@DeleteMapping("/{userId}/hostel/{hostelId}/roomdetails/{roomId}")
	private ResponseEntity<Hostel> hostelRoomDetailDelete(@RequestHeader("Authorization") String token,@PathVariable(name = "userId") long userId, @PathVariable(name = "hostelId") long hostelId,
			@PathVariable(name = "roomId") long roomId) throws Exception {
		Hostel response = userservices.hostelRoomDetailDelete(token,userId,hostelId,roomId);
		return new ResponseEntity<Hostel>(response, HttpStatus.ACCEPTED);

	}	

}
