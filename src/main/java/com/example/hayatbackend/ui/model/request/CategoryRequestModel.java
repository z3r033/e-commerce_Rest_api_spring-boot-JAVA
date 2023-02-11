/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hayatbackend.ui.model.request;

/**
 *
 * @author hayat
 */
public class CategoryRequestModel {

    private String creatorcategory_id; 
    private String projectcategory_id;
    private String category_name;
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCreatorcategory_id() {
		return creatorcategory_id;
	}
	public void setCreatorcategory_id(String creatorcategory_id) {
		this.creatorcategory_id = creatorcategory_id;
	}
	public String getProjectcategory_id() {
		return projectcategory_id;
	}
	public void setProjectcategory_id(String projectcategory_id) {
		this.projectcategory_id = projectcategory_id;
	} 
 


  
}
