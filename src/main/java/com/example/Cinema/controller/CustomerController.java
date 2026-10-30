package com.example.Cinema.controller;

import com.example.Cinema.model.Customer;
import com.example.Cinema.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired private CustomerRepository repo;

    @PostMapping public Customer create(@RequestBody Customer customer) { return repo.save(customer); }
    @GetMapping public List<Customer> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Customer> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer details) {
        return repo.findById(id).map(c -> {
            c.setFullName(details.getFullName());
            c.setPhone(details.getPhone());
            return ResponseEntity.ok(repo.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }
}