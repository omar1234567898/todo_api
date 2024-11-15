package com.example.do_api.service;

import com.example.do_api.model.Task;
import com.example.do_api.model.User;
import com.example.do_api.repository.TaskRepository;
import com.example.do_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // Create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get tasks by user
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // Get tasks by company
    public List<Task> getTasksByCompany(Long companyId) {
        return taskRepository.findByCompanyId(companyId);
    }

    // Get all tasks (Super-User only)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get task by ID
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    // Update task
    public Task updateTask(Long taskId, Task updatedTask) {
        if (taskRepository.existsById(taskId)) {
            updatedTask.setId(taskId);
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    // Delete task
    public boolean deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }

    // Check if the user has access to a task
    public boolean hasAccessToTask(User user, Task task) {
        if ("Super-User".equals(user.getRole())) {
            return true;
        }
        if ("Company-Admin".equals(user.getRole())) {
            return user.getCompanyId().equals(task.getCompanyId()) || user.getId().equals(task.getUserId());
        }
        return user.getId().equals(task.getUserId());
    }

    // Find user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
