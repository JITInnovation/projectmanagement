package com.projectmanagement.service;

import com.projectmanagement.model.Project;
import com.projectmanagement.model.TimeSheet;
import com.projectmanagement.model.ProjectStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportsService {
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private TimesheetService timesheetService;

    public List<Project> getProjectHealthReport() {
        List<Project> projects = projectService.getAllProjects();
        return projectService.getProjectHealthReport(projects);
    }

    public Map<String, Object> getResourceUtilizationReport() {
        List<TimeSheet> timesheets = timesheetService.getMyTimesheets();
        return projectService.calculateResourceUtilization(timesheets);
    }

    public List<Project> getProjectBurndownReport(String projectId, LocalDate startDate, LocalDate endDate) {
        return projectService.getProjectBurndown(projectId, startDate, endDate);
    }

    public List<TimeSheet> getTimesheetSummaryReport(LocalDate startDate, LocalDate endDate) {
        return timesheetService.findByDateRange(startDate, endDate);
    }

    public Map<String, Object> generateProjectBurndownReport(String projectId, LocalDate startDate, LocalDate endDate) {
        Map<String, Object> report = new HashMap<>();
        
        // Get project
        Project project = projectService.getProject(projectId);
        
        // Get timesheets
        List<TimeSheet> timesheets = timesheetService.findByDateRange(startDate, endDate);
        
        // Calculate metrics
        double totalHours = timesheets.stream()
                .mapToDouble(TimeSheet::getHoursWorked)
                .sum();
        
        int uniqueDays = (int) timesheets.stream()
                .map(TimeSheet::getDate)
                .distinct()
                .count();
        
        double avgHoursPerDay = uniqueDays > 0 ? totalHours / uniqueDays : 0;
        
        // Calculate progress
        double plannedHours = project.getRequiredDevelopers() * 8; // Assuming 8 hours per developer per day
        double progress = (totalHours / plannedHours) * 100;
        
        report.put("projectId", projectId);
        report.put("totalHours", totalHours);
        report.put("averageHoursPerDay", avgHoursPerDay);
        report.put("uniqueDays", uniqueDays);
        report.put("progressPercentage", progress);
        report.put("timesheets", timesheets);
        
        return report;
    }

    public Map<String, Object> generateResourceUtilizationReport() {
        Map<String, Object> report = new HashMap<>();
        
        // Get all timesheets
        List<TimeSheet> timesheets = timesheetService.getMyTimesheets();
        
        // Calculate total hours
        double totalHours = timesheets.stream()
                .mapToDouble(TimeSheet::getHoursWorked)
                .sum();
        
        // Calculate average hours per day
        int uniqueDays = (int) timesheets.stream()
                .map(TimeSheet::getDate)
                .distinct()
                .count();
        
        double avgHoursPerDay = uniqueDays > 0 ? totalHours / uniqueDays : 0;
        
        // Calculate utilization percentage
        double plannedHours = uniqueDays * 8; // Assuming 8 hours per day
        double utilization = (totalHours / plannedHours) * 100;
        
        report.put("totalHours", totalHours);
        report.put("averageHoursPerDay", avgHoursPerDay);
        report.put("uniqueDays", uniqueDays);
        report.put("utilizationPercentage", utilization);
        
        return report;
    }

    public Map<String, Object> generateProjectHealthReport() {
        Map<String, Object> report = new HashMap<>();
        
        // Get all projects
        List<Project> projects = projectService.getAllProjects();
        
        // Group projects by status
        Map<ProjectStatus, Long> statusCount = projects.stream()
                .collect(Collectors.groupingBy(Project::getStatus, Collectors.counting()));
        
        // Calculate overall health
        double totalProjects = projects.size();
        double healthyProjects = statusCount.getOrDefault(ProjectStatus.HEALTHY, 0L);
        double healthPercentage = (healthyProjects / totalProjects) * 100;
        
        report.put("totalProjects", totalProjects);
        report.put("healthyProjects", healthyProjects);
        report.put("healthPercentage", healthPercentage);
        report.put("statusCount", statusCount);
        
        return report;
    }

    public Map<String, Object> generateTimesheetSummaryReport(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> report = new HashMap<>();
        
        // Get timesheets
        List<TimeSheet> timesheets = timesheetService.findByDateRange(startDate, endDate);
        
        // Calculate metrics
        double totalHours = timesheets.stream()
                .mapToDouble(TimeSheet::getHoursWorked)
                .sum();
        
        int uniqueDays = (int) timesheets.stream()
                .map(TimeSheet::getDate)
                .distinct()
                .count();
        
        double avgHoursPerDay = uniqueDays > 0 ? totalHours / uniqueDays : 0;
        
        report.put("totalHours", totalHours);
        report.put("averageHoursPerDay", avgHoursPerDay);
        report.put("uniqueDays", uniqueDays);
        report.put("timesheets", timesheets);
        
        return report;
    }
}
