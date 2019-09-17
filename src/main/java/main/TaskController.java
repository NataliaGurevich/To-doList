package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;
  @GetMapping("/tasks/")
  public List<Task> list(){
    Iterable<Task> taskIterable = taskRepository.findAll();
    ArrayList<Task> tasks = new ArrayList<>();
    taskIterable.forEach(tasks::add);
    return tasks;
  }

  @GetMapping("/tasks/{id}")
  public ResponseEntity get(@PathVariable int id){
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (!optionalTask.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
  }

  @PostMapping("/tasks/")
  public int add(Task task){
    Task newTask = taskRepository.save(task);
    return newTask.getId();
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity delete(@PathVariable int id){
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (!optionalTask.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    taskRepository.deleteById(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping("/tasks/{id}")
  public ResponseEntity put(@PathVariable int id, Task task){
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (!optionalTask.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    Task editTask = optionalTask.get();
    editTask.setDescriptionTask(task.getDescriptionTask());
    editTask.setPriority(task.getPriority());
    taskRepository.save(editTask);
    return new ResponseEntity(HttpStatus.OK);
  }
}
