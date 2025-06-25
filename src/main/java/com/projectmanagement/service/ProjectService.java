package com.projectmanagement.service;

import com.projectmanagement.model.Project;
import com.projectmanagement.model.TimeSheet;
import com.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectHealthReport(List<Project> projects) {
        return projects.stream()
                .map(project -> {
                    project.setHealthStatus(project.getStatus());
                    return project;
                })
                .collect(Collectors.toList());
    }

    public List<Project> getProjectBurndown(String projectId, LocalDate startDate, LocalDate endDate) {

        
        // Get all projects with overlapping dates
        List<Project> overlappingProjects = projectRepository.findByStartDateBetweenAndEndDateBetween(
            startDate, endDate,
            startDate, endDate
        );
        
        return overlappingProjects;
    }

    public Map<String, Object> calculateResourceUtilization(List<TimeSheet> timesheets) {
        Map<String, Object> report = new HashMap<>();
        
        // Calculate total hours worked
        double totalHours = timesheets.stream()
                .mapToDouble(TimeSheet::getHoursWorked)
                .sum();
        
        // Calculate average hours per day
        int uniqueDays = (int) timesheets.stream()
                .map(ts -> ts.getDate())
                .distinct()
                .count();
        
        double avgHoursPerDay = uniqueDays > 0 ? totalHours / uniqueDays : 0;
        
        report.put("totalHours", totalHours);
        report.put("averageHoursPerDay", avgHoursPerDay);
        report.put("uniqueDays", uniqueDays);
        
        return report;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProject(String id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(String id, Project projectDetails) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            project.setStatus(projectDetails.getStatus());
            project.setStartDate(projectDetails.getStartDate());
            project.setEndDate(projectDetails.getEndDate());
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    public Project getProjectHealth(String projectId) {
        Project project = getProject(projectId);
        project.setHealthStatus(project.getStatus());
        return project;
    }
}
