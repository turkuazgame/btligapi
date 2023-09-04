package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.CompetitorRate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CompetitorRateResponse extends BaseResponse implements IResponse {

    private Long competitorRateId;
    private Long competitorMatchId;
    private Long matchRateId;

    public CompetitorRateResponse(CompetitorRate competitorRate) {
        this.setCompetitorRateId(competitorRate.getCompetitorRateId());
        this.setCompetitorMatchId(competitorRate.getCompetitorMatch().getCompetitorMatchId());
        this.setMatchRateId(competitorRate.getMatchRate().getMatchRateId());
        setBaseInfo(competitorRate.getBaseInfo());
    }
}
