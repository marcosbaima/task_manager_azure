package com.example.service;

import com.example.Model.Task;
import com.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
      return taskRepository.findAll();
    }

    public Task createTask(Task task) {
      return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task taskDetails) {
      return taskRepository.findById(id).map(existingTask -> {
        existingTask.setName(taskDetails.getName());
        existingTask.setDescription(taskDetails.getDescription());
        return taskRepository.save(existingTask);
      });
    }

    public boolean deleteTask(Long id) {
      return taskRepository.findById(id).map(task -> {
        taskRepository.delete(task);
        return true;
      }).orElse(false);
    }
  }
