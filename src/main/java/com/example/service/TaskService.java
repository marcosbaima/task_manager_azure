package com.example.service;

import com.example.Model.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

  @Autowired

  private TaskRepository taskRepository;

  public Task createTask(Task task) {
    if (task.getName() == null || task.getName().trim().isEmpty()) {
        throw new IllegalArgumentException("Task name is required");
    }
    return taskRepository.save(task);
  }

  public List<Task> getAllTasks() {

    return taskRepository.findAll();

  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Task not found for id: " + id));
  }

  public boolean updateTask(Long id, Task task) {
    return taskRepository.findById(id)
      .map(existingTask -> {
          existingTask.setName(task.getName());
          existingTask.setDescription(task.getDescription());
          existingTask.setPriority(task.getPriority());
          existingTask.setStatus(task.getStatus());
          taskRepository.save(existingTask);
          return true;
      })
      .orElse(false);
  }

  public boolean deleteTask(Long id) {
    return taskRepository.findById(id)
      .map(task -> {
          taskRepository.delete(task);
          return true;
      })
      .orElse(false);
  }
}