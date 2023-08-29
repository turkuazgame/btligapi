package com.turkuazgame.btlig.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.entity.BaseInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseRequest {

    @NotNull
    @NotEmpty
    private String status;
    @NotNull
    @NotEmpty
    private String updatedBy;
    @NotNull
    @NotEmpty
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
