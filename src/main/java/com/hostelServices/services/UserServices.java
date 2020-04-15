package com.hostelServices.services;

import java.util.List;

import com.hostelServices.dto.ResponseDto;
import com.hostelServices.excep.HostelNotFoundException;
import com.hostelServices.excep.UserNotFoundException;
import com.hostelServices.models.Hostel;
import com.hostelServices.models.RoomDetails;
import com.hostelServices.models.User;

public interface UserServices {

	public User addUserDetails(User userDetails);

	public List<User> getAllUser() throws UserNotFoundException;

	public List<Hostel> getHostelDetails(long userId) throws UserNotFoundException, HostelNotFoundException;

	public Hostel hotelDetailEntry(String token, long userId, Hostel hostel) throws UserNotFoundException, HostelNotFoundException, Exception;

	public Hostel hostelRoomDetailEntry(String token, long userId, long hostelId, List<RoomDetails> roomDetails) throws UserNotFoundException, HostelNotFoundException, Exception;

	public Hostel hotelDetailUpdate(String token, long userId, long hostelId, Hostel hostel) throws UserNotFoundException, HostelNotFoundException, HostelNotFoundException, Exception;

	public Hostel hostelRoomDetailUpdate(String token, long userId, long hostelId, long roomId, RoomDetails roomDetails) throws UserNotFoundException, HostelNotFoundException, Exception;

	public Hostel hostelRoomDetailDelete(String token, long userId, long hostelId, long roomId) throws UserNotFoundException, HostelNotFoundException, Exception;

	public void removeHostelDetails(long userId, long hostelId) throws UserNotFoundException, HostelNotFoundException;

	public ResponseDto userLogin(String mailId, String password) throws UserNotFoundException;
}
