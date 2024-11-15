package com.example.do_api.controller;

import com.example.do_api.model.Task;
import com.example.do_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{userId}")
    public List<Task> getTasks(@PathVariable Long userId) {
        return taskService.getTasksByUser(userId);
    }

    @GetMapping("/company/{companyId}")
    public List<Task> getTasksByCompany(@PathVariable Long companyId) {
        return taskService.getTasksByCompany(companyId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/{taskId}")
    public boolean deleteTask(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
