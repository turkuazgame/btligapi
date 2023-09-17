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
    private String resultAction;
    private String resultPeriod;

    public RateTypeResponse(RateType rateType) {
        this.setRateTypeId(rateType.getRateTypeId());
        this.setRateTypeCode(rateType.getRateTypeCode());
        this.setRateTypeName(rateType.getRateTypeName());
        this.setResultAction(rateType.getResultAction().toString());
        this.setResultPeriod(rateType.getResultPeriod().toString());

        setBaseInfo(rateType.getBaseInfo());
    }

}

