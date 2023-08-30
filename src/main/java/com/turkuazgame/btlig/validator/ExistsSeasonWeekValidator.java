package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsSeasonWeek;
import com.turkuazgame.btlig.entity.SeasonWeek;
import com.turkuazgame.btlig.repository.SeasonWeekRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsSeasonWeekValidator implements ConstraintValidator<ExistsSeasonWeek, Long> {

    @Autowired
    SeasonWeekRepository seasonWeekRepository;

    @Override
    public boolean isValid(Long seasonWeekId, ConstraintValidatorContext context) {
        SeasonWeek seasonWeek = seasonWeekRepository.findById(seasonWeekId).orElse(null);
        if(seasonWeek != null) {
            return true;
        }
        return false;
    }
}
