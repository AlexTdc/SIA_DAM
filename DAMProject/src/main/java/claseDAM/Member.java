package claseDAM;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.ManyToMany;
import static javax.persistence.EnumType.STRING;
import org.eclipse.persistence.annotations.Convert;
import javax.persistence.Lob;
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idMember;
	private String member_name;
	private String activity_domanin;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@Enumerated(STRING)
	private UserRole userRole;
	
	@ManyToMany(mappedBy = "task_memberList")
	private List<Task> member_taskList= new ArrayList<>();
	
	@ManyToMany(mappedBy = "team_memberList")
	private List<Team>member_teamList= new ArrayList<>();
	
	public Member(String member_name, String activity_domanin, @NotNull String username, @NotNull String password,
			UserRole userRole) {
		super();
		this.member_name = member_name;
		this.activity_domanin = activity_domanin;
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	
	}
	public Member() {
		super();
	}
	public Integer getIdMember() {
		return idMember;
	}
	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getActivity_domanin() {
		return activity_domanin;
	}
	public void setActivity_domanin(String activity_domanin) {
		this.activity_domanin = activity_domanin;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public List<Task> getMember_taskList() {
		return member_taskList;
	}
	public void setMember_taskList(List<Task> member_taskList) {
		this.member_taskList = member_taskList;
	}
	public List<Team> getMember_teamList() {
		return member_teamList;
	}
	public void setMember_teamList(List<Team> member_teamList) {
		this.member_teamList = member_teamList;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMember == null) ? 0 : idMember.hashCode());
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
		Member other = (Member) obj;
		if (idMember == null) {
			if (other.idMember != null)
				return false;
		} else if (!idMember.equals(other.idMember))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Member [idMember=" + idMember + ", member_name=" + member_name + ", activity_domanin="
				+ activity_domanin + ", userRole=" + userRole + "]";
	}
	
	
	
}
