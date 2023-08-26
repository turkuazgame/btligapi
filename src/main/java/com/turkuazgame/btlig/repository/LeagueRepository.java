package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    List<League> findByLeagueCode(String leagueCode);
}
