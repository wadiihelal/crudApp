package com.example.EPAC.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roll")
@Data
@NoArgsConstructor
public class Roll {

    @Transient
    public static final String SEQUENCE_NAME = "rolls_sequence";
    @Id
    private int rollId;
    private double height;
    private double width;
    private  String type;
    private float pourcentage;

    public Roll(int rollId, double height, double width) {
        this.rollId = rollId;
        this.height = height;
        this.width = width;
    }
}
