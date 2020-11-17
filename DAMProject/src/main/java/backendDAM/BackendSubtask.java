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
import claseDAM.UserRole;
import claseDAM.Subtask;

public class BackendSubtask {

	private Subtask subtask = new Subtask();
	private EntityManager em;
	private List<Subtask> lstSubtask = new ArrayList<>();
	

	public BackendSubtask() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstSubtask();
	}

	@SuppressWarnings("unchecked")
	public void initLstSubtask() {
		lstSubtask.clear();
		lstSubtask = em.createQuery("Select t from Subtask t").getResultList();
	}

	public void addSubtask() {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master) || BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader))
		{
		this.subtask = new Subtask();
		this.subtask.setSubtask_status("In Desfasurare");
		System.out.println("Permisiune  accordata.Subtask creat cu succes!");
	}else
		System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");
		
	}

	

	public void saveSubtaskBD(Subtask t) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master) || BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader))
		{
		initLstSubtask();

		em.getTransaction().begin();
		if (this.em.contains(t)) {
			this.em.merge(t);
         System.out.println("Saving subtask update" + t.getSubtask_name());
		} else {
			this.em.persist(t);
			System.out.println("Saving Subtask..." + t.getSubtask_name());
		}
		em.getTransaction().commit();

		initLstSubtask();
		}else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");
		
			
	}

	public void deleteSubtaskByName(String n) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master) || BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader))
		{
		initLstSubtask();
		List<Subtask>list= new ArrayList<>();
		list=getLstSubtask();

		for (Subtask t : list) {

			if (t.getSubtask_name().equals(n))
				deleteSubtask(t);

		}
		}
		else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");
			

	}

	private void deleteSubtask(Subtask t) {
		

		this.subtask = t;
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		
		System.out.println("A fost sters din db Subtask-ul " + t.getSubtask_name());

	}
	
	
	public void changeStatusSubtask(Subtask t) {
		t.setSubtask_status("Finalizat");
		em.getTransaction().begin();
			this.em.merge(t);
         System.out.println("Saving subtask update" + t.getSubtask_name());
		em.getTransaction().commit();
	}

	public Subtask getSubtask() {
		return subtask;
	}

	public void setSubtask(Subtask subtask) {
		this.subtask = subtask;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Subtask> getLstSubtask() {
		return lstSubtask;
	}

	public void setLstSubtask(List<Subtask> lstSubtask) {
		this.lstSubtask = lstSubtask;
	}




}
