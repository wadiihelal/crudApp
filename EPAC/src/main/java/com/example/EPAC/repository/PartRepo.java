package com.example.EPAC.repository;

import com.example.EPAC.entity.Part;
import com.example.EPAC.entity.Roll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartRepo  extends MongoRepository<Part,Integer> {
        Part findPartsByIsbn( int isbn);

}
