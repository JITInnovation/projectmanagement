package com.projectmanagement.controller;

import com.projectmanagement.model.Project;
import com.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<Project> getProject(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<Project> updateProject(@PathVariable String id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health/{id}")
    @PreAuthorize("hasAnyRole('PROJECT_MANAGER', 'ADMIN')")
    public ResponseEntity<Project> getProjectHealth(@PathVariable String id) {
        return ResponseEntity.ok(projectService.getProjectHealth(id));
    }
}
