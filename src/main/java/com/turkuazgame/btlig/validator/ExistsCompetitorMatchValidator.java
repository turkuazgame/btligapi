package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsCompetitorMatch;
import com.turkuazgame.btlig.entity.CompetitorMatch;
import com.turkuazgame.btlig.repository.CompetitorMatchRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsCompetitorMatchValidator implements ConstraintValidator<ExistsCompetitorMatch, Long> {

    @Autowired
    CompetitorMatchRepository competitorMatchRepository;

    @Override
    public boolean isValid(Long competitorMatchId, ConstraintValidatorContext context) {
        CompetitorMatch competitorMatch = competitorMatchRepository.findById(competitorMatchId).orElse(null);
        return competitorMatch!=null ? true : false;
    }
}
