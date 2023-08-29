package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsSeasonType;
import com.turkuazgame.btlig.entity.SeasonType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsSeasonTypeValidator implements ConstraintValidator<ExistsSeasonType, String> {

    @Override
    public boolean isValid(String seasonTypeStr, ConstraintValidatorContext context) {
        try {
            SeasonType seasonType = SeasonType.valueOf(seasonTypeStr);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }
}
