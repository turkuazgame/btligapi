package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.RateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateTypeRepository extends JpaRepository<RateType, Long> {

    // CRUD Database Methods
}
