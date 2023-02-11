/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.ui.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hayatbackend.service.ProjectService;
import com.example.hayatbackend.shared.dto.ProjectDto;
import com.example.hayatbackend.ui.model.request.ProjectDetailsRequestModel;
import com.example.hayatbackend.ui.model.response.OperStatusModel;
import com.example.hayatbackend.ui.model.response.ProjectRest;

/**
 *
 * @author hayat
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("projects")
public class ProjectController {
      @Autowired
      ProjectService projectService ;

      @PostMapping("getProjetsById")
    public  List<ProjectRest> getProjectsById(@RequestParam(value="creator_id") String creator_id  ) {
   
     List <ProjectRest> returnedValue = new ArrayList<>();
     List<ProjectDto> projects  =  projectService.getProjectsByUser(creator_id);
     
     for(ProjectDto projectdto : projects ) {
         
            ProjectRest projectrest = new ProjectRest();
            BeanUtils.copyProperties(projectdto, projectrest);
            returnedValue.add(projectrest);
     }
    Collections.reverse(returnedValue);
    return returnedValue; 
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/create")
public ProjectRest ajouterProject(@RequestBody ProjectDetailsRequestModel project) {
 
         ProjectRest returnedValue = new ProjectRest(); 

        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project,projectDto);
  
        ProjectDto createdproject = projectService.ajouterProject(projectDto);
        BeanUtils.copyProperties(createdproject, returnedValue);
      
    return returnedValue; 
    
}
    
    @CrossOrigin(origins = "*")
    @PostMapping("/change")
public OperStatusModel changeStatus(@RequestParam(value="projet_id") String projet_id,@RequestParam(value="status") String status) {
    	OperStatusModel returnedValue =new OperStatusModel();
    projectService.changeProjet(projet_id, status);
    returnedValue.setOperName("change");
    returnedValue.setOperResult("success");
    return returnedValue; 
    
}


    @DeleteMapping(path = "/{id}")
public OperStatusModel deleteProject(@PathVariable Long id) {
OperStatusModel returnedValue =new OperStatusModel();
returnedValue.setOperName("delete");

projectService.deleteProject(id);
returnedValue.setOperResult("success");
return returnedValue;
}
}
