package com.stackroute.usertrackservice.repository;

import com.stackroute.usertrackservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTrackRepository extends MongoRepository<User,String> {
    User findByUserName(String userName);
}
