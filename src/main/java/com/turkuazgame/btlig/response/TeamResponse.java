package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Team;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TeamResponse extends BaseResponse implements IResponse{

    private Long teamId;
    private String teamCode;
    private String teamName;
    private String teamNation;
    private Long leagueId;

    public TeamResponse(Team team) {
        this.setTeamId(team.getTeamId());
        this.setTeamName(team.getTeamName());
        this.setTeamNation(team.getTeamNation().toString());
        this.setLeagueId(team.getLeagueId());

        this.setStatus(team.getBaseInfo().getStatus());
        this.setCreatedBy(team.getBaseInfo().getCreatedBy());
        this.setCreateDate(team.getBaseInfo().getCreateDate());
        this.setUpdatedBy(team.getBaseInfo().getUpdatedBy());
        this.setUpdatedFrom(team.getBaseInfo().getUpdatedFrom());
        this.setUpdateDate(team.getBaseInfo().getUpdateDate());
    }

}

