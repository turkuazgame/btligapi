package com.turkuazgame.btlig.service.rateresult;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.ResultPeriod;

public class ActionMatchResultService implements  IActionService {

    MatchRate matchRate;

    public ActionMatchResultService(MatchRate matchRate) {
        this.matchRate = matchRate;
    }

    @Override
    public boolean calculate() {
        ResultPeriod resultPeriod = matchRate.getRate().getRateType().getResultPeriod();
        Match match = matchRate.getMatch();

        if(resultPeriod.equals(ResultPeriod.HALF)) {
            return switch (matchRate.getRate().getFirstValue()) {
                case "1" -> match.getHomeTeamHalfScore() > match.getAwayTeamHalfScore();
                case "2" -> match.getAwayTeamHalfScore() > match.getHomeTeamHalfScore();
                case "0" -> match.getHomeTeamHalfScore() == match.getAwayTeamHalfScore();
                default -> false;
            };
        }
        else if(resultPeriod.equals(ResultPeriod.FINAL)) {
            return switch (matchRate.getRate().getFirstValue()) {
                case "1" -> match.getHomeTeamFinalScore() > match.getAwayTeamFinalScore();
                case "2" -> match.getAwayTeamFinalScore() > match.getHomeTeamFinalScore();
                case "0" -> match.getHomeTeamFinalScore() == match.getAwayTeamFinalScore();
                default -> false;
            };
        }
        else if(resultPeriod.equals(ResultPeriod.BOTH)) {
            boolean halfResult =  switch (matchRate.getRate().getFirstValue()) {
                                    case "1" -> match.getHomeTeamHalfScore() > match.getAwayTeamHalfScore();
                                    case "2" -> match.getAwayTeamHalfScore() > match.getHomeTeamHalfScore();
                                    case "0" -> match.getHomeTeamHalfScore() == match.getAwayTeamHalfScore();
                                    default -> false; };

            boolean finalResult = switch (matchRate.getRate().getSecondValue()) {
                                    case "1" -> match.getHomeTeamFinalScore() > match.getAwayTeamFinalScore();
                                    case "2" -> match.getAwayTeamFinalScore() > match.getHomeTeamFinalScore();
                                    case "0" -> match.getHomeTeamFinalScore() == match.getAwayTeamFinalScore();
                                    default -> false; };

            return halfResult && finalResult;
        }
        else
            throw new IllegalArgumentException("Unknown Rate Result Period " + matchRate.getRate().getRateType().getResultPeriod().toString());
    }
}
