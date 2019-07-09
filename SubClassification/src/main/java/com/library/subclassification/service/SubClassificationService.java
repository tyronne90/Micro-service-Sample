package com.library.subclassification.service;

import java.util.List;

import com.library.subclassification.entity.MainClassification;
import com.library.subclassification.entity.SubClassification;

public interface SubClassificationService {
	SubClassification getSubClassificationBySubClassId(Long subClassId);
//	SubClassification save(SubClassification subClass);
	List<SubClassification> getAllSubClass();
//	SubClassification deleteSubClassificationById(Long subClassId);
//	void updateSubClassification(SubClassification subClass);

	void saveMainClass(List<MainClassification> mainClass);
	
	List<MainClassification> saveMainClassificationInTable(List<MainClassification> mainClass);

	List<MainClassification> getAllMainClass();
	List<SubClassification> getAllMainClassId();
	List<SubClassification> getAllSubClassId();
	
//	List<SubClassificationList> getAllSubClassificationList();
	
	
	
}