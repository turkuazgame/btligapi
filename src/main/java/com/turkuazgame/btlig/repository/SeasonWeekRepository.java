package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.SeasonWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonWeekRepository extends JpaRepository<SeasonWeek, Long> {

    // CRUD Database Methods
}
