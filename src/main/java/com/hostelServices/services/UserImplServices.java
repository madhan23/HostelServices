package com.hostelServices.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostelServices.Util.JwtUtil;
import com.hostelServices.dao.UserRepositoryImp;
import com.hostelServices.dto.ResponseDto;
import com.hostelServices.excep.HostelNotFoundException;
import com.hostelServices.excep.UserNotFoundException;
import com.hostelServices.models.Hostel;
import com.hostelServices.models.RoomDetails;
import com.hostelServices.models.User;

@Service
public class UserImplServices implements UserServices {

	@Autowired
	UserRepositoryImp userRepositoryImp;

	@Autowired
	JwtUtil jwt;
	
	@Override
	public ResponseDto userLogin(String mailId, String password) throws UserNotFoundException{
	
		User userdetails  = userRepositoryImp.userLogin(mailId,password);
		String jwtToken = 	jwt.generateToken(userdetails.getUserName()+" "+userdetails.getMailId());
	
		return new ResponseDto(new Timestamp(System.currentTimeMillis()), "success", jwtToken,"Logined Successfully");
	}

	public User addUserDetails(User userDetails) {
		return userRepositoryImp.addUserDetails(userDetails);
	}

	public List<User> getAllUser() throws UserNotFoundException {

		List<User> userList = userRepositoryImp.getAllUser();
		if (userList.isEmpty())
			throw new UserNotFoundException("No Users Found");
		return userList;
	}

	public List<Hostel> getHostelDetails(long userId) throws HostelNotFoundException, UserNotFoundException {

		List<Hostel> hostelList = userRepositoryImp.getHostelDetailsObj(userId);
		if (hostelList.isEmpty())
			throw new HostelNotFoundException("No HostelList found for these user");
		return hostelList;
	}

	
	public Hostel hotelDetailEntry(String token,long userId, Hostel hosteldetails) throws Exception {
		Hostel hostel=null;
		if(validateToken(token)){
			 hostel =userRepositoryImp.hotelDetailEntry(userId,hosteldetails);	
		}
				return hostel;
		
	}

 
	private boolean validateToken(String token) throws Exception {
		
	if(token!=null && !token.isEmpty()) {
		// Avoid Bearer reading token
	String jwtToken=token.substring(7);
	String username = null,userId =null;
	String []userInformation = jwt.extractUsername(jwtToken).split(" ");
	if(userInformation.length>1) {
		username = userInformation[0];
		userId = userInformation[1];
		try {
		return jwt.validateToken(jwtToken, username);
		}
		catch(Exception ex) {
		throw new Exception(ex.getMessage());
		}
	}
	
	}
		
	return false;
	}

	public Hostel hostelRoomDetailEntry(String token,long userId, long hostelId, List<RoomDetails> roomDetails) throws Exception {
		Hostel hostel=null;
		if(validateToken(token))
		 hostel =userRepositoryImp.hostelRoomDetailEntry(userId,hostelId,roomDetails);	
		
			return hostel;
	
	}


	public Hostel hotelDetailUpdate(String token,long userId, long hostelId, Hostel hostel) throws Exception {
		Hostel hostelObj=null;
		if(validateToken(token))
		 hostelObj =userRepositoryImp.hotelDetailUpdate(userId,hostelId,hostel);	
			return hostelObj;

	}



	public Hostel hostelRoomDetailUpdate(String token,long userId, long hostelId,long roomId, RoomDetails roomDetails) throws Exception {
		Hostel hostel=null;
		if(validateToken(token))
		hostel =userRepositoryImp.hostelRoomDetailUpdate(userId,hostelId,roomId,roomDetails);	
			return hostel;

	}



	public Hostel hostelRoomDetailDelete(String token,long userId, long hostelId, long roomId) throws Exception {
		Hostel hostel=null;
		if(validateToken(token))
		 hostel =userRepositoryImp.hostelRoomDetailDelete(userId,hostelId,roomId);	

			return hostel;
	
	}


	@Override
	public void  removeHostelDetails(long userId, long hostelId) throws UserNotFoundException, HostelNotFoundException {
		userRepositoryImp.removeHostelDetails(userId,hostelId);
	
	}







	
	
}
