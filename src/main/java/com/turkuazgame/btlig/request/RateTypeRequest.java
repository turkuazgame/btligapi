package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.annotation.ExistsResultAction;
import com.turkuazgame.btlig.annotation.ExistsResultPeriod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RateTypeRequest extends BaseRequest implements IRequest {

    private Long rateTypeId;
    @NotNull
    @NotEmpty
    private String rateTypeCode;
    @NotNull
    @NotEmpty
    private String rateTypeName;
    @NotNull
    @NotEmpty
    @ExistsResultAction
    private String resultAction;
    @NotNull
    @NotEmpty
    @ExistsResultPeriod
    private String resultPeriod;

    @Override
    public void setId(Long id) {
        this.rateTypeId = id;
    }
}
