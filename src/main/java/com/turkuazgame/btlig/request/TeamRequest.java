package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.entity.League;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeamRequest extends BaseRequest implements IRequest {

    private Long teamId;
    private String teamName;
    private String teamNation;
    private Long leagueId;

    @JsonIgnore
    private League league;

    @Override
    public void setId(Long id) {
        this.teamId = id;
    }

}
