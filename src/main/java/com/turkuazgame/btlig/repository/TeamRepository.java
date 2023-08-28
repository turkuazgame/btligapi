package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByLeague(League league);
}
