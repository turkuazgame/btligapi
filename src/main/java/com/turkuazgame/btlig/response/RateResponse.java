package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.Rate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class RateResponse extends BaseResponse implements IResponse{

    private Long rateId;
    private String rateCode;
    private String rateName;
    private Long rateTypeId;

    public RateResponse(Rate rate) {
        this.setRateId(rate.getRateId());
        this.setRateCode(rate.getRateCode());
        this.setRateName(rate.getRateName());
        this.setRateTypeId(rate.getRateType().getRateTypeId());

        this.setStatus(rate.getBaseInfo().getStatus());
        this.setCreatedBy(rate.getBaseInfo().getCreatedBy());
        this.setCreateDate(rate.getBaseInfo().getCreateDate());
        this.setUpdatedBy(rate.getBaseInfo().getUpdatedBy());
        this.setUpdatedFrom(rate.getBaseInfo().getUpdatedFrom());
        this.setUpdateDate(rate.getBaseInfo().getUpdateDate());
    }

}

