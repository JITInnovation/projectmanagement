package com.projectmanagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "timesheets")
public class TimeSheet {
    @Id
    private String id;
    private String projectId;
    private String userId;
    private LocalDate date;
    private double hoursWorked;
    private String taskDescription;
    private String status;

}
