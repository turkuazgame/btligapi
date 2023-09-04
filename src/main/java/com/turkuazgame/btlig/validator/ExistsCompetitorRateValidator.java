package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsCompetitorRate;
import com.turkuazgame.btlig.entity.CompetitorRate;
import com.turkuazgame.btlig.repository.CompetitorRateRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsCompetitorRateValidator implements ConstraintValidator<ExistsCompetitorRate, Long> {

    @Autowired
    CompetitorRateRepository competitorRateRepository;

    @Override
    public boolean isValid(Long competitorRateId, ConstraintValidatorContext context) {
        CompetitorRate competitorRate = competitorRateRepository.findById(competitorRateId).orElse(null);
        return competitorRate!=null ? true : false;
    }
}
