package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsCompetitorSeason;
import com.turkuazgame.btlig.annotation.ExistsSeasonWeek;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.SeasonWeek;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorWeekRequest extends BaseRequest implements IRequest {

    private Long competitorWeekId;
    @NotNull
    @NotEmpty
    @ExistsCompetitorSeason
    private Long competitorSeasonId;
    @NotNull
    @NotEmpty
    @ExistsSeasonWeek
    private Long seasonWeekId;

    @JsonIgnore
    private CompetitorSeason competitorSeason;

    @JsonIgnore
    private SeasonWeek seasonWeek;

    @Override
    public void setId(Long id) {
        this.competitorWeekId = id;
    }

}
