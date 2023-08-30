package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.RateType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RateTypeResponse extends BaseResponse implements IResponse{

    private Long rateTypeId;
    private String rateTypeCode;
    private String rateTypeName;

    public RateTypeResponse(RateType rateType) {
        this.setRateTypeId(rateType.getRateTypeId());
        this.setRateTypeCode(rateType.getRateTypeCode());
        this.setRateTypeName(rateType.getRateTypeName());

        setBaseInfo(rateType.getBaseInfo());
    }

}

