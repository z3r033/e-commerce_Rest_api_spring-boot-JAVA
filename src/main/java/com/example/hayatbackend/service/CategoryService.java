/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.service;


import java.util.List;

import com.example.hayatbackend.shared.dto.CategoryDto;
import com.example.hayatbackend.ui.model.request.CategoryRequestModel;

/**
 *
 * @author hayat
 */
public interface CategoryService {

    public CategoryDto ajouterCategory(CategoryRequestModel categoryDto);
      public List<CategoryDto> getCategoryByProject(Long project_id);
    
}
