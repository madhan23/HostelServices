package com.hostelServices.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hostelServices.models.RoomDetails;


public interface RoomRepository extends CrudRepository<RoomDetails, Long>{

}
