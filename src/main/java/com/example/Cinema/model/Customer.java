package com.example.Cinema.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "customers")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String fullName;
    private String bankCard;
    private String phone;
    private String email;
}