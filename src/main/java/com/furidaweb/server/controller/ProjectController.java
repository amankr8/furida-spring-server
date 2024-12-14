package com.furidaweb.server.controller;

import com.furidaweb.server.entity.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/projects")
public interface ProjectController {

    @GetMapping
    ResponseEntity<?> getAllProjects();

    @GetMapping("/{id}")
    ResponseEntity<?> getProject(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> addProject(@RequestBody Project project);

    @PutMapping("/{id}")
    ResponseEntity<?> updateProject(@PathVariable int id, @RequestBody Project project);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProject(@PathVariable int id);

    @DeleteMapping
    ResponseEntity<?> deleteAllProjects();
}
