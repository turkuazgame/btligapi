package com.turkuazgame.btlig.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RateTypeRequest extends BaseRequest implements IRequest {

    private Long rateTypeId;
    private String rateTypeCode;
    private String rateTypeName;
    private String rateTypeNation;

    @Override
    public void setId(Long id) {
        this.rateTypeId = id;
    }
}
