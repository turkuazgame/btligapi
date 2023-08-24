package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.Entity;

public interface IEntity {

    public BaseInfo getBaseInfo();
    public void setBaseInfo(BaseInfo baseInfo);
    public void setFromOther(IEntity other);
    public void setFromRequest(IRequest request);
}
