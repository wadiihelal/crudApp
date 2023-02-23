package com.example.EPAC.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";
    @Id
    private int orderId;
    private int isbn;
    private int nbCopie;
    private boolean doubleSided;
    private int rollId;
    private List ListParts;
}
