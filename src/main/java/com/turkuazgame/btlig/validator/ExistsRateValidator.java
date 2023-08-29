package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsRate;
import com.turkuazgame.btlig.entity.Rate;
import com.turkuazgame.btlig.repository.RateRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsRateValidator implements ConstraintValidator<ExistsRate, Long> {

    @Autowired
    RateRepository rateRepository;

    @Override
    public boolean isValid(Long rateId, ConstraintValidatorContext context) {
        Rate rate = rateRepository.findById(rateId).orElse(null);
        if(rate != null) {
            return true;
        }
        return false;
    }
}
