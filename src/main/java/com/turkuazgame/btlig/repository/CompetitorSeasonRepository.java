package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitorSeasonRepository extends JpaRepository<CompetitorSeason, Long> {

    List<CompetitorSeason> findByCompetitor(Competitor competitor);

   // List<CompetitorSeason> findByCompetitorId(Long competitorId);

}
