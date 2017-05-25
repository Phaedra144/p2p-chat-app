package com.greenfox.szilvi.chatapp.repository;

import com.greenfox.szilvi.chatapp.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Szilvi on 2017. 05. 18..
 */
public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message>findAllByOrderByTimestampAsc();
}
