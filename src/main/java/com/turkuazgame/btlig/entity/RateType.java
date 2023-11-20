package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.RateTypeRequest;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="rate_type")
public class RateType implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rate_type_id")
    private Long rateTypeId;

    @Column(name="rate_type_code", unique = true)
    private String rateTypeCode;

    @Column(name="rate_type_name")
    private String rateTypeName;

    @Column(name="result_action")
    @Enumerated(EnumType.STRING)
    private ResultAction resultAction;

    @Column(name="result_period")
    @Enumerated(EnumType.STRING)
    private ResultPeriod resultPeriod;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        RateType newEntity = (RateType) other;
        this.setRateTypeCode(newEntity.getRateTypeCode());
        this.setRateTypeName(newEntity.getRateTypeName());
        this.setResultAction(newEntity.getResultAction());
        this.setResultPeriod(newEntity.getResultPeriod());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        RateTypeRequest req = (RateTypeRequest) request;
        this.rateTypeId = req.getRateTypeId();
        this.rateTypeCode = req.getRateTypeCode();
        this.rateTypeName = req.getRateTypeName();
        this.resultAction = ResultAction.valueOf(req.getResultAction());
        this.resultPeriod = ResultPeriod.valueOf(req.getResultPeriod());

        this.baseInfo.setFromRequest(req);
    }

/*
MATCH_RESULT :
FINAL RESULT			RESULT_FINAL_1
						RESULT_FINAL_2
						RESULT_FINAL_0

HALF RESULT 			RESULT_HALF_1
						RESULT_HALF_2
						RESULT_HALF_0

HALF/FINAL RESULT		RESULT_HALF_FINAL_1_1
						RESULT_HALF_FINAL_1_0
						RESULT_HALF_FINAL_1_2
						RESULT_HALF_FINAL_0_1
						RESULT_HALF_FINAL_0_0
						RESULT_HALF_FINAL_0_2
						RESULT_HALF_FINAL_2_1
						RESULT_HALF_FINAL_2_0
						RESULT_HALF_FINAL_2_2

SCORE_UPDOWN :
HALF UP/DOWN  			UPDOWN_HALF_15_UP
						UPDOWN_HALF_15_DOWN

FINAL UP/DOWN	 		UPDOWN_FINAL_25_UP
						UPDOWN_FINAL_25_DOWN

SCORE_TOTAL :
TOTAL SCORE 			TOTAL_SCORE_0_1
						TOTAL_SCORE_2_3
						TOTAL_SCORE_4_5
						TOTAL_SCORE_6_100

SCORE_RECIPROCAL :
RECIPROCAL_SCORE		RECIPROCAL_SCORE_YES
						RECIPROCAL_SCORE_NO

MATCH_RESULT
SCORE_UPDOWN
SCORE_TOTAL
SCORE_RECIPROCAL
*/
}
