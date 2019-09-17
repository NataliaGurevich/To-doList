package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

  @Id@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String descriptionTask;
  private Integer priority;

  public Task(){}

  public Task(Integer id, String descriptionTask, Integer priority) {
    this.id = id;
    this.descriptionTask = descriptionTask;
    this.priority = priority;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescriptionTask() {
    return descriptionTask;
  }

  public void setDescriptionTask(String descriptionTask) {
    this.descriptionTask = descriptionTask;
  }

  public Integer getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", descriptionTask='" + descriptionTask + '\'' +
        ", priority=" + priority +
        '}';
  }

  public void setPriority(Integer priority) {
    this.priority = priority;

  }
}
