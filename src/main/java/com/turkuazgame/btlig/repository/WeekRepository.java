package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {

    List<Week> findBySeason(Season season);
}
