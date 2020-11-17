package backendDAM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import claseDAM.Board;
import claseDAM.Subtask;
import claseDAM.Team;
import claseDAM.Task;
import claseDAM.TaskList;
import claseDAM.UserRole;

public class BackendTeam {

	private Team team = new Team();
	private EntityManager em;
	private List<Team> lstTeam = new ArrayList<>();
	

	public BackendTeam() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstTeam();
	}

	@SuppressWarnings("unchecked")
	public void initLstTeam() {
		lstTeam.clear();
		lstTeam = em.createQuery("Select m from Team m").getResultList();
	}

	public void addTeam() {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
		this.team = new Team();
		System.out.println("Echipa a fost creata");
		}
		else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master ");
       
	}



	public void saveTeamBD(Team t) {
		initLstTeam();
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
		em.getTransaction().begin();
		if (this.em.contains(t)) {
			this.em.merge(t);

		} else {
			this.em.persist(t);
			System.out.println("Saving team..." + t.getTeamName());
		}
		em.getTransaction().commit();

		initLstTeam();
		}else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master ");
	       
	}

	public void deleteTeamByName(String n) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
			
		initLstTeam();
		List<Team>list= new ArrayList<>();
		list=getLstTeam();
		for (Team t : getLstTeam()) {

			if (t.getTeamName().equals(n))
				deleteTeam(t);

		}
		}else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master ");
	}

	private void deleteTeam(Team t) {
	

		this.team = t;
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	
		System.out.println("A fost sters din db echipa " + t.getTeamName());

	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Team> getLstTeam() {
		return lstTeam;
	}

	public void setLstTeam(List<Team> lstTeam) {
		this.lstTeam = lstTeam;
	}

	
	





}
