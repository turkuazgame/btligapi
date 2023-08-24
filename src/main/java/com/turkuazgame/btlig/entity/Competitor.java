package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.CompetitorRequest;
import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="competitor")
public class Competitor implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="competitor_id")
    private Long competitorId;

    @Column(name="display_name")
    private String displayName;

    // Firebase USER_UID
    @Column(name="user_uid")
    private String userUID;

    @OneToMany
    @JoinColumn(name = "competitor_id")
    private List<CompetitorSeason> seasons = new ArrayList<CompetitorSeason>();

    @Embedded
    protected BaseInfo baseInfo;

    public int getSeasonListSize() {
        if(this.seasons!=null)
            return this.seasons.size();
        else
            return 0;
    }

    public Competitor(CompetitorRequest request) {
        this.competitorId = request.getCompetitorId();
        this.displayName = request.displayName;
        this.userUID = request.userUID;
        this.baseInfo = new BaseInfo();
        this.baseInfo.setFromRequest(request);
    }

    @Override
    public void setFromOther(IEntity other) {
        Competitor newEntity = (Competitor) other;
        this.setDisplayName(newEntity.getDisplayName());
        this.setUserUID(newEntity.getUserUID());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        CompetitorRequest req = (CompetitorRequest) request;
        this.competitorId = req.getCompetitorId();
        this.displayName = req.displayName;
        this.userUID = req.userUID;
        this.baseInfo.setFromRequest(req);
    }

}
