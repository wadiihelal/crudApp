package com.example.EPAC.repository;

import com.example.EPAC.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order,Integer> {
    Order findOrdersByIsbn(Integer id);

}
