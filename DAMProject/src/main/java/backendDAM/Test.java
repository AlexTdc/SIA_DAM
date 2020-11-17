package backendDAM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import claseDAM.Board;
import claseDAM.Member;
import claseDAM.Subtask;
import claseDAM.Task;
import claseDAM.TaskList;
import claseDAM.Team;
import claseDAM.UserRole;

public class Test {

	public static void main(String[] args) {

		// 1. Populare baza de date
		//populateDB();

		// 2.Login cont SCRUM MASTER
		// loginScrumMaster();

		// 3. Adauga 2 membri + setare roluri ( Persission only for ScrumMaster)
		// setRolUsers();

		// 4. Creare teamNou
		// createTeam();

		// 5.Update task status - In desfasurare => Finalizat
		// updateTaskStatus(new BackendTask().getLstTask().get(0));

		//6.Arata toate task-urile + membrii acestora dintr-un TaskList
		//showAllTasks(new BackendTaskList().getLstTaskList().get(0));
		
		//7.Arata task-urile unui membru
		//showMyTasks(new BackendMember().getLstMember().get(0));
		
		//8.. Delete board //(scrum master)
		//deleteBoardByName("Board1");
	}

private static void showMyTasks(Member m) {
		
		loginScrumMaster();
		BackendTask bt= new BackendTask();
		bt.showMytasks(m);
	}
	
	private static void showAllTasks(TaskList t) {
		
		loginScrumMaster();
		BackendTaskList bt= new BackendTaskList();
		bt.showAllTasks(t);
	}
	
	private static void updateTaskStatus(Task t) {
		loginScrumMaster();
		BackendTask bckTask = new BackendTask();
		bckTask.changeStatusTask(t);

	}

	private static void createTeam() {
		loginScrumMaster();
		BackendTeam bt = new BackendTeam();
		bt.addTeam();
		bt.getTeam().setTeamName("Team DAM");
		bt.saveTeamBD(bt.getTeam());

	}

	private static void setRolUsers() {

		loginScrumMaster();

		Member m1 = new Member();
		m1.setMember_name("Ion");
		m1.setActivity_domanin("IT");
		m1.setUsername("Ion");
		m1.setPassword("ion");

		Member m2 = new Member();
		m2.setMember_name("Ionela");
		m2.setActivity_domanin("IT");
		m2.setUsername("Ionela");
		m2.setPassword("ionela");

		BackendMember bckMember = new BackendMember();
		bckMember.saveMemberBD(m1);
		bckMember.saveMemberBD(m2);
		bckMember.setRolMember(m1, UserRole.Team_Leader);
		bckMember.setRolMember(m2, UserRole.Team_Member);

	}

	private static void loginScrumMaster() {
		BackendLogin login = new BackendLogin();
		login.setUsername("Silviu");
		// login.setPassword("ssilviu");
		login.setPassword("silviu");
		login.loginMember(login.getUsername(), login.getPassword());
	}

	private static void deleteBoardByName(String s) {
		loginScrumMaster();
		BackendBoard bb = new BackendBoard();
		bb.deleteBoardByName(s);

	}

	private static void populateDB() {
		Board b = new Board();
		TaskList t = new TaskList();
		Task task = new Task();
		Subtask s = new Subtask();
		Subtask s1 = new Subtask();
		Member m = new Member();
		Member m1 = new Member();
		Team team = new Team();

		BackendBoard bb = new BackendBoard();
		BackendTaskList bt = new BackendTaskList();
		BackendTask bTask = new BackendTask();
		BackendSubtask bs = new BackendSubtask();
		BackendMember bm = new BackendMember();
		BackendTeam bTeam = new BackendTeam();
		BackendLogin login = new BackendLogin();

		// create members

		m.setMember_name("Silviu");
		m.setActivity_domanin("It");
		m.setUsername("Silviu");
		m.setPassword("silviu");
		m.setUserRole(UserRole.SCRUM_Master);

		m1.setMember_name("Alex");
		m1.setActivity_domanin("MK");
		m1.setUsername("Alex");
		m1.setPassword("alex");
		m1.setUserRole(UserRole.Team_Leader);

		// create board

		b.setBoardName("Board1");
		b.setBoardStatus("In Desfasurare");
		b.setStartDate_board(Calendar.getInstance().getTime());

		// create team

		team.setTeamName("BumBUm");
		team.getTeam_memberList().add(m);
		team.getTeam_memberList().add(m1);

		// create tasklist

		t.setTaskListName("TaskList1");
		t.setTaskListStatus("In desfasurare");
		t.setBoard_taskList(b);
		// t.getTaskList_tasks().add(task);
		t.setTeam_taskList(team);

		// create Task

		task.setTaskName("Task 1");
		task.setTaskDescription("Acesta e task1");
		task.setTaskStatus("Nefinalizat");
		task.setTask_taskList(t);
		task.getTask_memberList().add(m);
		task.getTask_memberList().add(m1);

		// create subtask

		s.setSubtask_name("Subtask1");
		s.setSubtask_status("Pe jumate facut");
		s.setSubtask_member(m);
		s.setSubtask_task(task);

		s1.setSubtask_name("Subtask2");
		s1.setSubtask_status("Pe jumate facut");
		s1.setSubtask_member(m1);
		s1.setSubtask_task(task);

		// add object in backend classes :))

		bm.saveMemberBD(m);
		bm.saveMemberBD(m1);

		// login baza de date
		login.setUsername("Silviu");
		login.setPassword("silviu");
		login.loginMember(login.getUsername(), login.getPassword());

		bb.saveBoardBD(b);

		bTeam.saveTeamBD(team);

		bt.saveTaskListBD(t);

		bTask.saveTaskBD(task);

		bs.saveSubtaskBD(s);
		bs.saveSubtaskBD(s1);

	}

}
