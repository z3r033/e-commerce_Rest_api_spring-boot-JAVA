
package com.example.hayatbackend.ui.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.hayatbackend.exceptions.UserServiceException;
import com.example.hayatbackend.service.FileStorageService;
import com.example.hayatbackend.service.UserService;
import com.example.hayatbackend.shared.dto.UserDto;
import com.example.hayatbackend.ui.model.request.UserDetailsRequestModel;
import com.example.hayatbackend.ui.model.response.ErrorMessages;
import com.example.hayatbackend.ui.model.response.OperStatusModel;
import com.example.hayatbackend.ui.model.response.UserRest;

/**
 *
 * @author hayat
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
    

    @Autowired
    UserService userService ;
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    FileStorageService fileStorageService;
    
  

  
    
@GetMapping(path = "/{id}"
,produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE }
)
public UserRest getUser(@PathVariable String id) {
  UserRest returnedValue = new UserRest() ;
  UserDto userdto  = userService.getUserById(id);
  if(userdto == null) throw new RuntimeException(" something wrong");
  BeanUtils.copyProperties(userdto, returnedValue);
 
return returnedValue; 

}



@PostMapping 
(produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
 consumes =  { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE }
)
public UserRest createUser (@RequestBody UserDetailsRequestModel userDetails ) throws Exception {
    
    
  
  UserRest returnedValue = new UserRest() ; 
  if(userDetails.getFirstName().isEmpty()) throw new UserServiceException (ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());

  UserDto userDto = new UserDto();
  BeanUtils.copyProperties(userDetails,userDto);
  
  
  UserDto createdUser = userService.createUser(userDto);
  BeanUtils.copyProperties(createdUser, returnedValue);
  return returnedValue ; 
}

@PutMapping
(path = "/{id}",
 produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
 consumes =  { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE }
)
public UserRest updateUser(@PathVariable String id , @RequestBody UserDetailsRequestModel userDetails){
    UserRest returnValue = new UserRest();
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);
    UserDto userUpdated = userService.updateUser(id,userDto);
    BeanUtils.copyProperties(userUpdated, returnValue);
        return returnValue; 
        
}

@DeleteMapping(path = "/{id}",
        produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE },
 consumes =  { MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE }
        )
public OperStatusModel deleteUser(@PathVariable String id) {
OperStatusModel returnedValue =new OperStatusModel();
returnedValue.setOperName("delete");

userService.deleteUser(id);
returnedValue.setOperResult("success");
return returnedValue;
}


@PostMapping("/create")
public UserRest createuser(@RequestParam(value="firstName") String firstName ,@RequestParam(value="lastName") String lastName, 
        @RequestParam(value="email") String email ,@RequestParam(value="telephone") String telephone, @RequestParam(value="password") String password,
        @RequestParam(value="adresse") String adresse      ) {

    
       

UserRest returnedValue = new UserRest() ; 
UserDetailsRequestModel userDetails = new UserDetailsRequestModel(firstName, lastName, password, email, telephone, "image",adresse);
UserDto userDto = new UserDto();
BeanUtils.copyProperties(userDetails,userDto);


UserDto createdUser = userService.createUser(userDto);
BeanUtils.copyProperties(createdUser, returnedValue);
return returnedValue ; 

}

   
    
    @PostMapping("/updateuser")
    public UserRest updateUserName(@RequestParam(value="firstName") String firstName ,@RequestParam(value="lastName") String lastName, 
            @RequestParam(value="email") String email ,@RequestParam(value="telephone") String telephone,
            @RequestParam(value="adresse") String adresse  ) {

  UserRest returnedValue = new UserRest() ; 
 UserDetailsRequestModel userDetails = new UserDetailsRequestModel(firstName, lastName, email, telephone,adresse);
  UserDto userDto = new UserDto();
  BeanUtils.copyProperties(userDetails,userDto);
  
  
  UserDto createdUser = userService.updateUser(userDto);
  BeanUtils.copyProperties(createdUser, returnedValue);
  return returnedValue ; 

    }
    @PostMapping("/updatepassword")
    public UserRest updatePassword(@RequestParam(value="email") String email ,@RequestParam(value="password") String password) {

  UserRest returnedValue = new UserRest() ; 
 UserDetailsRequestModel userDetails = new UserDetailsRequestModel( email, password);
  UserDto userDto = new UserDto();
  BeanUtils.copyProperties(userDetails,userDto);
  
  
  UserDto createdUser = userService.updatePassword(userDto);
  BeanUtils.copyProperties(createdUser, returnedValue);
  return returnedValue ; 

 
    }
    
    
      
    
    
   

}


