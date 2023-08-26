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

        this.setStatus(rateType.getBaseInfo().getStatus());
        this.setCreatedBy(rateType.getBaseInfo().getCreatedBy());
        this.setCreateDate(rateType.getBaseInfo().getCreateDate());
        this.setUpdatedBy(rateType.getBaseInfo().getUpdatedBy());
        this.setUpdatedFrom(rateType.getBaseInfo().getUpdatedFrom());
        this.setUpdateDate(rateType.getBaseInfo().getUpdateDate());
    }

}

