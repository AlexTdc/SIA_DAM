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
import claseDAM.TaskList;
import claseDAM.UserRole;

public class BackendBoard {

	private Board board = new Board();
	private EntityManager em;
	private List<Board> lstBoard = new ArrayList<>();
	

	public BackendBoard() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstBoard();
	}

	@SuppressWarnings("unchecked")
	public void initLstBoard() {
		lstBoard.clear();
		lstBoard = em.createQuery("Select b from Board b").getResultList();
	}

	public void addBoard() {
	if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master))
	{
		this.board = new Board();
		this.board.setBoardStatus("In Desfasurare");
		this.board.setStartDate_board(Calendar.getInstance().getTime());
		System.out.println("Permisiune acceptata! Board-ul nou a fost creat");
	}else
		System.out.println("Permisiune respinsa! Nu aveti rolul de SCRUM Master");
	}
	
	
	public void saveBoardBD(Board b) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master))
		{
		initLstBoard();

		

			em.getTransaction().begin();
			if (this.em.contains(b)) {
				this.em.merge(b);
				System.out.println("Saving Board Updates..." + b.getBoardName());
			} else {
				this.em.persist(b);
				System.out.println("Saving Board..." + b.getBoardName());
			}
			em.getTransaction().commit();
		

		initLstBoard();

	}else
		System.out.println("Permisiune respinsa! Nu aveti rolul de SCRUM Master");
	}

	public void deleteBoardByName(String n) {
		if(BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master))
		{
		initLstBoard();
		List<Board>list= new ArrayList<>();
		list=getLstBoard();
		for (Board b : list) {

			if (b.getBoardName().equals(n))
				deleteBoard(b);
		}
		}
		else
			System.out.println("Permisiune respinsa! Nu puteti sterge un Board! Nu aveti rolul de SCRUM Master");
		

	}

	private void deleteBoard(Board b) {
		

		this.board = b;
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();
	
		System.out.println("A fost sters din db board-ul " + this.board.getBoardName());

	}

public void changeStatusBoard(Board t) {
		
		t.setBoardStatus("Finalizat");
		saveBoardBD(t);
	}
	
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Board> getLstBoard() {
		return lstBoard;
	}

	public void setLstBoard(List<Board> lstBoard) {
		this.lstBoard = lstBoard;
	}



}
