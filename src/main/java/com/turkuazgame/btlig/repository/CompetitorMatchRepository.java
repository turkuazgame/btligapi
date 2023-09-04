package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.CompetitorMatch;
import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorMatchRepository extends JpaRepository<CompetitorMatch, Long> {

    List<CompetitorMatch> findByMatch(Match match);
    List<CompetitorMatch> findByCompetitorWeek(CompetitorWeek competitorWeek);
    List<CompetitorMatch> findByCompetitorWeekAndMatch(CompetitorWeek competitorWeek, Match match);
}
