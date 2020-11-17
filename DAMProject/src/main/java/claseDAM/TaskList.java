package claseDAM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.ManyToOne;
@Entity
public class TaskList {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idTaskList;
	private String taskListName;
	private String taskListStatus;
	@Temporal(TIMESTAMP)
	private Date startDate_taskList= new Date();
	@Temporal(TIMESTAMP)
	private Date endDate_TaskList =  new Date();
	@OneToMany(cascade = ALL, mappedBy = "task_taskList")
	private List<Task>taskList_tasks= new ArrayList<>();
	@ManyToOne
	private Board board_taskList;
	@ManyToOne
	private Team team_taskList;
	
	
	
	
	public TaskList() {
		super();
	}
	
	public TaskList(Integer idTaskList, String taskListName, String taskListStatus, Date startDate_taskList,
			Date endDate_TaskList, List<Task> taskList_tasks, Board board_taskList, Team team_taskList) {
		super();
		this.idTaskList = idTaskList;
		this.taskListName = taskListName;
		this.taskListStatus = taskListStatus;
		this.startDate_taskList = startDate_taskList;
		this.endDate_TaskList = endDate_TaskList;
		this.taskList_tasks = taskList_tasks;
		this.board_taskList = board_taskList;
		this.team_taskList = team_taskList;
	}
	public Integer getIdTaskList() {
		return idTaskList;
	}
	public void setIdTaskList(Integer idTaskList) {
		this.idTaskList = idTaskList;
	}
	public String getTaskListName() {
		return taskListName;
	}
	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}
	public String getTaskListStatus() {
		return taskListStatus;
	}
	public void setTaskListStatus(String taskListStatus) {
		this.taskListStatus = taskListStatus;
	}
	public Date getStartDate_taskList() {
		return startDate_taskList;
	}
	public void setStartDate_taskList(Date startDate_taskList) {
		this.startDate_taskList = startDate_taskList;
	}
	public Date getEndDate_TaskList() {
		return endDate_TaskList;
	}
	public void setEndDate_TaskList(Date endDate_TaskList) {
		this.endDate_TaskList = endDate_TaskList;
	}
	public List<Task> getTaskList_tasks() {
		return taskList_tasks;
	}
	public void setTaskList_tasks(List<Task> taskList_tasks) {
		this.taskList_tasks = taskList_tasks;
	}
	public Board getBoard_taskList() {
		return board_taskList;
	}
	public void setBoard_taskList(Board board_taskList) {
		this.board_taskList = board_taskList;
	}
	public Team getTeam_taskList() {
		return team_taskList;
	}
	public void setTeam_taskList(Team team_taskList) {
		this.team_taskList = team_taskList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTaskList == null) ? 0 : idTaskList.hashCode());
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
		TaskList other = (TaskList) obj;
		if (idTaskList == null) {
			if (other.idTaskList != null)
				return false;
		} else if (!idTaskList.equals(other.idTaskList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskList [idTaskList=" + idTaskList + ", taskListName=" + taskListName + ", taskListStatus="
				+ taskListStatus + ", startDate_taskList=" + startDate_taskList + ", endDate_TaskList="
				+ endDate_TaskList + "]";
	}
	
	
	
}
