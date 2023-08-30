package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Week;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WeekResponse extends BaseResponse implements IResponse{

    private Long weekId;
    private String weekCode;
    private String weekName;
    private Date startDate;
    private Date endDate;
    private Long seasonId;
    private int matchListSize;

    public WeekResponse(Week week) {
        this.setWeekId(week.getWeekId());
        this.setWeekCode(week.getWeekCode());
        this.setWeekName(week.getWeekName());
        this.setStartDate(week.getStartDate());
        this.setEndDate(week.getEndDate());
        this.setSeasonId(week.getSeason().getSeasonId());
        this.setMatchListSize(week.getMatches().size());

        setBaseInfo(week.getBaseInfo());
    }

}

