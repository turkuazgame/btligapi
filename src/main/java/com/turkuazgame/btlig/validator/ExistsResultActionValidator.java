package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsResultAction;
import com.turkuazgame.btlig.entity.ResultAction;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsResultActionValidator implements ConstraintValidator<ExistsResultAction, String> {

    @Override
    public boolean isValid(String resultActionStr, ConstraintValidatorContext context) {
        try {
            ResultAction resultAction = ResultAction.valueOf(resultActionStr);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }
}
