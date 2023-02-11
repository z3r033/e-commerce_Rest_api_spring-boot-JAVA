/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hayatbackend.io.entity.ProjectEntity;
import com.example.hayatbackend.io.entity.CategoryEntity;
import com.example.hayatbackend.io.entity.UserEntity;
import com.example.hayatbackend.io.repository.ProjectRepository;
import com.example.hayatbackend.io.repository.CategoryRepository;
import com.example.hayatbackend.io.repository.UserRepository;
import com.example.hayatbackend.service.ProjectService;
import com.example.hayatbackend.shared.dto.ProjectDto;

/**
 *
 * @author hayat
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectRepository projectRepository; 
      @Autowired
    CategoryRepository tacheRepository; 
    @Autowired
    UserRepository userRepository; 
    @Transactional
    @Override
    public ProjectDto ajouterProject(ProjectDto projectDto) {
        ProjectEntity  projectEntity = new ProjectEntity();
            
            UserEntity creator_id = userRepository.findByUserId(projectDto.getCreator_id());
            BeanUtils.copyProperties(projectDto, projectEntity);
            projectEntity.setCreator(creator_id);
       
           ProjectEntity storedProjectDetail = projectRepository.save(projectEntity);
           creator_id.addProject(storedProjectDetail);
             
             userRepository.save(creator_id);
            ProjectDto returnValue = new ProjectDto();
            BeanUtils.copyProperties(storedProjectDetail, returnValue);
            returnValue.setCreator_id(projectDto.getCreator_id());
            return returnValue ;
    }

    @Override
    public List<ProjectDto> getProjectsByUser(String creator_id) {
         List <ProjectEntity>projects = projectRepository.fetchProjectsById(creator_id);
  
        List <ProjectDto> returnedValue = new ArrayList<>();    
        for(ProjectEntity projectentity : projects ) {
         
            ProjectDto projectdto = new ProjectDto();
            BeanUtils.copyProperties(projectentity, projectdto);
            projectdto.setCreator_id(creator_id);
            returnedValue.add(projectdto);
     }
               return returnedValue;
    }

    @Override
    public List<ProjectDto> getProjectAll() {
     return null; 
    }

    @Override
    public String ajouterUserToProject(String project_id, String user_id) {
     return "ok";
    }
    @Transactional
    @Override
    public void deleteProject(Long id) {
        ProjectEntity projectEntity = projectRepository.findProjectById(id);
        UserEntity user = userRepository.findByUserId(projectEntity.getCreator().getUserId());
        user.removeProject(projectEntity);
        userRepository.save(user);
        projectRepository.delete(projectEntity);
    }
    
    @Transactional
    @Override
    public String deleteProjectToUser(Long project_id, Long tache_id) {
  
     ProjectEntity project = projectRepository.findProjectById(project_id);
        CategoryEntity tache = tacheRepository.findProjectById(tache_id);
     project.removeTache(tache);
     
     projectRepository.save(project);
         
         return "cet tache a ete supprimer";
    }

	@Override
	public void changeProjet(String projet_id, String status) {
		// TODO Auto-generated method stub
		ProjectEntity project = projectRepository.findProjectById(Long.valueOf(projet_id));
		project.setStatus(status);
		projectRepository.save(project);
		
	}

  
    
}
