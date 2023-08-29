package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsNation;
import com.turkuazgame.btlig.entity.Nation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsNationValidator implements ConstraintValidator<ExistsNation, String> {

    @Override
    public boolean isValid(String nationStr, ConstraintValidatorContext context) {
        try {
            Nation nation = Nation.valueOf(nationStr);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }
}
