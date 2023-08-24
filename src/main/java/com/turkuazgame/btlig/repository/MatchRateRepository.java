package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.MatchRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRateRepository extends JpaRepository<MatchRate, Long> {

    // CRUD Database Methods
}
