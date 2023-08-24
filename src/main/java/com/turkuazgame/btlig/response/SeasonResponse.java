package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.IEntity;
import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.SeasonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;


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

        this.setStatus(season.getBaseInfo().getStatus());
        this.setCreatedBy(season.getBaseInfo().getCreatedBy());
        this.setCreateDate(season.getBaseInfo().getCreateDate());
        this.setUpdatedBy(season.getBaseInfo().getUpdatedBy());
        this.setUpdatedFrom(season.getBaseInfo().getUpdatedFrom());
        this.setUpdateDate(season.getBaseInfo().getUpdateDate());
    }

}

