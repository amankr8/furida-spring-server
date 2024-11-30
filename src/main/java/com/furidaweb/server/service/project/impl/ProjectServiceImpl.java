package com.furidaweb.server.service.project.impl;

import com.furidaweb.server.entity.Project;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.ProjectRepository;
import com.furidaweb.server.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(int id, Project project) {
        Project oldProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        oldProject.setName(project.getName());
        oldProject.setDesc(project.getDesc());
        oldProject.setAddress(project.getAddress());

        return projectRepository.save(oldProject);
    }

    @Override
    public void deleteProjectById(int id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }
}
