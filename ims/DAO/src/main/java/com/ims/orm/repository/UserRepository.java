package com.ims.orm.repository;

import com.ims.orm.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Users, String> {
	 Users findByEmail(String email);
	Users findOneById(String id);
	Page<Users> findAll(Pageable p);
}
