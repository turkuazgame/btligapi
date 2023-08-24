package com.turkuazgame.btlig.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkuazgame.btlig.entity.BaseInfo;
import com.turkuazgame.btlig.entity.IEntity;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Locale;

@Data
public class BaseResponse {

    private String status;
    private Timestamp updateDate;
    private String updatedBy;
    private String updatedFrom;
    private Timestamp createDate;
    private String createdBy;

    @JsonIgnore
    public void setBaseInfo(BaseInfo baseInfo) {
        this.status = baseInfo.getStatus();
        this.updateDate = baseInfo.getUpdateDate();
        this.updatedBy = baseInfo.getUpdatedBy();
        this.updatedFrom = baseInfo.getUpdatedFrom();
        this.createDate = baseInfo.getCreateDate();
        this.createdBy = baseInfo.getCreatedBy();
    }

    @JsonIgnore
    public BaseInfo getBaseInfo() {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setStatus(this.status);
        baseInfo.setUpdateDate(this.updateDate);
        baseInfo.setUpdatedBy(this.updatedBy);
        baseInfo.setUpdatedFrom(this.updatedFrom);
        baseInfo.setCreateDate(this.createDate);
        baseInfo.setCreatedBy(this.createdBy);
        return baseInfo;
    }

}
