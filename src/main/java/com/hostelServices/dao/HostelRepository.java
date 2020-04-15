package com.hostelServices.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hostelServices.models.Hostel;


public interface HostelRepository extends CrudRepository<Hostel, Long>{

}
