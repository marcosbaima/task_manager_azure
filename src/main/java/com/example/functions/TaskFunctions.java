
package com.example.functions;

import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.ExecutionContext;
import com.example.service.TaskService;
import com.example.Model.Task;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskFunctions {

  private final TaskService taskService;

  public TaskFunctions(TaskService taskService) {
    this.taskService = taskService;
  }

  @FunctionName("createTask")
  public HttpResponseMessage createTask(
      @HttpTrigger(name = "req", methods = {HttpMethod.POST}, route = "tasks") HttpRequestMessage<Task> request,
      final ExecutionContext context) {
      
      // Obtém o corpo da requisição
      Task task = request.getBody();
      if (task != null) {
          taskService.createTask(task);
  
          return request.createResponseBuilder(HttpStatus.OK)
              .body("Task created successfully")
              .build();
      } else {
          return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
              .body("Invalid task data")
              .build();
      }
  }

  @FunctionName("getAllTasks")
  public HttpResponseMessage getAllTasks(
      @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "tasks") HttpRequestMessage<Void> request,
      final ExecutionContext context) {
    
      context.getLogger().info("Starting to retrieve all tasks.");
      
      List<Task> tasks = taskService.getAllTasks();
      
      if (tasks != null && !tasks.isEmpty()) {
        context.getLogger().info("Successfully retrieved " + tasks.size() + " tasks.");
        return request.createResponseBuilder(HttpStatus.OK)
            .body(tasks)
            .build();
      } else {
        context.getLogger().warning("No tasks found.");
        return request.createResponseBuilder(HttpStatus.NOT_FOUND)
            .body("No tasks found")
            .build();
      }
  }

  @FunctionName("getTaskById")
  public HttpResponseMessage getTaskById(
      @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "tasks/{id}") HttpRequestMessage<Void> request,
      @BindingName("id") String id,
      final ExecutionContext context) {
    
      context.getLogger().info("Retrieving task with id: " + id);
      
      Long taskId = Long.parseLong(id);
      Task task = taskService.getTaskById(taskId);
      
      if (task != null) {
        context.getLogger().info("Task found with id: " + id);
        return request.createResponseBuilder(HttpStatus.OK)
            .body(task)
            .build();
      } else {
        context.getLogger().warning("Task not found with id: " + id);
        return request.createResponseBuilder(HttpStatus.NOT_FOUND)
            .body("Task not found")
            .build();
      }
  }

  @FunctionName("updateTask")
  public HttpResponseMessage updateTask(
      @HttpTrigger(name = "req", methods = {HttpMethod.PUT}, route = "tasks/{id}") HttpRequestMessage<Task> request,
      @BindingName("id") String id,
      final ExecutionContext context) {
    
      context.getLogger().info("Updating task with id: " + id);
      
      Task task = request.getBody();
      if (task != null) {
          Long taskId = Long.parseLong(id);
          boolean updated = taskService.updateTask(taskId, task);
          if (updated) {
              context.getLogger().info("Task updated successfully with id: " + id);
              return request.createResponseBuilder(HttpStatus.OK)
                      .body("Task updated successfully")
                      .build();
          } else {
              context.getLogger().warning("Task update failed with id: " + id);
              return request.createResponseBuilder(HttpStatus.NOT_FOUND)
                      .body("Task not found for update")
                      .build();
          }
    } else {
        context.getLogger().warning("Invalid data received for task update.");
        return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                .body("Invalid task data")
                .build();
    }
  }

  @FunctionName("deleteTask")
  public HttpResponseMessage deleteTask(
      @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "tasks/{id}") HttpRequestMessage<Void> request,
      @BindingName("id") String id,
      final ExecutionContext context) {
  
      context.getLogger().info("Deleting task with id: " + id);
  
      Long taskId = Long.parseLong(id);
      boolean deleted = taskService.deleteTask(taskId);
  
      if (deleted) {
          context.getLogger().info("Task deleted successfully with id: " + id);
          return request.createResponseBuilder(HttpStatus.NO_CONTENT)
              .build();
      } else {
          context.getLogger().warning("Task not found for deletion with id: " + id);
          return request.createResponseBuilder(HttpStatus.NOT_FOUND)
              .body("Task not found")
              .build();
      }
  }  
}
