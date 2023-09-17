package com.turkuazgame.btlig.service.rateresult;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.ResultPeriod;

public class ActionScoreUpdownService implements IActionService {

    MatchRate matchRate;

    public ActionScoreUpdownService(MatchRate matchRate) {
        this.matchRate = matchRate;
    }

    @Override
    public boolean calculate() {
        ResultPeriod resultPeriod = matchRate.getRate().getRateType().getResultPeriod();
        Match match = matchRate.getMatch();
        double updown = Double.parseDouble(matchRate.getRate().getFirstValue().charAt(0)+"."+matchRate.getRate().getFirstValue().charAt(1));

        if(resultPeriod.equals(ResultPeriod.HALF)) {
            double halfTotalScore = match.getHomeTeamHalfScore()+match.getAwayTeamHalfScore();
            if(matchRate.getRate().getSecondValue().equals("UP"))
                return halfTotalScore > updown;
            else if(matchRate.getRate().getSecondValue().equals("DOWN"))
                return halfTotalScore < updown;
            else
                return false;
        }
        else if(resultPeriod.equals(ResultPeriod.FINAL)) {
            double finalTotalScore = match.getHomeTeamFinalScore()+match.getAwayTeamFinalScore();
            if(matchRate.getRate().getSecondValue().equals("UP"))
                return finalTotalScore > updown;
            else if(matchRate.getRate().getSecondValue().equals("DOWN"))
                return finalTotalScore < updown;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("Unknown Rate Result Period : " + matchRate.getRate().getRateType().getResultPeriod().toString());
    }
}
