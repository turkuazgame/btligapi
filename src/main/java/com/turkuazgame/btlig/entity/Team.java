package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.TeamRequest;
import com.turkuazgame.btlig.request.SeasonRequest;
import com.turkuazgame.btlig.request.TeamRequest;
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
    @Enumerated(EnumType.STRING)
    private Nation teamNation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id", referencedColumnName = "league_id")
    private League league;

    @Embedded
    private BaseInfo baseInfo;

    public Long getLeagueId() {
        if(this.league!=null)
            return league.getLeagueId();
        else
            return 0L;
    }

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
        TeamRequest req = (TeamRequest) request;
        this.teamId = req.getTeamId();
        this.teamName = req.getTeamName();
        this.teamNation = Nation.valueOf(req.getTeamNation());
        this.league = req.getLeague();
        this.baseInfo.setFromRequest(req);
    }

}