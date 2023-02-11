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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hayatbackend.service.CategoryService;
import com.example.hayatbackend.shared.dto.CategoryDto;
import com.example.hayatbackend.ui.model.request.CategoryRequestModel;
import com.example.hayatbackend.ui.model.response.CategoryRest;

/**
 *
 * @author hayat
 */
@RestController
@RequestMapping("categories")
public class CategoryController {
       @Autowired
      CategoryService categoryService ;
               
      
          @PostMapping
public CategoryRest ajouterCategory(@RequestBody CategoryRequestModel category) {
 
         CategoryRest returnedValue = new CategoryRest(); 

              CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category,categoryDto);
  
        CategoryDto createdtache = categoryService.ajouterCategory(category);
        BeanUtils.copyProperties(createdtache, returnedValue);
      
       return returnedValue; 
    
}

  @PostMapping("get")
    public  List<CategoryRest> getCategoryById(@RequestParam(value="project_id") String project_id  ) {
   
     List <CategoryRest> returnedValue = new ArrayList<>();
     List<CategoryDto> cateogires  =  categoryService.getCategoryByProject(Long.valueOf(project_id));
     
     for(CategoryDto categorydto : cateogires ) {
         
            CategoryRest categoryrest = new CategoryRest();
            BeanUtils.copyProperties(categorydto, categoryrest);
            returnedValue.add(categoryrest);
     }
    return returnedValue; 
    }
    
}
