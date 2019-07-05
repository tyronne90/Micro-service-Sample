package com.library.subclassification.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(schema = "libraryMicroServiceSubClassification", name = "mainclass")
public class MainClassification implements Serializable{
	@Id
	public Long mainClassId;
	private String mainClassName;


	public MainClassification() {
		super();
	}

	public MainClassification(Long mainClassId, String mainClassName) {
		super();
		this.mainClassId = mainClassId;
		this.mainClassName = mainClassName;
	}

	public Long getMainClassId() {
		return mainClassId;
	}

	public void setMainClassId(Long mainClassId) {
		this.mainClassId = mainClassId;
	}

	public String getMainClassName() {
		return mainClassName;
	}

	public void setMainClassName(String mainClassName) {
		this.mainClassName = mainClassName;
	}

}