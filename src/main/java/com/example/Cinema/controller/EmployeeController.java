package com.example.Cinema.controller;

import com.example.Cinema.model.Employee;
import com.example.Cinema.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired private EmployeeRepository repo;

    @PostMapping public Employee create(@RequestBody Employee employee) { return repo.save(employee); }
    @GetMapping public List<Employee> getAll() { return repo.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}") public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee details) {
        return repo.findById(id).map(e -> {
            e.setFullName(details.getFullName());
            e.setRole(details.getRole());
            return ResponseEntity.ok(repo.save(e));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.ok().build(); }
        return ResponseEntity.notFound().build();
    }
}