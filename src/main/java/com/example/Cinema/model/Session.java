package com.example.Cinema.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data @Entity @Table(name = "sessions")
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne @JoinColumn(name = "hall_id")
    private Hall hall;

    private LocalDateTime startTime;
    private String format;
    private Double basePrice;
}