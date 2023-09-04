package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsCompetitorWeek;
import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.entity.Match;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorMatchRequest extends BaseRequest implements IRequest {

    private Long competitorMatchId;
    @NotNull
    @NotEmpty
    @ExistsCompetitorWeek
    private Long competitorWeekId;
    @NotNull
    @NotEmpty
    @ExistsMatch
    private Long matchId;

    @JsonIgnore
    private CompetitorWeek competitorWeek;

    @JsonIgnore
    private Match match;

    @Override
    public void setId(Long id) {
        this.competitorMatchId = id;
    }

}
