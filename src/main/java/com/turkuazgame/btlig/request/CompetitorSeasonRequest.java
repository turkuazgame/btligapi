package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsCompetitor;
import com.turkuazgame.btlig.annotation.ExistsNation;
import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.Season;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.checkerframework.common.value.qual.DoubleVal;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorSeasonRequest extends BaseRequest implements IRequest {

    private Long competitorSeasonId;
    @NotNull
    @NotEmpty
    @ExistsCompetitor
    private Long competitorId;
    @NotNull
    @NotEmpty
    @ExistsSeason
    private Long seasonId;
    //private double totalScore;

    @JsonIgnore
    private Competitor competitor;

    @JsonIgnore
    private Season season;

    @Override
    public void setId(Long id) {
        this.competitorSeasonId = id;
    }

}
