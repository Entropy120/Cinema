package com.example.Cinema.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data @Entity @Table(name = "actors")
public class Actor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;
    private String fullName;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private List<Movie> movies;
}