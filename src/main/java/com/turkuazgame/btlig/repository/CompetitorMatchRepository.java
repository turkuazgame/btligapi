package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.CompetitorMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorMatchRepository extends JpaRepository<CompetitorMatch, Long> {

    // CRUD Database Methods
}
