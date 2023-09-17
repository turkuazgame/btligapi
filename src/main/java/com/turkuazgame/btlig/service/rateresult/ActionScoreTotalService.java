package com.turkuazgame.btlig.service.rateresult;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.ResultPeriod;

public class ActionScoreTotalService implements IActionService {

    MatchRate matchRate;

    public ActionScoreTotalService(MatchRate matchRate) {
        this.matchRate = matchRate;
    }

    @Override
    public boolean calculate() {
        ResultPeriod resultPeriod = matchRate.getRate().getRateType().getResultPeriod();
        Match match = matchRate.getMatch();
        short startScore = Short.parseShort(matchRate.getRate().getFirstValue());
        short endScore = Short.parseShort(matchRate.getRate().getSecondValue());

        if(resultPeriod.equals(ResultPeriod.HALF)) {
            short halfTotalScore = (short) (match.getHomeTeamHalfScore()+match.getAwayTeamHalfScore());
            return halfTotalScore >= startScore && halfTotalScore <= endScore;
        }
        else if(resultPeriod.equals(ResultPeriod.FINAL)) {
            short finalTotalScore = (short) (match.getHomeTeamFinalScore()+match.getAwayTeamFinalScore());
            return finalTotalScore >= startScore && finalTotalScore <= endScore;
        }
        else
            throw new IllegalArgumentException("Unknown Rate Result Period : " + matchRate.getRate().getRateType().getResultPeriod().toString());
    }
}
