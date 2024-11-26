package com.furidaweb.server.service.project;

import com.furidaweb.server.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(int id);

    Project addProject(Project project);

    Project updateProject(int id, Project project);

    void deleteProjectById(int id);

    void deleteAllProjects();
}
