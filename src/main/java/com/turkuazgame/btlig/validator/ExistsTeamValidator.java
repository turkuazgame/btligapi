package com.turkuazgame.btlig.validator;

import com.turkuazgame.btlig.annotation.ExistsTeam;
import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.repository.TeamRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistsTeamValidator implements ConstraintValidator<ExistsTeam, Long> {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public boolean isValid(Long teamId, ConstraintValidatorContext context) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if(team != null) {
            return true;
        }
        return false;
    }
}
