package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="team")
public class Team implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="team_id")
    private long teamId;

    @Column(name="team_name")
    private String teamName;

    @Column(name="team_nation")
    private Nation teamNation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    private League league;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        Team newEntity = (Team) other;
        this.setTeamName(newEntity.getTeamName());
        this.setTeamNation(newEntity.getTeamNation());
        this.setLeague(newEntity.getLeague());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {

    }

}