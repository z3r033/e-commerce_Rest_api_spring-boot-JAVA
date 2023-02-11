/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.io.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hayatbackend.io.entity.CategoryEntity;

/**
 *
 * @author saad
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    public CategoryEntity findProjectById(Long tache_id);
    
}
