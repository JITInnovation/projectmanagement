package com.projectmanagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String release;
    private LocalDate startDate;
    private LocalDate endDate;
    private int requiredDevelopers;
    private int requiredTesters;
    private String projectManager;
    private String description;
    private ProjectStatus status;
    private String healthStatus;
    
    public void setHealthStatus(ProjectStatus status) {
        this.healthStatus = status.getDisplayName();
    }
    private List<String> assignedResources;
    private double progress;
}
