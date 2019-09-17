package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import main.model.Task;

public class Storage {

  private static int currentId = 4;
  private static HashMap<Integer, Task> tasks = new HashMap<Integer, Task>(){{
    put(1, new Task(1,"read a book", 7));
    put(2, new Task(2,"work at home", 5));
    put(3, new Task(3,"work at office", 1));
  }};


  public static List<Task> getAllTasks(){
    ArrayList<Task> tasksList = new ArrayList<>();
    tasksList.addAll(tasks.values());
    return tasksList;
  }

  public static int addTask(Task task){
    int id = currentId++;
    task.setId(id);
    tasks.put(id, task);
    return id;
  }

  public static Task getTask(int taskId){
    if(tasks.containsKey(taskId)){
      return tasks.get(taskId);
    }
    return null;
  }

  public static Task deleteTask(int taskId){
    if(tasks.containsKey(taskId)){
      return tasks.remove(taskId);
    }
    return null;
  }
}