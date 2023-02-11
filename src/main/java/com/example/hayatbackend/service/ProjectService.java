/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.service;


import java.util.List;

import com.example.hayatbackend.shared.dto.ProjectDto;
/**
 *
 * @author hayat
 */
public interface ProjectService {

    public ProjectDto ajouterProject(ProjectDto projectDto);
    public String ajouterUserToProject(String project_id , String user_id);
    public List<ProjectDto> getProjectsByUser(String creator_id);
    public List<ProjectDto> getProjectAll();

    public void deleteProject(Long id);
   public String deleteProjectToUser(Long project_id, Long tache_id);
public void changeProjet(String projet_id, String status);
    
}
