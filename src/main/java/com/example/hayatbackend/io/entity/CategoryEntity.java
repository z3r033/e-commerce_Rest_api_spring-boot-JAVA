/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author hayat
 */
@Entity(name="categories")
public class CategoryEntity  implements Serializable {
      private static final Long serialVersionUID= 1L; 
      
         @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
         
         @Column(nullable=false ,length = 50)
         private String category_name; 

         
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private UserEntity creatorCategory; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectEntity projectcategory; 

    public UserEntity getCreatorTache() {
        return creatorCategory;
    }

    public void setCreatorTache(UserEntity creatorCategory) {
        this.creatorCategory = creatorCategory;
    }

    public ProjectEntity getProjecttache() {
        return projectcategory;
    }

    public void setProjecttache(ProjectEntity projectcategory) {
        this.projectcategory = projectcategory;
    }
    
    
            
  
 
 
    
    public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    
    
         
    @Override
    public boolean equals(Object obj) {
               
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return id != null && id.equals(((CategoryEntity) obj).id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }
    
}
