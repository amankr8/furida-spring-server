package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.ProjectController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.entity.Project;
import com.furidaweb.server.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectControllerImpl implements ProjectController {

    private final ProjectService projectService;

    @Override
    public ResponseEntity<?> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<?> getProject(int id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @Override
    public ResponseEntity<?> addProject(Project project) {
        Project newProject = projectService.addProject(project);
        return ResponseEntity.ok(newProject);
    }

    @Override
    public ResponseEntity<?> updateProject(int id, Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return ResponseEntity.ok(updatedProject);
    }

    @Override
    public ResponseEntity<?> deleteProject(int id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "Project deleted successfully!"));
    }

    @Override
    public ResponseEntity<?> deleteAllProjects() {
        projectService.deleteAllProjects();
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "All Projects deleted successfully!"));
    }
}
