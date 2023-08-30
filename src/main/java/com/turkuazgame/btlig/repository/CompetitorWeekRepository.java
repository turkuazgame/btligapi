package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorWeekRepository extends JpaRepository<CompetitorWeek, Long> {

    List<CompetitorWeek> findByCompetitorSeason(CompetitorSeason competitorSeason);
    List<CompetitorWeek> findBySeasonWeek(SeasonWeek seasonWeek);
    List<CompetitorWeek> findByCompetitorSeasonAndSeasonWeek(CompetitorSeason competitorSeason, SeasonWeek seasonWeek);
}
