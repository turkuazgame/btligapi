package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorRateRepository extends JpaRepository<CompetitorRate, Long> {

    List<CompetitorRate> findByCompetitorMatch(CompetitorMatch competitorMatch);
    List<CompetitorRate> findByMatchRate(MatchRate matchRate);
    List<CompetitorRate> findByCompetitorMatchAndMatchRate(CompetitorMatch competitorMatch, MatchRate matchRate);
}
