package com.library.subclassification.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(schema = "libraryMicroServiceSubClassification", name = "subclass")
public class SubClassification implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subClassId;
	private String subClassName;
    private Long mainClass;
    

	public SubClassification() {
		super();
	}

	public Long getMainClass() {
		return mainClass;
	}




	public void setMainClass(Long mainClass) {
		this.mainClass = mainClass;
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