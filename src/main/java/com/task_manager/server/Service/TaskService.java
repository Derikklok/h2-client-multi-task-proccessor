package com.task_manager.server.Service;


import com.task_manager.server.DTOs.TaskRequestDTO;
import com.task_manager.server.DTOs.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO request);

    List<TaskResponseDTO> getAllTasks();

    TaskResponseDTO getTaskById(Long id);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO request);

    void deleteTask(Long id);
}
