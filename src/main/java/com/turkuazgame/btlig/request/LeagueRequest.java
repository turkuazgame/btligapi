package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.annotation.ExistsNation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LeagueRequest extends BaseRequest implements IRequest {

    private Long leagueId;
    @NotNull
    @NotEmpty
    private String leagueCode;
    @NotNull
    @NotEmpty
    private String leagueName;
    @NotNull
    @NotEmpty
    @ExistsNation
    private String leagueNation;

    @Override
    public void setId(Long id) {
        this.leagueId = id;
    }

}
