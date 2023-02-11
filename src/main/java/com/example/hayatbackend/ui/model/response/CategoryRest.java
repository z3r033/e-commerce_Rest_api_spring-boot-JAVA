/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.ui.model.response;

/**
 *
 * @author hayat
 */
public class CategoryRest {
        private Long id;
        private String creatorcategory_id; 
        private Long projectcategory_id;
        private String category_name;
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getCreatorcategory_id() {
		return creatorcategory_id;
	}

	public void setCreatorcategory_id(String creatorcategory_id) {
		this.creatorcategory_id = creatorcategory_id;
	}

	public Long getProjectcategory_id() {
		return projectcategory_id;
	}

	public void setProjectcategory_id(Long projectcategory_id) {
		this.projectcategory_id = projectcategory_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

   

    
    
}
