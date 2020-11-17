package claseDAM;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Subtask {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer idSubtask;
	private String subtask_name;
	private String subtask_description;
	private String subtask_status;
	@ManyToOne
	private Member subtask_member;
	@ManyToOne
	private Task   subtask_task;
	
	

	public Subtask(Integer idSubtask, String subtask_name, String subtask_description, String subtask_status,
			Member subtask_member, Task subtask_task) {
		super();
		this.idSubtask = idSubtask;
		this.subtask_name = subtask_name;
		this.subtask_description = subtask_description;
		this.subtask_status = subtask_status;
		this.subtask_member = subtask_member;
		this.subtask_task = subtask_task;
	}

	public Subtask() {
		super();
	}

	public Integer getIdSubtask() {
		return idSubtask;
	}

	public void setIdSubtask(Integer idSubtask) {
		this.idSubtask = idSubtask;
	}

	public String getSubtask_name() {
		return subtask_name;
	}

	public void setSubtask_name(String subtask_name) {
		this.subtask_name = subtask_name;
	}

	public String getSubtask_description() {
		return subtask_description;
	}

	public void setSubtask_description(String subtask_description) {
		this.subtask_description = subtask_description;
	}

	public String getSubtask_status() {
		return subtask_status;
	}

	public void setSubtask_status(String subtask_status) {
		this.subtask_status = subtask_status;
	}

	public Member getSubtask_member() {
		return subtask_member;
	}

	public void setSubtask_member(Member subtask_member) {
		this.subtask_member = subtask_member;
	}

	public Task getSubtask_task() {
		return subtask_task;
	}

	public void setSubtask_task(Task subtask_task) {
		this.subtask_task = subtask_task;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSubtask == null) ? 0 : idSubtask.hashCode());
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
		Subtask other = (Subtask) obj;
		if (idSubtask == null) {
			if (other.idSubtask != null)
				return false;
		} else if (!idSubtask.equals(other.idSubtask))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subtask [idSubtask=" + idSubtask + ", subtask_name=" + subtask_name + ", subtask_description="
				+ subtask_description + "]";
	}
	
	
	
	
}
