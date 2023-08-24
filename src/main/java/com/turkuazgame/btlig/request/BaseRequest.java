package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.entity.BaseInfo;
import lombok.Data;

@Data
public class BaseRequest {

    private String status;
    private String updatedBy;
    private String updatedFrom;

    @JsonIgnore
    public void setBaseInfo(BaseInfo baseInfo) {
        this.status = baseInfo.getStatus();
        this.updatedBy = baseInfo.getUpdatedBy();
        this.updatedFrom = baseInfo.getUpdatedFrom();
    }

    @JsonIgnore
    public BaseInfo getBaseInfo() {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStatus(this.status);
        baseInfo.setUpdatedBy(this.updatedBy);
        baseInfo.setUpdatedFrom(this.updatedFrom);
        return baseInfo;
    }

}
