package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CompetitorSeasonResponse extends BaseResponse implements IResponse {

    private Long competitorSeasonId;
    private Long competitorId;
    private Long seasonId;
    private double totalScore;
    private int weekListSize;

    public CompetitorSeasonResponse(CompetitorSeason competitorSeason) {
        this.setCompetitorSeasonId(competitorSeason.getCompetitorSeasonId());
        this.setCompetitorId(competitorSeason.getCompetitorId());
        this.setSeasonId(competitorSeason.getSeasonId());
        this.setTotalScore(competitorSeason.getTotalScore());
        this.setWeekListSize(competitorSeason.getWeekListSize());

        setBaseInfo(competitorSeason.getBaseInfo());
    }
}
