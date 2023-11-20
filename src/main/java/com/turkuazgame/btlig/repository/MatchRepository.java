package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByWeek(Week week);
    List<Match> findByMatchCode(String matchCode);
    List<Match> findByHomeTeam(Team homeTeam);
    List<Match> findByAwayTeam(Team awayTeam);

    @Modifying
    @Query(value = "UPDATE Match SET homeTeamHalfScore = :homeTeamHalfScore, " +
                                    "awayTeamHalfScore = :awayTeamHalfScore, " +
                                    "homeTeamFinalScore = :homeTeamFinalScore, " +
                                    "awayTeamFinalScore = :awayTeamFinalScore " +
                   "WHERE matchId = :matchId")
    int updateMatchScoreById(@Param("matchId") Long matchId,
                             @Param("homeTeamHalfScore") short homeTeamHalfScore,
                             @Param("awayTeamHalfScore") short awayTeamHalfScore,
                             @Param("homeTeamFinalScore") short homeTeamFinalScore,
                             @Param("awayTeamFinalScore") short awayTeamFinalScore);
}
