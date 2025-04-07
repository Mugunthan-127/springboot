package com.example.demo1.controller;

import com.example.demo1.entity.Owner;
import com.example.demo1.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public String createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @GetMapping
    public Page<Owner> getAllOwners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {
        return ownerService.getAllOwners(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.getOwnerById(id);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Owner> getOwnerByEmail(@PathVariable String email) {
        Owner owner = ownerService.getOwnerByEmail(email);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public long countOwnersByName(@PathVariable String name) {
        return ownerService.countOwnersByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Owner owner = ownerService.updateOwner(id, updatedOwner);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
