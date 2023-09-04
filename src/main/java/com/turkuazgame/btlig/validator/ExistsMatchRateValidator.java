package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsMatchRate;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.repository.MatchRateRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsMatchRateValidator implements ConstraintValidator<ExistsMatchRate, Long> {

    @Autowired
    MatchRateRepository matchRateRepository;

    @Override
    public boolean isValid(Long matchRateId, ConstraintValidatorContext context) {
        MatchRate matchRate = matchRateRepository.findById(matchRateId).orElse(null);
        return matchRate!=null ? true : false;
    }
}
