package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.repository.MatchRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsMatchValidator implements ConstraintValidator<ExistsMatch, Long> {

    @Autowired
    MatchRepository matchRepository;

    @Override
    public boolean isValid(Long matchId, ConstraintValidatorContext context) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if(match != null) {
            return true;
        }
        return false;
    }
}
