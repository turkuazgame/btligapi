package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // CRUD Database Methods
}
