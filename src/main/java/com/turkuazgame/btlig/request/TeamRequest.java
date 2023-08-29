package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsLeague;
import com.turkuazgame.btlig.annotation.ExistsNation;
import com.turkuazgame.btlig.entity.League;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeamRequest extends BaseRequest implements IRequest {

    private Long teamId;
    @NotNull
    @NotEmpty
    private String teamName;
    @NotNull
    @NotEmpty
    @ExistsNation
    private String teamNation;
    @NotNull
    @ExistsLeague
    private Long leagueId;

    @JsonIgnore
    private League league;

    @Override
    public void setId(Long id) {
        this.teamId = id;
    }

}
