package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.MatchRate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MatchRateResponse extends BaseResponse implements IResponse {

    private Long matchRateId;
    private Long matchId;
    private Long rateId;
    private double rateValue;
    private boolean rateRealFlag;

    public MatchRateResponse(MatchRate matchRate) {
        this.setMatchRateId(matchRate.getMatchRateId());
        this.setMatchId(matchRate.getMatch().getMatchId());
        this.setRateId(matchRate.getRate().getRateId());
        this.setRateValue(matchRate.getRateValue());
        this.setRateRealFlag(matchRate.isRateRealFlag());
        setBaseInfo(matchRate.getBaseInfo());
    }
}
