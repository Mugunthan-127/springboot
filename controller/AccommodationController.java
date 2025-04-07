package com.example.demo1.controller;

import com.example.demo1.entity.Accommodation;
import com.example.demo1.service.AccommodationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @PostMapping
    public Accommodation createAccommodation(@RequestBody Accommodation accommodation) {
        return accommodationService.createAccommodation(accommodation);
    }

    @GetMapping
    public Page<Accommodation> getAllAccommodations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "location,asc") String[] sort) {
        return accommodationService.getAllAccommodations(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Accommodation accommodation = accommodationService.getAccommodationById(id);
        return accommodation != null ? ResponseEntity.ok(accommodation) : ResponseEntity.notFound().build();
    }

    @GetMapping("/location/{location}")
    public List<Accommodation> getAccommodationsByLocation(@PathVariable String location) {
        return accommodationService.getAccommodationsByLocation(location);
    }
    @GetMapping("/price/{location}")
    
        public double getAccommodationByLocation(@PathVariable String location)
        {
            return accommodationService.getByPricePerNight(location);
        }
    

    @GetMapping("/price-range")
    public List<Accommodation> getAccommodationsWithinPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return accommodationService.getAccommodationsWithinPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/search")
    public List<Accommodation> getAccommodationsByKeyword(@RequestParam String keyword) {
        return accommodationService.getAccommodationsByKeywordInName(keyword);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable Long id, @RequestBody Accommodation updatedAccommodation) {
        Accommodation accommodation = accommodationService.updateAccommodation(id, updatedAccommodation);
        return accommodation != null ? ResponseEntity.ok(accommodation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }
}
