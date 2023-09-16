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
    private String resultSubject;
    private String resultPeriod;
    private String resultAction;

    public RateTypeResponse(RateType rateType) {
        this.setRateTypeId(rateType.getRateTypeId());
        this.setRateTypeCode(rateType.getRateTypeCode());
        this.setRateTypeName(rateType.getRateTypeName());
        this.setResultSubject(rateType.getResultSubject().toString());
        this.setResultPeriod(rateType.getResultPeriod().toString());
        this.setResultAction(rateType.getResultAction().toString());

        setBaseInfo(rateType.getBaseInfo());
    }

}

