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

public class BackendTask {

	private Task task = new Task();
	private EntityManager em;
	private List<Task> lstTask = new ArrayList<>();

	public BackendTask() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstTask();
	}

	@SuppressWarnings("unchecked")
	public void initLstTask() {
		lstTask.clear();
		lstTask = em.createQuery("Select t from Task t").getResultList();
	}

	public void addTask() {
		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)
				|| BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader)) {
			this.task = new Task();
			this.task.setTaskStatus("In Desfasurare");
			System.out.println("Permisiune  accordata.Task creat cu succes!");

		} else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");
	}

	public void saveTaskBD(Task t) {
		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)
				|| BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader)) {

			initLstTask();

			em.getTransaction().begin();
			if (this.em.contains(t)) {
				this.em.merge(t);
				System.out.println("Saving task update: " + t.getTaskName());

			} else {
				this.em.persist(t);
				System.out.println("Saving Task..." + t.getTaskName());
			}
			em.getTransaction().commit();

			initLstTask();
		} else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");

	}

	public void deleteTaskByName(String n) {
		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)
				|| BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.Team_Leader)) {
			initLstTask();
			List<Task> list = new ArrayList<>();
			list = getLstTask();
			for (Task t : list) {

				if (t.getTaskName().equals(n))
					deleteTask(t);

			}

		} else
			System.out.println("Permisiune  respinsa.Nu aveti rol de SCRUM Master sau TeamLeader!");

	}

	private void deleteTask(Task t) {

		this.task = t;
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

		System.out.println("A fost sters din db task-ul " + t.getTaskName());

	}

	public void changeStatusTask(Task t) {
		BackendSubtask bckS = new BackendSubtask();
		t.setTaskStatus("Finalizat");
		em.getTransaction().begin();
		this.em.merge(t);
		System.out.println("Saving task update" + t.getTaskName());
		em.getTransaction().commit();
		for (Subtask st : t.getTask_subtaskList()) {
			bckS.changeStatusSubtask(st);
		}
	}
	
	public void showMytasks(Member m) {
		
		for(Task t:lstTask)
		{
			for(Member member:t.getTask_memberList())
			{
				if(member.equals(m))
				{
					System.out.println(m.getMember_name()+"----Task :"+ t.getTaskName());
					for(Subtask s:t.getTask_subtaskList()) {
						if(member.equals(s.getSubtask_member()))
							System.out.println("Task :"+ t.getTaskName() + "--- Subtask"+ s.getSubtask_name());
					}
					
				}
			}
		}
		
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Task> getLstTask() {
		return lstTask;
	}

	public void setLstTask(List<Task> lstTask) {
		this.lstTask = lstTask;
	}

}
