package com.turkuazgame.btlig.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LeagueRequest extends BaseRequest implements IRequest {

    private Long leagueId;
    private String leagueCode;
    private String leagueName;
    private String leagueNation;

    @Override
    public void setId(Long id) {
        this.leagueId = id;
    }

}
