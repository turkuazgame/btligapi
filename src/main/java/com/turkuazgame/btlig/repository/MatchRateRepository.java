package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRateRepository extends JpaRepository<MatchRate, Long> {

    List<MatchRate> findByMatch(Match match);
    List<MatchRate> findByRate(Rate rate);
    List<MatchRate> findByMatchAndRate(Match match, Rate rate);

    @Modifying
    @Query(value = "UPDATE MatchRate SET rateRealFlag = :rateRealFlag WHERE matchRateId = :matchRateId")
    int updateRateRealFlagById(@Param("matchRateId") Long matchRateId, @Param("rateRealFlag") boolean rateRealFlag);
}
