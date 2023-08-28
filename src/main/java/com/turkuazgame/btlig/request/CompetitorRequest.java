package com.turkuazgame.btlig.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorRequest extends BaseRequest implements IRequest {

    public Long competitorId;
    public String displayName;
    public String userUID;

    @Override
    public void setId(Long id) {
        this.competitorId = id;
    }

}
