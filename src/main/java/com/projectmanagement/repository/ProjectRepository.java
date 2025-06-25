package com.projectmanagement.repository;

import com.projectmanagement.model.Project;
import com.projectmanagement.model.ProjectStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    Optional<Project> findById(String id);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByStartDateBetweenAndEndDateBetween(LocalDate startDateStart, LocalDate startDateEnd, LocalDate endDateStart, LocalDate endDateEnd);
}
