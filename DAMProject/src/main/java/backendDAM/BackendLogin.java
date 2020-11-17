package backendDAM;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import claseDAM.Member;

public class BackendLogin {

	private  Member member= new Member();
	private EntityManager em;
	private String username;
	private String password;
	private List<Member>lstMember= new ArrayList<>();
	
	
	public BackendLogin() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em=emf.createEntityManager();
		initLstMember();
		
	}
	
	public void initLstMember() {
		lstMember= em.createQuery("Select m from Member m").getResultList();
	}

	
	public void loginMember(String u, String p) {
		initLstMember();
		for(Member m:lstMember) {
			
			if(m.getUsername().equals(u) && m.getPassword().equals(p)) {
				this.member=m;
				BackendLoginStatic.setMemberStatic(m);
				System.out.println("Logarea a fost realizata cu succes pentru membrul "+m.getMember_name()+ " --Rol: "+ m.getUserRole().toString());
				return;
			}
			
		}
		System.out.println("Logarea nu s-a putut realiza pentru acest username:"+this.username);
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

	public List<Member> getLstMember() {
		return lstMember;
	}

	public void setLstMember(List<Member> lstMember) {
		this.lstMember = lstMember;
	}
	
	
	
	
}
