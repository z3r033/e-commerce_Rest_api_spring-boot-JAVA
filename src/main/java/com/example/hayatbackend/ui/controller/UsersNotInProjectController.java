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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hayatbackend.io.repository.UserRepository;
import com.example.hayatbackend.service.UserService;
import com.example.hayatbackend.shared.dto.UserDto;
import com.example.hayatbackend.ui.model.response.UserRest;

/**
 *
 * @author hayat
 */
@RestController
@RequestMapping("usersproject")
public class UsersNotInProjectController {
        @Autowired
       UserService userService ;
         @Autowired
         UserRepository userRepository ;
         
          @PostMapping("/notin")
    public  List<UserRest> getUsersNotInProject( @RequestParam(value="project_id") Long project_id ) {
        
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  userService.getUsersInProject(project_id);
     
     for(UserDto userdto : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(userdto, userrest);
            returnedValue.add(userrest);
     }
    return returnedValue; 
    }
     
          @PostMapping("in")
    public  List<UserRest> getUserInProject( @RequestParam(value="project_id") Long project_id ) {
        
   
     List <UserRest> returnedValue = new ArrayList<>();
     List<UserDto> users  =  userService.getUsersNotInProject(project_id);
     
     for(UserDto userdto : users ) {
         
            UserRest userrest = new UserRest();
            BeanUtils.copyProperties(userdto, userrest);
            returnedValue.add(userrest);
     }
    return returnedValue; 
    }

    
}
