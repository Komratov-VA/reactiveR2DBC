package com.example.reactiveR2DBC.repo;

import com.example.reactiveR2DBC.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {


}
