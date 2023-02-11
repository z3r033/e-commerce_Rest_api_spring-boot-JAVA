/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;




/**
 *
 * @author hayat
 */
@Entity(name="users")
public class UserEntity implements Serializable {
    private static final Long serialVersionUID= 1L; 
    
    @Id()
    @GeneratedValue()
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL,CascadeType.REMOVE},
            mappedBy ="creator" , orphanRemoval = true)
      private List<ProjectEntity> creatorproject = new ArrayList<>();
      
      
       
         @OneToMany(cascade ={CascadeType.ALL,CascadeType.REMOVE},
          mappedBy ="creatorcategory" , orphanRemoval = true)
          private List<UserEntity> creatorcategory = new ArrayList<>();
       
  /*  @ManyToMany(mappedBy = "projects")
     private Set<ProjectEntity> projects = new HashSet<>();*/
     
     @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
     @JoinTable(name = "project_users",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "project_id" )
     )
     private Set<ProjectEntity> projects = new HashSet<>();
     
     public void addProject(ProjectEntity project) {
         this.projects.add(project);
         project.getUsers().add(this);
         
     }

     public void removeProject(ProjectEntity project) {
         this.projects.remove(project);
         project.getUsers().remove(this);
        
     }
     public void removeProjects() {
         Iterator<ProjectEntity> iterator = this.projects.iterator();

         while (iterator.hasNext()) {
             ProjectEntity project = iterator.next();

             project.getUsers().remove(this);
             iterator.remove();
         }
     }
     public Set<ProjectEntity> getProjects() {
         return projects;
     }

     public void setProjects(Set<ProjectEntity> projects) {
         this.projects = projects;
     }

  
    @Column(nullable=false)
    private String userId ; 
    @Column(nullable=false ,length = 50)
    private String firstName; 
     @Column(nullable=false ,length = 50)
    private String lastName; 
    @Column(nullable=false ,length = 150 , unique = true)
    private String email ; 
    @Column(nullable=false ,length = 200 , unique = true)
    private String adresse ; 
    public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(nullable=false)
    private String encryptedPassword ; 
       
    private String emailVerificationToken;
    @Column(nullable=false)
    private Boolean emailVerificationStatus=false;
    @Column(nullable=false)
    private String image_profile; 
     @Column(nullable=false)
    private String telephone; 

     
    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
   
}
