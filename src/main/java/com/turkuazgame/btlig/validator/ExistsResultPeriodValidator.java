package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsResultPeriod;
import com.turkuazgame.btlig.entity.ResultPeriod;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsResultPeriodValidator implements ConstraintValidator<ExistsResultPeriod, String> {

    @Override
    public boolean isValid(String resultPeriodStr, ConstraintValidatorContext context) {
        try {
            ResultPeriod resultPeriod = ResultPeriod.valueOf(resultPeriodStr);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }
}
