package com.library.subclassification.entity;

public class SubClassificationList {
	private Long subClassId;
	private String subClassName;
//	private Long mainClassId;
//	private List<MainClassification> mainClass;
	private MainClassification mainClassObj;
	
	public MainClassification getMainClassObj() {
		return mainClassObj;
	}
	public void setMainClassObj(MainClassification mainClassObj) {
		this.mainClassObj = mainClassObj;
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
