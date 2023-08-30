package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Match;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MatchResponse extends BaseResponse implements IResponse{

    private Long matchId;
    private String matchCode;
    private String matchName;
    private Date matchDate;
    private Long weekId;
    private Long homeTeamId;
    private Long awayTeamId;
    private short homeTeamScore;
    private short awayTeamScore;
    private int rateListSize;

    public MatchResponse(Match match) {
        this.setMatchId(match.getMatchId());
        this.setMatchCode(match.getMatchCode());
        this.setMatchName(match.getMatchName());
        this.setMatchDate(match.getMatchDate());
        this.setWeekId(match.getWeek().getWeekId());
        this.setHomeTeamId(match.getHomeTeam().getTeamId());
        this.setAwayTeamId(match.getAwayTeam().getTeamId());
        this.setHomeTeamScore(match.getHomeTeamScore());
        this.setAwayTeamScore(match.getAwayTeamScore());
        this.setRateListSize(match.getRates().size());

        setBaseInfo(match.getBaseInfo());
    }

}

