package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByWeek(Week week);
    List<Match> findByMatchCode(String matchCode);
    List<Match> findByHomeTeam(Team homeTeam);
    List<Match> findByAwayTeam(Team awayTeam);
}
