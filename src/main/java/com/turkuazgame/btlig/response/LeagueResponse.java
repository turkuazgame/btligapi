package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.League;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LeagueResponse extends BaseResponse implements IResponse{

    private Long leagueId;
    private String leagueCode;
    private String leagueName;
    private String leagueNation;
    private int teamListSize;

    public LeagueResponse(League league) {
        this.setLeagueId(league.getLeagueId());
        this.setLeagueCode(league.getLeagueCode());
        this.setLeagueName(league.getLeagueName());
        this.setLeagueNation(league.getLeagueNation().toString());
        this.setTeamListSize(league.getTeamListSize());

        this.setStatus(league.getBaseInfo().getStatus());
        this.setCreatedBy(league.getBaseInfo().getCreatedBy());
        this.setCreateDate(league.getBaseInfo().getCreateDate());
        this.setUpdatedBy(league.getBaseInfo().getUpdatedBy());
        this.setUpdatedFrom(league.getBaseInfo().getUpdatedFrom());
        this.setUpdateDate(league.getBaseInfo().getUpdateDate());
    }

}

