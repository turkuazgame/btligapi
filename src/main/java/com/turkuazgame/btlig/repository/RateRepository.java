package com.turkuazgame.btlig.repository;

import com.turkuazgame.btlig.entity.Rate;
import com.turkuazgame.btlig.entity.RateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findByRateType(RateType rateType);
    List<Rate> findByRateCode(String rateCode);
}
