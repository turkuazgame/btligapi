package com.turkuazgame.btlig.validator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.turkuazgame.btlig.annotation.ExistsFirebaseUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsFirebaseUserValidator implements ConstraintValidator<ExistsFirebaseUser, String> {

    @Override
    public boolean isValid(String userUID, ConstraintValidatorContext context) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(userUID);
            return userRecord!=null ? true : false;
        } catch (FirebaseAuthException e) {
            return false;
        }
    }
}
