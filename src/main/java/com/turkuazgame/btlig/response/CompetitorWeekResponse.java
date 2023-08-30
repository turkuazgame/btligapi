package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.CompetitorWeek;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CompetitorWeekResponse extends BaseResponse implements IResponse {

    private Long competitorWeekId;
    private Long competitorSeasonId;
    private Long weekId;
    private double totalScore;
    private int matchListSize;

    public CompetitorWeekResponse(CompetitorWeek competitorWeek) {
        this.setCompetitorWeekId(competitorWeek.getCompetitorWeekId());
        this.setCompetitorSeasonId(competitorWeek.getCompetitorSeason().getCompetitorSeasonId());
        this.setWeekId(competitorWeek.getWeek().getWeekId());
        this.setTotalScore(competitorWeek.getTotalScore());
        this.setMatchListSize(competitorWeek.getMatches().size());

        setBaseInfo(competitorWeek.getBaseInfo());
    }
}
