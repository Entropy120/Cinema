package com.example.Cinema.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "halls")
public class Hall {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallId;
    private String name;
    private Integer totalRows;
    private Integer seatsPerRow;
    private String supportedFormat;
}