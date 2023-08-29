package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.annotation.ExistsRateType;
import com.turkuazgame.btlig.entity.RateType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RateRequest extends BaseRequest implements IRequest {

    private Long rateId;
    @NotNull
    @NotEmpty
    private String rateCode;
    @NotNull
    @NotEmpty
    private String rateName;
    @NotNull
    @NotEmpty
    @ExistsRateType
    private Long rateTypeId;

    @JsonIgnore
    private RateType rateType;

    @Override
    public void setId(Long id) {
        this.rateId = id;
    }

}
