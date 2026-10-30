package com.example.Cinema.controller;

import com.example.Cinema.model.Ticket;
import com.example.Cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketRepository repo;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Ticket ticket) {
        ticket.setBookingTime(LocalDateTime.now());
        try {
            return ResponseEntity.ok(repo.save(ticket));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: Это место уже забронировано!");
        }
    }

    @GetMapping
    public List<Ticket> getAll() { return repo.findAll(); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}