package backendDAM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import claseDAM.Board;
import claseDAM.Member;
import claseDAM.Subtask;
import claseDAM.Task;
import claseDAM.TaskList;
import claseDAM.UserRole;

public class BackendMember {

	private Member member = new Member();
	private EntityManager em;
	private List<Member> lstMember = new ArrayList<>();
	

	public BackendMember() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstMember();
	}

	@SuppressWarnings("unchecked")
	public void initLstMember() {
		lstMember.clear();
		lstMember = em.createQuery("Select m from Member m").getResultList();
	}

	

	public void addMember() {
		this.member = new Member();
	}

	public void saveMemberBD(Member t) {
		initLstMember();

		em.getTransaction().begin();
		if (this.em.contains(t)) {
			this.em.merge(t);

		} else {
			this.em.persist(t);
			System.out.println("Saving Member..." + t.getMember_name());
		}
		em.getTransaction().commit();

		initLstMember();

	}

	public void deleteMemberByName(String n) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master))
		{
		initLstMember();
		List<Member>list= new ArrayList<>();
		list=getLstMember();
		for (Member t : list) {

			if (t.getMember_name().equals(n)) {
				deleteMember(t);
			
			}
		}

	}else
		System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master");
	}

	private void deleteMember(Member t) {
	

		this.member = t;
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		
		System.out.println("A fost sters din db membrul " + t.getMember_name());

	}
	
	public void setRolMember(Member m, UserRole rol) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master))
		{
			em.getTransaction().begin();
			m.setUserRole(rol);
			this.em.merge(m);
			em.getTransaction().commit();
			System.out.println("Roluri Modificare cu succes");
		}else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master");
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Member> getLstMember() {
		return lstMember;
	}

	public void setLstMember(List<Member> lstMember) {
		this.lstMember = lstMember;
	}

	




}
