package com.library.subclassification.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(schema = "library_micro_service_sub_classification", name = "subclass")
public class SubClassification implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subClassId;
	private String subClassName;
    private Long mainClassId;
    

	public SubClassification() {
		super();
	}

	
	

	public SubClassification(Long mainClassId) {
		this.mainClassId = mainClassId;
	}




	public SubClassification(Long subClassId, String subClassName, Long mainClassId) {
		super();
		this.subClassId = subClassId;
		this.subClassName = subClassName;
		this.mainClassId = mainClassId;
	}




	public Long getMainClassId() {
		return mainClassId;
	}



	public void setMainClassId(Long mainClassId) {
		this.mainClassId = mainClassId;
	}



	public Long getSubClassId() {
		return subClassId;
	}


	public void setSubClassId(Long subClassId) {
		this.subClassId = subClassId;
	}


	public String getSubClassName() {
		return subClassName;
	}


	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}




}