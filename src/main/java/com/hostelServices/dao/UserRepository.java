package com.hostelServices.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hostelServices.models.User;


public interface UserRepository extends CrudRepository<User, Long> {


}
