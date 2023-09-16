package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsResultSubject;
import com.turkuazgame.btlig.entity.ResultSubject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsResultSubjectValidator implements ConstraintValidator<ExistsResultSubject, String> {

    @Override
    public boolean isValid(String resultSubjectStr, ConstraintValidatorContext context) {
        try {
            ResultSubject resultSubject = ResultSubject.valueOf(resultSubjectStr);
            return true;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }
}
