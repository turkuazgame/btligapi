package com.turkuazgame.btlig.service.rateresult;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.ResultPeriod;

public class ActionScoreReciprocalService implements IActionService {

    MatchRate matchRate;

    public ActionScoreReciprocalService(MatchRate matchRate) {
        this.matchRate = matchRate;
    }

    @Override
    public boolean calculate() {
        ResultPeriod resultPeriod = matchRate.getRate().getRateType().getResultPeriod();
        Match match = matchRate.getMatch();
        boolean reciprocalFlag = Boolean.parseBoolean(matchRate.getRate().getFirstValue());

        if(resultPeriod.equals(ResultPeriod.HALF))
            return reciprocalFlag && match.getHomeTeamHalfScore() > 0 && match.getAwayTeamHalfScore() > 0;
        else if(resultPeriod.equals(ResultPeriod.FINAL))
            return reciprocalFlag && match.getHomeTeamFinalScore() > 0 && match.getAwayTeamFinalScore() > 0;
        else
            throw new IllegalArgumentException("Unknown Rate Result Period " + matchRate.getRate().getRateType().getResultPeriod().toString());
    }
}
