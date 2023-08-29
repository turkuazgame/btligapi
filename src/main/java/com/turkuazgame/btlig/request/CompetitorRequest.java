package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.annotation.ExistsFirebaseUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompetitorRequest extends BaseRequest implements IRequest {

    public Long competitorId;
    @NotNull
    @NotEmpty
    public String displayName;
    @NotNull
    @NotEmpty
    @ExistsFirebaseUser
    public String userUID;

    @Override
    public void setId(Long id) {
        this.competitorId = id;
    }

}
