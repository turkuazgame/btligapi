package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.entity.SeasonType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class SeasonRequest extends BaseRequest implements IRequest {

    private Long seasonId;
    private String seasonCode;
    private String seasonName;
    private String seasonType;
    private Date startDate;
    private Date endDate;

    @Override
    public void setId(Long id) {
        this.seasonId = id;
    }
}
