package com.example.EPAC.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequence_part")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBaseSequencePart {


    @Id
    private String id;

    private int seq;

    //getters and setters omitted
}