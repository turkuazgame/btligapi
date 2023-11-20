package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.LeagueRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="league")
public class League implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="league_id")
    private Long leagueId;

    @Column(name="league_code", unique = true)
    private String leagueCode;

    @Column(name="league_name")
    private String leagueName;

    @Column(name="league_nation")
    @Enumerated(EnumType.STRING)
    private Nation leagueNation;

    @OneToMany
    @JoinColumn(name = "league_id")
    private List<Team> teams = new ArrayList<Team>();

    @Embedded
    private BaseInfo baseInfo;

    public int getTeamListSize() {
        if(this.teams!=null)
            return this.teams.size();
        else
            return 0;
    }

    @Override
    public void setFromOther(IEntity other) {
        League newEntity = (League) other;
        this.setLeagueCode(newEntity.getLeagueCode());
        this.setLeagueName(newEntity.getLeagueName());
        this.setLeagueNation(newEntity.getLeagueNation());
        this.setTeams(newEntity.getTeams());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        LeagueRequest req = (LeagueRequest) request;
        this.leagueId = req.getLeagueId();
        this.leagueCode = req.getLeagueCode();
        this.leagueName = req.getLeagueName();
        this.leagueNation = Nation.valueOf(req.getLeagueNation());
        this.baseInfo.setFromRequest(req);
    }

}
