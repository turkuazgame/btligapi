package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Season;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SeasonResponse extends BaseResponse implements IResponse{

    private Long seasonId;
    private String seasonCode;
    private String seasonName;
    private String seasonType;
    private Date startDate;
    private Date endDate;
    private int weekListSize;

    public SeasonResponse(Season season) {
        this.setSeasonId(season.getSeasonId());
        this.setSeasonCode(season.getSeasonCode());
        this.setSeasonName(season.getSeasonName());
        this.setSeasonType(season.getSeasonType().toString());
        this.setStartDate(season.getStartDate());
        this.setEndDate(season.getEndDate());
        this.setWeekListSize(season.getWeekListSize());

        setBaseInfo(season.getBaseInfo());
    }

}

