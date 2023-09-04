package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsCompetitorMatch;
import com.turkuazgame.btlig.annotation.ExistsMatchRate;
import com.turkuazgame.btlig.entity.CompetitorMatch;
import com.turkuazgame.btlig.entity.MatchRate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorRateRequest extends BaseRequest implements IRequest {

    private Long competitorRateId;
    @NotNull
    @NotEmpty
    @ExistsCompetitorMatch
    private Long competitorMatchId;
    @NotNull
    @NotEmpty
    @ExistsMatchRate
    private Long matchRateId;

    @JsonIgnore
    private CompetitorMatch competitorMatch;

    @JsonIgnore
    private MatchRate matchRate;

    @Override
    public void setId(Long id) {
        this.competitorRateId = id;
    }

}
