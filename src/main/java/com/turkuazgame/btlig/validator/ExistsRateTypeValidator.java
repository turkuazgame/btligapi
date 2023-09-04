package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsRateType;
import com.turkuazgame.btlig.entity.RateType;
import com.turkuazgame.btlig.repository.RateTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsRateTypeValidator implements ConstraintValidator<ExistsRateType, Long> {

    @Autowired
    RateTypeRepository rateTypeRepository;

    @Override
    public boolean isValid(Long rateTypeId, ConstraintValidatorContext context) {
        RateType rateType = rateTypeRepository.findById(rateTypeId).orElse(null);
        return rateType!=null ? true : false;
    }
}
