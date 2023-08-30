package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsCompetitorSeason;
import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.Week;
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
    @ExistsWeek
    private Long weekId;

    @JsonIgnore
    private CompetitorSeason competitorSeason;

    @JsonIgnore
    private Week week;

    @Override
    public void setId(Long id) {
        this.competitorWeekId = id;
    }

}
