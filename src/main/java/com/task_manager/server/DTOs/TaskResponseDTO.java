package com.task_manager.server.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
