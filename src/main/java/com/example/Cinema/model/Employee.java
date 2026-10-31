package com.example.Cinema.model;
import jakarta.persistence.*;
import lombok.Data;

@Data @Entity @Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String fullName;
    private String role;
}