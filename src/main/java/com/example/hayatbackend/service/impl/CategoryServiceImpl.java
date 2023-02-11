/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import com.example.hayatbackend.service.CategoryService;
import com.example.hayatbackend.shared.dto.CategoryDto;
import com.example.hayatbackend.ui.model.request.CategoryRequestModel;

/**
 *
 * @author hayat
 */
@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
    ProjectRepository projectRepository; 
  
    @Autowired
    CategoryRepository categoryeRepository; 
      @Autowired
    UserRepository userRepository; 
    
    @Transactional
    @Override
    public CategoryDto ajouterCategory(CategoryRequestModel categoryDto) {
          ProjectEntity  projectEntity = new ProjectEntity();
            
            UserEntity creator_id = userRepository.findByUserId(categoryDto.getCreatorcategory_id());
   
            
          
            ProjectEntity project = projectRepository.findProjectById(Long.valueOf(categoryDto.getProjectcategory_id()) );
            CategoryEntity categoryEntity = new CategoryEntity();
            
            BeanUtils.copyProperties(categoryDto, categoryEntity);
            categoryEntity.setCreatorTache(creator_id);
             CategoryEntity storedTacheDetail = categoryeRepository.save(categoryEntity);
            project.addCategory(storedTacheDetail);
       
       
            projectRepository.save(project);
            CategoryDto returnValue = new CategoryDto();
            BeanUtils.copyProperties(storedTacheDetail, returnValue);
            
            returnValue.setCreatorcategory_id(categoryDto.getCreatorcategory_id());
         //   returnValue.setProjectcategory_id(categoryDto.getProjectcategory_id());
            return returnValue;
    }
    @Transactional
    @Override
    public List<CategoryDto> getCategoryByProject(Long project_id) {
          ProjectEntity projectEntity = projectRepository.findProjectById(project_id);
     List<CategoryEntity> categories=  projectEntity.getProjectcategories();
           List <CategoryDto> returnedValue = new ArrayList<>();    
     for(CategoryEntity categoryentity : categories ) {
         
            CategoryDto categoryedto = new CategoryDto();
            BeanUtils.copyProperties(categoryentity, categoryedto);
           // UserEntity creator = categoryentity.getCreatorTache();
            ProjectEntity project = categoryentity.getProjecttache();
         //   categoryedto.setCreatorcategory_id(creator.getUserId());
            categoryedto.setProjectcategory_id(project.getId().longValue());
            returnedValue.add(categoryedto);
     }
               return returnedValue;
    }

    
}
