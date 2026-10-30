package com.example.Cinema.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"session_id", "row_number", "seat_number"})
})
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer rowNumber;
    private Integer seatNumber;
    private LocalDateTime bookingTime;
    private String status;
    private Double totalPrice;
}