package com.ita.attractions.controller;

import com.ita.attractions.model.Attractions;
import com.ita.attractions.repository.AttractionsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Get all attractions", description = "Get a list of all attractions")
    @GetMapping
    public List<Attractions> getAllAttractions() {
        return attractionRepository.findAll();
    }

    @Operation(summary = "Create a new attraction", description = "Create a new attraction entry")
    @ApiResponse(responseCode = "201", description = "Attraction created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Attractions.class)) })
    @PostMapping
    public ResponseEntity<Attractions> createAttraction(@RequestBody Attractions attraction) {
        return ResponseEntity.ok(attractionRepository.save(attraction));
    }

    @Operation(summary = "Get an attraction by ID", description = "Get details of a specific attraction by its ID")
    @ApiResponse(responseCode = "200", description = "Attraction found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Attractions.class)) })
    @ApiResponse(responseCode = "404", description = "Attraction not found")
    @GetMapping("/{id}")
    public ResponseEntity<Attractions> getAttractionById(@PathVariable Long id) {
        Optional<Attractions> attraction = attractionRepository.findById(id);
        return attraction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an attraction by ID", description = "Update details of a specific attraction by its ID")
    @ApiResponse(responseCode = "200", description = "Attraction updated", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Attractions.class)) })
    @ApiResponse(responseCode = "404", description = "Attraction not found")
    @PutMapping("/{id}")
    public ResponseEntity<Attractions> updateAttraction(@PathVariable Long id,
            @RequestBody Attractions attractionDetails) {
        return attractionRepository.findById(id)
                .map(attraction -> {
                    attraction.setName(attractionDetails.getName());
                    attraction.setDescription(attractionDetails.getDescription());
                    attraction.setLocation(attractionDetails.getLocation());
                    return ResponseEntity.ok(attractionRepository.save(attraction));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an attraction by ID", description = "Delete a specific attraction by its ID")
    @ApiResponse(responseCode = "200", description = "Attraction deleted")
    @ApiResponse(responseCode = "404", description = "Attraction not found")
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
