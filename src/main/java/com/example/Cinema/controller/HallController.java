package com.example.Cinema.controller;

import com.example.Cinema.model.Hall;
import com.example.Cinema.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@CrossOrigin(origins = "*")
public class HallController {
    @Autowired
    private HallRepository repo;

    @PostMapping
    public Hall create(@RequestBody Hall hall) {
        return repo.save(hall);
    }

    @GetMapping
    public List<Hall> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> update(@PathVariable Long id, @RequestBody Hall details) {
        return repo.findById(id).map(h -> {
            h.setName(details.getName());
            h.setTotalRows(details.getTotalRows());
            h.setSeatsPerRow(details.getSeatsPerRow());
            h.setSupportedFormat(details.getSupportedFormat()); // <--- ДОБАВИТЬ ЭТУ СТРОКУ
            return ResponseEntity.ok(repo.save(h));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}