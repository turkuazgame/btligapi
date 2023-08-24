package com.turkuazgame.btlig.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorSeasonRequest extends BaseRequest implements IRequest {

    private Long competitorSeasonId;
    private Long competitorId;
    private Long seasonId;
    private double totalScore;

    @Override
    public void setId(Long id) {
        this.competitorSeasonId = id;
    }
}
