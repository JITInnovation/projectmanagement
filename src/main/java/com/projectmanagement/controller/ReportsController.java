package com.projectmanagement.controller;

import com.projectmanagement.model.Project;
import com.projectmanagement.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    
    @Autowired
    private ReportsService reportsService;

    @GetMapping("/project-health")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'ADMIN')")
    public ResponseEntity<List<Project>> getProjectHealthReport() {
        return ResponseEntity.ok(reportsService.getProjectHealthReport());
    }

    @GetMapping("/resource-utilization")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getResourceUtilizationReport() {
        return ResponseEntity.ok(reportsService.getResourceUtilizationReport());
    }

    @GetMapping("/project-burndown/{projectId}/{startDate}/{endDate}")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getProjectBurndownReport(@PathVariable String projectId, 
                                                                      @PathVariable String startDate, 
                                                                      @PathVariable String endDate) {
        LocalDate parsedStartDate = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate parsedEndDate = endDate != null ? LocalDate.parse(endDate) : null;
        return ResponseEntity.ok(reportsService.generateProjectBurndownReport(projectId, parsedStartDate, parsedEndDate));
    }

    @GetMapping("/timesheet-summary/{startDate}/{endDate}")
    @PreAuthorize("hasAnyRole('PRODUCT_OWNER', 'ADMIN')")
    public ResponseEntity<Map<String, Object>> getTimesheetSummary(@PathVariable String startDate, @PathVariable String endDate) {
        LocalDate parsedStartDate = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate parsedEndDate = endDate != null ? LocalDate.parse(endDate) : null;
        return ResponseEntity.ok(reportsService.generateTimesheetSummaryReport(parsedStartDate, parsedEndDate));
    }
}
