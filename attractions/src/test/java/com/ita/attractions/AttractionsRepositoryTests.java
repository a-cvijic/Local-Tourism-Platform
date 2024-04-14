package com.ita.attractions;

import com.ita.attractions.model.Attractions;
import com.ita.attractions.repository.AttractionsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AttractionsRepositoryTests {

    @Autowired
    private AttractionsRepository attractionsRepository;

    @Test
    void testAttractionSave() {
        Attractions attraction = new Attractions();
        attraction.setName("Test Attraction");
        attraction.setDescription("Test Description");
        attraction.setLocation("Test Location");

        Attractions savedAttraction = attractionsRepository.save(attraction);

        assertThat(savedAttraction).isNotNull();
        assertThat(savedAttraction.getId()).isNotNull();
    }
}
