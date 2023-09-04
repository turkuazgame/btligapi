package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.annotation.ExistsRate;
import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.Rate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchRateRequest extends BaseRequest implements IRequest {

    private Long matchRateId;
    @NotNull
    @NotEmpty
    @ExistsMatch
    private Long matchId;
    @NotNull
    @NotEmpty
    @ExistsRate
    private Long rateId;
    @NotNull
    @NotEmpty
    private double rateValue;

    @JsonIgnore
    private Match match;

    @JsonIgnore
    private Rate rate;

    @Override
    public void setId(Long id) {
        this.matchRateId = id;
    }

}
