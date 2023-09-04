package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsCompetitorSeason;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.repository.CompetitorSeasonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsCompetitorSeasonValidator implements ConstraintValidator<ExistsCompetitorSeason, Long> {

    @Autowired
    CompetitorSeasonRepository competitorSeasonRepository;

    @Override
    public boolean isValid(Long competitorSeasonId, ConstraintValidatorContext context) {
        CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorSeasonId).orElse(null);
        return competitorSeason!=null ? true : false;
    }
}
