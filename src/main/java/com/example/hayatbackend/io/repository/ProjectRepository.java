/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.io.repository;



import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hayatbackend.io.entity.ProjectEntity;

/**
 *
 * @author saad
 */
@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long>{
    @Query(value="SELECT * FROM projects p WHERE p.creator_id =:creator_id",nativeQuery = true)
    List<ProjectEntity> fetchProjectsById(@Param("creator_id") String creator_id);
    
    @Query(value="SELECT * FROM projects",nativeQuery = true)
    List<ProjectEntity> fetchProjects();
  
    public ProjectEntity findProjectById(Long id);
    
   
}
