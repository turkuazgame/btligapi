package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.SeasonWeek;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SeasonWeekResponse extends BaseResponse implements IResponse{

    private Long seasonWeekId;
    private String seasonWeekCode;
    private String seasonWeekName;
    private Date startDate;
    private Date endDate;
    private Long seasonId;
    private int matchListSize;

    public SeasonWeekResponse(SeasonWeek seasonWeek) {
        this.setSeasonWeekId(seasonWeek.getSeasonWeekId());
        this.setSeasonWeekCode(seasonWeek.getSeasonWeekCode());
        this.setSeasonWeekName(seasonWeek.getSeasonWeekName());
        this.setStartDate(seasonWeek.getStartDate());
        this.setEndDate(seasonWeek.getEndDate());
        this.setSeasonId(seasonWeek.getSeason().getSeasonId());
        this.setMatchListSize(seasonWeek.getMatches().size());

        setBaseInfo(seasonWeek.getBaseInfo());
    }

}

