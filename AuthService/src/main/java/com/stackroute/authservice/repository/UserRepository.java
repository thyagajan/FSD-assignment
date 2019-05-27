package com.stackroute.authservice.repository;

import com.stackroute.authservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserNameAndPassword(String userName,String Password);
}
