package com.ita.attractions.controller;

import com.ita.attractions.model.Attractions;
import com.ita.attractions.repository.AttractionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attractions")
public class AttractionsController {

    @Autowired
    private AttractionsRepository attractionRepository;

    @GetMapping
    public List<Attractions> getAllAttractions() {
        return attractionRepository.findAll();
    }

    @PostMapping
    public Attractions createAttraction(@RequestBody Attractions attraction) {
        return attractionRepository.save(attraction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attractions> getAttractionById(@PathVariable Long id) {
        Optional<Attractions> attraction = attractionRepository.findById(id);
        return attraction.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attractions> updateAttraction(@PathVariable Long id,
            @RequestBody Attractions attractionDetails) {
        Optional<Attractions> attraction = attractionRepository.findById(id);
        if (attraction.isPresent()) {
            Attractions updatedAttraction = attraction.get();
            updatedAttraction.setName(attractionDetails.getName());
            updatedAttraction.setDescription(attractionDetails.getDescription());
            updatedAttraction.setLocation(attractionDetails.getLocation());
            attractionRepository.save(updatedAttraction);
            return ResponseEntity.ok(updatedAttraction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        if (attractionRepository.existsById(id)) {
            attractionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
