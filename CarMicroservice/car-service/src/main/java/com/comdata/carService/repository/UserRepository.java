package com.comdata.carService.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.comdata.carService.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

	Optional<User> findByEmail(String email);
	
}
