package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.annotation.ExistsNation;
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
    @ExistsNation
    private String rateTypeNation;

    @Override
    public void setId(Long id) {
        this.rateTypeId = id;
    }
}
