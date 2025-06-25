package com.projectmanagement.controller;

import com.projectmanagement.model.TimeSheet;
import com.projectmanagement.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {
    
    @Autowired
    private TimesheetService timesheetService;

    @GetMapping
    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER')")
    public ResponseEntity<List<TimeSheet>> getMyTimesheets() {
        return ResponseEntity.ok(timesheetService.getMyTimesheets());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER')")
    public ResponseEntity<TimeSheet> createTimesheet(@RequestBody TimeSheet timesheet) {
        return ResponseEntity.ok(timesheetService.createTimesheet(timesheet));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DEVELOPER', 'TESTER')")
    public ResponseEntity<TimeSheet> updateTimesheet(@PathVariable String id, @RequestBody TimeSheet timesheet) {
        return ResponseEntity.ok(timesheetService.updateTimesheet(id, timesheet));
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<List<TimeSheet>> getProjectTimesheets(@PathVariable String projectId) {
        return ResponseEntity.ok(timesheetService.getProjectTimesheets(projectId));
    }

    @GetMapping("/daily/{date}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<List<TimeSheet>> getDailyTimesheets(@PathVariable String date) {
        return ResponseEntity.ok(timesheetService.getDailyTimesheets(LocalDate.parse(date)));
    }
}
