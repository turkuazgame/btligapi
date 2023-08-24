package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.CompetitorWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorWeekRepository extends JpaRepository<CompetitorWeek, Long> {

    // CRUD Database Methods
}
