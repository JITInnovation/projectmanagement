package com.projectmanagement.service;

import com.projectmanagement.model.TimeSheet;
import com.projectmanagement.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {
    
    @Autowired
    private TimesheetRepository timesheetRepository;

    public List<TimeSheet> getMyTimesheets() {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        return timesheetRepository.findByUserId(currentUserId);
    }

    public List<TimeSheet> getProjectTimesheets(String projectId) {
        return timesheetRepository.findByProjectId(projectId);
    }

    public List<TimeSheet> getDailyTimesheets(LocalDate date) {
        return timesheetRepository.findByDate(date);
    }

    public List<TimeSheet> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return timesheetRepository.findByDateBetween(startDate, endDate);
    }

    public TimeSheet createTimesheet(TimeSheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public TimeSheet updateTimesheet(String id, TimeSheet timesheetDetails) {
        Optional<TimeSheet> optionalTimesheet = timesheetRepository.findById(id);
        if (optionalTimesheet.isPresent()) {
            TimeSheet timesheet = optionalTimesheet.get();
            timesheet.setProjectId(timesheetDetails.getProjectId());
            timesheet.setUserId(timesheetDetails.getUserId());
            timesheet.setDate(timesheetDetails.getDate());
            timesheet.setHoursWorked(timesheetDetails.getHoursWorked());
            timesheet.setTaskDescription(timesheetDetails.getTaskDescription());
            timesheet.setStatus(timesheetDetails.getStatus());
            return timesheetRepository.save(timesheet);
        }
        throw new RuntimeException("Timesheet not found");
    }

    public void deleteTimesheet(String id) {
        timesheetRepository.deleteById(id);
    }
}
