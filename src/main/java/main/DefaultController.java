package main;

import java.util.ArrayList;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

  @Autowired
  private main.model.TaskRepository taskRepository;

  @RequestMapping("/")
  public String index(Model model) {
    Iterable<Task> taskIterable = taskRepository.findAll();
    ArrayList<Task> tasks = new ArrayList<>();
    taskIterable.forEach(tasks::add);

    model.addAttribute("tasks", tasks);
    model.addAttribute("tasksCount", tasks.size());
    return "index";
  }
}