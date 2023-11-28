package kz.nu.edu.vms.Controllers;

import kz.nu.edu.vms.DTO.TaskDTO;
import kz.nu.edu.vms.models.Task;
import kz.nu.edu.vms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping()
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.addTask(taskDTO);
        if (task == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task was deleted");
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.updateTask(taskDTO, id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        Task task = taskService.getTask(id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PostMapping("{id}/complete")
    public ResponseEntity<Task> completeStatus(@PathVariable int id) {
        Task task = taskService.completeStatus(id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }


}
