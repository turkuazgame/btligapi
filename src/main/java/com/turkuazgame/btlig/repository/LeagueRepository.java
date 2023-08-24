package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    // CRUD Database Methods
}
