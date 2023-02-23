package com.example.EPAC.repository;

import com.example.EPAC.entity.Order;
import com.example.EPAC.entity.Roll;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RollRepo  extends MongoRepository<Roll,Integer> {

    List<Roll> findAllByType(String type);

}
