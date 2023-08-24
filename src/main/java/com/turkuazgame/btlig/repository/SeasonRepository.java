package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

    List<Season> findBySeasonCode(String seasonCode);
}
