package com.ppmtool.services;

import com.ppmtool.domain.Project;
import com.ppmtool.exceptions.ProjectIdException;
import com.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + " is already exist");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' does not exist");
        }
        return projectRepository.findByProjectIdentifier(projectId);
    }

    public List<Project> findAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        if (projectList.isEmpty()) {
            throw new ProjectIdException("Project is not found");
        }
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project is not  found with id '" + projectId.toUpperCase() + "'");
        }
        projectRepository.delete(project);
    }

}

