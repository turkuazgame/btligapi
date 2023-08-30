package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.annotation.ExistsTeam;
import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.entity.Week;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MatchRequest extends BaseRequest implements IRequest {

    private Long matchId;
    @NotNull
    @NotEmpty
    private String matchCode;
    @NotNull
    @NotEmpty
    private String matchName;
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private String matchDate;
    @NotNull
    @ExistsSeason
    private Long weekId;
    @NotNull
    @ExistsTeam
    private Long homeTeamId;
    @NotNull
    @ExistsTeam
    private Long awayTeamId;
    @NotNull
    private short homeTeamScore;
    @NotNull
    private short awayTeamScore;

    @JsonIgnore
    private Week week;
    @JsonIgnore
    private Team homeTeam;
    @JsonIgnore
    private Team awayTeam;

    @Override
    public void setId(Long id) {
        this.matchId = id;
    }

}
