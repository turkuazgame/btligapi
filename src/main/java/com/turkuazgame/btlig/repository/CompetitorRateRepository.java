package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.CompetitorRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRateRepository extends JpaRepository<CompetitorRate, Long> {

    // CRUD Database Methods
}
