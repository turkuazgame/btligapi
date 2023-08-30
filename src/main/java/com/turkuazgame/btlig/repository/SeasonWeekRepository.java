package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.SeasonWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonWeekRepository extends JpaRepository<SeasonWeek, Long> {

    List<SeasonWeek> findBySeason(Season season);
}
