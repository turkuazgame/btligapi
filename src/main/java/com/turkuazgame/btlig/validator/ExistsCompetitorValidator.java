package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsCompetitor;
import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.repository.CompetitorRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsCompetitorValidator implements ConstraintValidator<ExistsCompetitor, Long> {

    @Autowired
    CompetitorRepository competitorRepository;

    @Override
    public boolean isValid(Long competitorId, ConstraintValidatorContext context) {
        Competitor competitor = competitorRepository.findById(competitorId).orElse(null);
        return competitor!=null ? true : false;
    }
}
