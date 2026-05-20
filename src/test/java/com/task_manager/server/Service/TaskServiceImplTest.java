package com.task_manager.server.Service;

import com.task_manager.server.DTOs.TaskRequestDTO;
import com.task_manager.server.DTOs.TaskResponseDTO;
import com.task_manager.server.Entity.Task;
import com.task_manager.server.Repository.TaskRepository;
import com.task_manager.server.Service.Impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;

    @BeforeEach
    void setUp() {

        task = Task.builder()
                .id(1L)
                .title("Learn Testing")
                .description("Mockito + JUnit")
                .completed(false)
                .build();
    }

    @Test
    void shouldCreateTaskSuccessfully() {

        TaskRequestDTO request = TaskRequestDTO.builder()
                .title("Learn Testing")
                .description("Mockito + JUnit")
                .build();

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        TaskResponseDTO response = taskService.createTask(request);

        assertNotNull(response);
        assertEquals("Learn Testing", response.getTitle());

        verify(taskRepository, times(1))
                .save(any(Task.class));
    }
}
