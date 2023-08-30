package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Competitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CompetitorResponse extends BaseResponse implements IResponse {

    private Long competitorId;
    private String displayName;
    private String userUID;
    private int seasonListSize;

    public CompetitorResponse(Competitor competitor) {
        this.setCompetitorId(competitor.getCompetitorId());
        this.setDisplayName(competitor.getDisplayName());
        this.setUserUID(competitor.getUserUID());
        this.setSeasonListSize(competitor.getSeasonListSize());

        setBaseInfo(competitor.getBaseInfo());
    }

}
