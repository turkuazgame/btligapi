package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.entity.Season;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class WeekRequest extends BaseRequest implements IRequest {

    private Long weekId;
    @NotNull
    @NotEmpty
    private String weekCode;
    @NotNull
    @NotEmpty
    private String weekName;
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private String startDate;
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private String endDate;
    @NotNull
    @ExistsSeason
    private Long SeasonId;

    @JsonIgnore
    private Season season;

    @Override
    public void setId(Long id) {
        this.weekId = id;
    }

}
