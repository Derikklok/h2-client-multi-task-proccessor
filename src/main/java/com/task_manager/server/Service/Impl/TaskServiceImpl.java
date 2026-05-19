package com.task_manager.server.Service.Impl;

import com.task_manager.server.DTOs.TaskRequestDTO;
import com.task_manager.server.DTOs.TaskResponseDTO;
import com.task_manager.server.Entity.Task;
import com.task_manager.server.Repository.TaskRepository;
import com.task_manager.server.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .build();
        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapToResponse(task);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(task);
    }

    private TaskResponseDTO mapToResponse(Task task) {

        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }
}
