package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.CompetitorMatch;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CompetitorMatchResponse extends BaseResponse implements IResponse {

    private Long competitorMatchId;
    private Long competitorWeekId;
    private Long matchId;
    private int rateListSize;

    public CompetitorMatchResponse(CompetitorMatch competitorMatch) {
        this.setCompetitorMatchId(competitorMatch.getCompetitorMatchId());
        this.setCompetitorWeekId(competitorMatch.getCompetitorWeek().getCompetitorWeekId());
        this.setMatchId(competitorMatch.getMatch().getMatchId());
        this.setRateListSize(competitorMatch.getRates().size());

        setBaseInfo(competitorMatch.getBaseInfo());
    }
}
