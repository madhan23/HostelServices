package com.hostelServices.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hostelServices.excep.HostelNotFoundException;
import com.hostelServices.excep.UserNotFoundException;
import com.hostelServices.models.Hostel;
import com.hostelServices.models.RoomDetails;
import com.hostelServices.models.User;

@Repository
public class UserRepositoryImp {

	@Autowired
	UserRepository userRepository;

	@Autowired
	HostelRepository hostelRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	EntityManager em;

	public List<User> getAllUser() {
		List<User> userList = (List<User>) userRepository.findAll();
		return userList;
	}

	public User getUser(long userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null)
			throw new UserNotFoundException("Invalid UserID");

		return user;

	}

	public Hostel getHostelDetails(long hostelId) throws HostelNotFoundException {

		Hostel hostel = hostelRepository.findById(hostelId).orElse(null);
		if (hostel == null)
			throw new HostelNotFoundException("Invalid HostelID");

		return hostel;

	}

	public List<Hostel> getHostelDetailsObj(long userId) throws UserNotFoundException {
		User user = getUser(userId);
		return user.getHostelList();

	}

	@Transactional
	public User addUserDetails(User user) {
		userRepository.save(user);
		return user;
	}

	@Transactional
	public Hostel hotelDetailEntry(long userId, Hostel hostel) throws UserNotFoundException {
		User user = getUser(userId);
		hostel.setUser(user);
		return hostelRepository.save(hostel);

	}

	@Transactional
	public Hostel hostelRoomDetailEntry(long userId, long hostelId, List<RoomDetails> roomDetailsList)
			throws UserNotFoundException, HostelNotFoundException {
		getUser(userId);
		Hostel hostel = getHostelDetails(hostelId);
		for (RoomDetails roomDetails : roomDetailsList) {
			roomDetails.setHostel(hostel);
			roomRepository.save(roomDetails);
		}

		return hostel;

	}

	@Transactional
	public Hostel hotelDetailUpdate(long userId, long hostelId, Hostel hostel)
			throws UserNotFoundException, HostelNotFoundException {
		User user = getUser(userId);
		Hostel hostelObj = getHostelDetails(hostelId);
		hostel.setUser(user);
		hostel.setRoomDetails(hostelObj.getRoomDetails());
		hostel.setHostelId(hostelId);

		return hostelRepository.save(hostel);

	}

	@Transactional
	public Hostel hostelRoomDetailUpdate(long userId, long hostelId, long roomId, RoomDetails roomDetails)
			throws UserNotFoundException, HostelNotFoundException {
	    getUser(userId);
		Hostel hostel = getHostelDetails(hostelId);
		RoomDetails roomInfoDetails = roomRepository.findById(roomId).orElse(null);
	
		
		
		if (roomInfoDetails == null)
			throw new HostelNotFoundException("Invalid RoomId");
		
			roomDetails.setRoomId(roomId);
			roomDetails.setHostel(hostel);
			roomRepository.save(roomDetails);
		

		
		return hostel;
		
		
		
/*		hostel.setUser(user);
		if (hostel.getRoomDetails().isEmpty())
			throw new HostelNotFoundException("No RoomDetails Found");
		List<RoomDetails> details = hostel.getRoomDetails().stream().filter(room -> room.getRoomId() != roomId)
				.collect(Collectors.toList());

		roomDetails.setRoomId(roomId);
		roomDetails.setHostel(hostel);
		details.add(roomDetails);
		hostel.setRoomDetails(details);
		hostel.setHostelId(hostelId);
		return hostelRepository.save(hostel);*/

	}

	@Transactional
	public Hostel hostelRoomDetailDelete(long userId, long hostelId, long roomId)
			throws UserNotFoundException, HostelNotFoundException {
		getUser(userId);
		Hostel hostel = getHostelDetails(hostelId);

		RoomDetails roomInfoDetails = roomRepository.findById(roomId).orElse(null);

		if (roomInfoDetails == null)
			throw new HostelNotFoundException("Invalid RoomId");

		roomRepository.deleteById(roomId);

		return hostel;

	}

	@Transactional
	public void removeHostelDetails(long userId, long hostelId) throws UserNotFoundException, HostelNotFoundException {
		getUser(userId);
		getHostelDetails(hostelId);
		hostelRepository.deleteById(hostelId);

	}

	public User userLogin(String mailId, String password) throws UserNotFoundException {

		Query query = em.createNamedQuery("LoginQuery", User.class);
		query.setParameter(1, mailId);
		query.setParameter(2, password);
		User user = (User) query.getSingleResult();
		if (user == null)
			throw new UserNotFoundException("Incorrect mailId or Password");
		return user;
	}

}
