package com.example.Cinema.controller;

import com.example.Cinema.model.Actor;
import com.example.Cinema.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actors")
@CrossOrigin(origins = "*")
public class ActorController {
    @Autowired private ActorRepository repo;

    @PostMapping public Actor create(@RequestBody Actor actor) { return repo.save(actor); }
    @GetMapping public List<Actor> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Actor> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor details) {
        return repo.findById(id).map(a -> {
            a.setFullName(details.getFullName());
            return ResponseEntity.ok(repo.save(a));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }
}