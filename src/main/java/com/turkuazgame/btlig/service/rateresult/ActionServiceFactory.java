package com.turkuazgame.btlig.service.rateresult;

import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.ResultAction;

public class ActionServiceFactory {

    public IActionService createActionService(MatchRate matchRate) {
        if(matchRate.getRate().getRateType().getResultAction().equals(ResultAction.MATCH_RESULT))
            return new ActionMatchResultService(matchRate);
        else if(matchRate.getRate().getRateType().getResultAction().equals(ResultAction.SCORE_UPDOWN))
            return new ActionScoreUpdownService(matchRate);
        else if(matchRate.getRate().getRateType().getResultAction().equals(ResultAction.SCORE_TOTAL))
            return new ActionScoreTotalService(matchRate);
        else if(matchRate.getRate().getRateType().getResultAction().equals(ResultAction.SCORE_RECIPROCAL))
            return new ActionScoreReciprocalService(matchRate);
        else
            throw new IllegalArgumentException("Unknown Rate Result Action : " + matchRate.getRate().getRateType().getResultAction().toString());
    }

}
