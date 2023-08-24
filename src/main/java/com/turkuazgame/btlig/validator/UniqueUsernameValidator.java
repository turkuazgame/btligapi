package com.turkuazgame.btlig.validator;


import com.turkuazgame.btlig.annotation.UniqueUsername;
import com.turkuazgame.btlig.entity.User;
import com.turkuazgame.btlig.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return false;
        }
        return true;
    }

}