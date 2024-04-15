package com.ita.attractions.controller;

import com.ita.attractions.model.Attractions;
import com.ita.attractions.repository.AttractionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AttractionsControllerTest {

    @Mock
    private AttractionsRepository attractionRepository;

    @InjectMocks
    private AttractionsController attractionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAttractions() {
        // Arrange
        Attractions attraction1 = new Attractions();
        attraction1.setId(1L);
        attraction1.setName("Attraction 1");
        attraction1.setDescription("Description 1");
        attraction1.setLocation("Location 1");

        Attractions attraction2 = new Attractions();
        attraction2.setId(2L);
        attraction2.setName("Attraction 2");
        attraction2.setDescription("Description 2");
        attraction2.setLocation("Location 2");

        List<Attractions> attractions = Arrays.asList(attraction1, attraction2);

        when(attractionRepository.findAll()).thenReturn(attractions);

        // Act
        List<Attractions> response = attractionController.getAllAttractions();

        // Assert
        assertEquals(2, response.size());
    }

    @Test
    void testGetAttractionById() {
        // Arrange
        Long attractionId = 1L;
        Attractions attraction = new Attractions();
        attraction.setId(attractionId);
        attraction.setName("Attraction 1");
        attraction.setDescription("Description 1");
        attraction.setLocation("Location 1");

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));

        // Act
        ResponseEntity<Attractions> response = attractionController.getAttractionById(attractionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attraction, response.getBody());
    }

    @Test
    void testCreateAttraction() {
        // Arrange
        Attractions attraction = new Attractions();
        attraction.setId(1L);
        attraction.setName("Attraction 1");
        attraction.setDescription("Description 1");
        attraction.setLocation("Location 1");

        when(attractionRepository.save(attraction)).thenReturn(attraction);

        // Act
        ResponseEntity<Attractions> response = attractionController.createAttraction(attraction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(attraction, response.getBody());
    }

    @SuppressWarnings("null")
    @Test
    void testUpdateAttraction() {
        // Arrange
        Long attractionId = 1L;
        Attractions originalAttraction = new Attractions();
        originalAttraction.setId(attractionId);
        originalAttraction.setName("Original Attraction");
        originalAttraction.setDescription("Original Description");
        originalAttraction.setLocation("Original Location");

        Attractions updatedAttraction = new Attractions();
        updatedAttraction.setId(attractionId);
        updatedAttraction.setName("Updated Attraction");
        updatedAttraction.setDescription("Updated Description");
        updatedAttraction.setLocation("Updated Location");

        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(originalAttraction));
        when(attractionRepository.save(any(Attractions.class))).thenReturn(updatedAttraction);

        // Act
        ResponseEntity<Attractions> response = attractionController.updateAttraction(attractionId, updatedAttraction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAttraction.getName(), response.getBody().getName());
        assertEquals(updatedAttraction.getDescription(), response.getBody().getDescription());
        assertEquals(updatedAttraction.getLocation(), response.getBody().getLocation());
    }

    @Test
    void testDeleteAttraction() {
        // Arrange
        Long attractionId = 1L;
        Attractions attraction = new Attractions();
        attraction.setId(attractionId);
        attraction.setName("Attraction 1");
        attraction.setDescription("Description 1");
        attraction.setLocation("Location 1");

        when(attractionRepository.existsById(attractionId)).thenReturn(true);
        doNothing().when(attractionRepository).deleteById(attractionId);

        // Act
        ResponseEntity<Void> response = attractionController.deleteAttraction(attractionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(attractionRepository, times(1)).deleteById(attractionId);
    }
}
