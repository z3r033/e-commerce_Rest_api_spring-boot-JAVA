/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.ui.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hayatbackend.io.repository.UserRepository;
import com.example.hayatbackend.service.ProjectService;
import com.example.hayatbackend.service.UserService;
import com.example.hayatbackend.shared.dto.ProjectDto;
import com.example.hayatbackend.ui.model.response.OperStatusModel;
import com.example.hayatbackend.ui.model.response.ProjectRest;

/**
 *
 * @author hayat
 */
@RestController
@RequestMapping("project_users")
public class ProjectUsersController {
    
       @Autowired
       UserService userService ;
         @Autowired
         UserRepository userRepository ;
           @Autowired
           ProjectService projectService ; 
         
@PostMapping 
public OperStatusModel createUser (@RequestParam(value="project_id") String project_id, @RequestParam(value="user_id") String user_id) throws Exception {
    
	OperStatusModel returnedValue =new OperStatusModel();
   userService.ajouterProjectToUser(Long.valueOf(project_id),user_id);
  returnedValue.setOperName("adduser");
  returnedValue.setOperResult("success");
 return returnedValue ; 
}

@PostMapping ("delete")
public OperStatusModel deleteUser (@RequestParam(value="project_id") String project_id, @RequestParam(value="user_id") String user_id) throws Exception {
    
	OperStatusModel returnedValue =new OperStatusModel();
   userService.deleteProjectToUser(Long.valueOf(project_id),user_id);
   returnedValue.setOperName("deluser");
   returnedValue.setOperResult("success");
 return returnedValue ; 
}

@PostMapping ("deletecategory")
public String deletecategory(@RequestParam(value="project_id") Long project_id, @RequestParam(value="tache_id") Long tache_id) throws Exception {
    
    
  String returnedValue = projectService.deleteProjectToUser(project_id,tache_id);

 return returnedValue ; 
}
@PostMapping ("getpro")
    public  List<ProjectRest> getProjectsById(@RequestParam(value="user_id") String user_id  ) {
   
     List <ProjectRest> returnedValue = new ArrayList<>();
     List<ProjectDto> projects  =  userService.getProjectsByUser(user_id);
     
     for(ProjectDto projectdto : projects ) {
         
            ProjectRest projectrest = new ProjectRest();
            BeanUtils.copyProperties(projectdto, projectrest);
            returnedValue.add(projectrest);
     }
    return returnedValue; 
    }

    
}
