package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRateRepository extends JpaRepository<MatchRate, Long> {

    List<MatchRate> findByMatch(Match match);
    List<MatchRate> findByRate(Rate rate);
    List<MatchRate> findByMatchAndRate(Match match, Rate rate);
}
