package claseDAM;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idteam;
	private String teamName;
	@OneToMany(cascade = ALL, mappedBy = "team_taskList")
	private List<TaskList>team_taskList= new ArrayList<>();
	
	@JoinTable(joinColumns = @JoinColumn(name = "member_teamList_idteam", referencedColumnName = "idteam"), inverseJoinColumns = @JoinColumn(name = "team_memberList_idMember", referencedColumnName = "idMember"))
	@ManyToMany
	private List<Member>team_memberList= new ArrayList<>();
	
	public Team(Integer idteam, String teamName, List<TaskList> team_taskList,
			List<Member> team_memberList) {
		super();
		this.idteam = idteam;
		this.teamName = teamName;
		this.team_taskList = team_taskList;
		this.team_memberList = team_memberList;
	}
	
	public Team() {
		super();
	}

	public Integer getIdteam() {
		return idteam;
	}

	public void setIdteam(Integer idteam) {
		this.idteam = idteam;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public List<TaskList> getTeam_taskList() {
		return team_taskList;
	}

	public void setTeam_taskList(List<TaskList> team_taskList) {
		this.team_taskList = team_taskList;
	}

	public List<Member> getTeam_memberList() {
		return team_memberList;
	}

	public void setTeam_memberList(List<Member> team_memberList) {
		this.team_memberList = team_memberList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idteam == null) ? 0 : idteam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (idteam == null) {
			if (other.idteam != null)
				return false;
		} else if (!idteam.equals(other.idteam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [idteam=" + idteam + ", teamName=" + teamName + "]";
	}
	
	
	
	
	
	
	
}
