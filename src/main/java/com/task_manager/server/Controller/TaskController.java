package com.task_manager.server.Controller;

import com.task_manager.server.DTOs.TaskRequestDTO;
import com.task_manager.server.DTOs.TaskResponseDTO;
import com.task_manager.server.Service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO request
    ) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteTask(id);
    }
}
