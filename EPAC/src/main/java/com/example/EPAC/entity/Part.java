package com.example.EPAC.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Part")
@Data
@NoArgsConstructor
public class Part {

    @Id
    private  int isbn;
    private int pageNb;
    private double height;
    private double width;
    private boolean printed;

    public Part(int isbn, int pageNb, double height, double width) {
        this.isbn = isbn;
        this.pageNb = pageNb;
        this.height = height;
        this.width = width;
        this.printed=false;
    }
}
