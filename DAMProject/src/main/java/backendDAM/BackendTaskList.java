package backendDAM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.platform.database.SybasePlatform;

import claseDAM.Board;
import claseDAM.Member;
import claseDAM.Subtask;
import claseDAM.Task;
import claseDAM.TaskList;
import claseDAM.UserRole;

public class BackendTaskList {

	private TaskList taskList = new TaskList();
	private EntityManager em;
	private List<TaskList> lstTaskList = new ArrayList<>();
	private List<TaskList> lstAddTaskList = new ArrayList<>();

	public BackendTaskList() {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAM");
		em = emf.createEntityManager();
		initLstTaskList();
	}

	@SuppressWarnings("unchecked")
	public void initLstTaskList() {
		lstTaskList.clear();
		lstTaskList = em.createQuery("Select t from TaskList t").getResultList();
	}

	public void addTaskList() {

		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
			this.taskList = new TaskList();
			this.taskList.setTaskListStatus("In Desfasurare");
			this.taskList.setStartDate_taskList(Calendar.getInstance().getTime());
			System.out.println("Persiune SCRUM Master accordata.TaskList creat cu succes!");
		} else
			System.out.println("Permisiune adaugare TaskList respinsa");
	}

	public void saveTaskListBD(TaskList t) {
		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
			initLstTaskList();

			em.getTransaction().begin();
			if (this.em.contains(t)) {
				this.em.merge(t);
				System.out.println("Saving Updates TaskList BD..." + t.getTaskListName());
			} else {
				this.em.persist(t);
				System.out.println("Saving TaskList BD..." + t.getTaskListName());
			}
			em.getTransaction().commit();

			initLstTaskList();
		} else
			System.out.println("Permisiune adaugare TaskList BD respinsa");

	}

	public void deletetaskListByName(String n) {
		if (BackendLoginStatic.getMemberStatic().getUserRole().equals(UserRole.SCRUM_Master)) {
			initLstTaskList();
			List<TaskList> list = new ArrayList<>();
			list = getLstTaskList();
			for (TaskList t : list) {

				if (t.getTaskListName().equals(n))
					deleteTaskList(t);

			}

		} else
			System.out.println("Permisiune respinsa! Nu puteti sterge un TaskList! Nu aveti rolul de SCRUM Master");

	}

	private void deleteTaskList(TaskList t) {

		this.taskList = t;
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

		System.out.println("A fost sters din db taskList-ul " + t.getTaskListName());

	}

	public void changeStatusTaskList(TaskList t) {

		t.setTaskListStatus("Finalizat");
		em.getTransaction().begin();
		this.em.merge(t);
		System.out.println("Saving tasklist update" + t.getTaskListName());
		em.getTransaction().commit();

	}

	public void showAllTasks(TaskList t) {
		for (Task task : t.getTaskList_tasks()) {
			for (Member m : task.getTask_memberList()) {
				System.out.println("Task: " + task.getTaskName() + "-- Membru:" + m.getMember_name());
			}
			for (Subtask s : task.getTask_subtaskList()) {
				System.out.println(
						"Subtask: " + s.getSubtask_name() + " --- Membru:" + s.getSubtask_member().getMember_name());
			}
		}
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<TaskList> getLstTaskList() {
		return lstTaskList;
	}

	public void setLstTaskList(List<TaskList> lstTaskList) {
		this.lstTaskList = lstTaskList;
	}

	public List<TaskList> getLstAddTaskList() {
		return lstAddTaskList;
	}

	public void setLstAddTaskList(List<TaskList> lstAddTaskList) {
		this.lstAddTaskList = lstAddTaskList;
	}

}
