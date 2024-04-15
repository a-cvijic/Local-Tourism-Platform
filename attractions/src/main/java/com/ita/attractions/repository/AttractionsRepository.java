package com.ita.attractions.repository;

import com.ita.attractions.model.Attractions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionsRepository extends JpaRepository<Attractions, Long> {
}
