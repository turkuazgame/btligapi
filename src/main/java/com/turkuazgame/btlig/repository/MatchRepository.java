package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    // CRUD Database Methods
}
