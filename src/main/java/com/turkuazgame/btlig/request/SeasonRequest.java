package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turkuazgame.btlig.annotation.ExistsSeasonType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.apache.bcel.ExceptionConstants;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class SeasonRequest extends BaseRequest implements IRequest {

    private Long seasonId;
    @NotNull
    @NotEmpty
    private String seasonCode;
    @NotNull
    @NotEmpty
    private String seasonName;
    @NotNull
    @NotEmpty
    @ExistsSeasonType
    private String seasonType;
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String startDate;
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String endDate;

    @Override
    public void setId(Long id) {
        this.seasonId = id;
    }
}
