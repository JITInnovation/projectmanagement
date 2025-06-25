package com.projectmanagement.repository;

import com.projectmanagement.model.TimeSheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends MongoRepository<TimeSheet, String> {
    List<TimeSheet> findByUserId(String userId);
    List<TimeSheet> findByProjectId(String projectId);
    List<TimeSheet> findByDate(LocalDate date);
    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<TimeSheet> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
