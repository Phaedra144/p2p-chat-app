package com.greenfox.szilvi.chatapp.repository;

import com.greenfox.szilvi.chatapp.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
public interface UserRepo extends CrudRepository<User, Long> {
}
