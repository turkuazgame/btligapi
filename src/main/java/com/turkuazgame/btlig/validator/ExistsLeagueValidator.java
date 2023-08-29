package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsLeague;
import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.repository.LeagueRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsLeagueValidator implements ConstraintValidator<ExistsLeague, Long> {

    @Autowired
    LeagueRepository leagueRepository;

    @Override
    public boolean isValid(Long leagueId, ConstraintValidatorContext context) {
        League league = leagueRepository.findById(leagueId).orElse(null);
        if(league != null) {
            return true;
        }
        return false;
    }
}
