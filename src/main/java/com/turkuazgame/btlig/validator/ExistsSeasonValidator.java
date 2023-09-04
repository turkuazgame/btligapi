package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.repository.SeasonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsSeasonValidator implements ConstraintValidator<ExistsSeason, Long> {

    @Autowired
    SeasonRepository seasonRepository;

    @Override
    public boolean isValid(Long seasonId, ConstraintValidatorContext context) {
        Season season = seasonRepository.findById(seasonId).orElse(null);
        return season!=null ? true : false;
    }
}
