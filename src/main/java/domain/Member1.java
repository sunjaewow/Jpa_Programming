package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Member1 {
    public Member1(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void setTeam(Team team) {
        if (this.team != null) {//member1이 tema1이랑 양방향 연관관계일 때 그 후 연관관계를 team2로 수정했을 때 member1은
            //team2로 가르키지만 team1은 여전히 member1을 가르키고 있음 그래서 초기화하는 코드.
            this.team.getMember1s().remove(this);
        }
        this.team = team;
        team.getMember1s().add(this);
    }//연관관계 편의 메소드
}
