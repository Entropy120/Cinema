package com.example.Cinema.controller;

import com.example.Cinema.model.Session;
import com.example.Cinema.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "*")
public class SessionController {
    @Autowired private SessionRepository repo;

    @PostMapping public Session create(@RequestBody Session session) { return repo.save(session); }
    @GetMapping public List<Session> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Session> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody Session details) {
        return repo.findById(id).map(s -> {
            s.setMovie(details.getMovie());
            s.setHall(details.getHall());
            s.setStartTime(details.getStartTime());
            s.setBasePrice(details.getBasePrice());
            return ResponseEntity.ok(repo.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }
}