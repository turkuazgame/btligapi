package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.entity.Week;
import com.turkuazgame.btlig.repository.WeekRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsWeekValidator implements ConstraintValidator<ExistsWeek, Long> {

    @Autowired
    WeekRepository weekRepository;

    @Override
    public boolean isValid(Long seasonWeekId, ConstraintValidatorContext context) {
        Week week = weekRepository.findById(seasonWeekId).orElse(null);
        if(week != null) {
            return true;
        }
        return false;
    }
}
