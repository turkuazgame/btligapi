package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {

    List<Competitor> findByUserUID(String userUID);
}
