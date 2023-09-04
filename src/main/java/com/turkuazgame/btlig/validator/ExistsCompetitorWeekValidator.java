package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsCompetitorWeek;
import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.repository.CompetitorWeekRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsCompetitorWeekValidator implements ConstraintValidator<ExistsCompetitorWeek, Long> {

    @Autowired
    CompetitorWeekRepository competitorWeekRepository;

    @Override
    public boolean isValid(Long competitorWeekId, ConstraintValidatorContext context) {
        CompetitorWeek competitorWeek = competitorWeekRepository.findById(competitorWeekId).orElse(null);
        return competitorWeek!=null ? true : false;
    }
}
