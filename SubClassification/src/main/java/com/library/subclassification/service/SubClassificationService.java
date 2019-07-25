package com.library.subclassification.service;

import java.util.List;

import com.library.subclassification.entity.MainClassification;
import com.library.subclassification.entity.SubClassification;

public interface SubClassificationService {
	// Get All SubClassification By Id
	SubClassification getSubClassificationBySubClassId(Long subClassId);
	// Save SubClassification
	SubClassification save(SubClassification subClass);
	// List All SubClassification
	List<SubClassification> getAllSubClass();
	// Delete SubClassification
	SubClassification deleteSubClassificationById(Long subClassId);
	// Update SubClassification
	void updateSubClassification(SubClassification subClass);

	//Save MainClassification into mainClassification table
	void saveMainClass(List<MainClassification> mainClass);
	
	
	List<MainClassification> saveMainClassificationInTable(List<MainClassification> mainClass);
	
	//Save SubClassification bulk
	void saveSubClassificationInTable(List<SubClassification> subClass);

	List<MainClassification> getAllMainClass();
	List<SubClassification> getAllMainClassId();
	List<SubClassification> getAllSubClassId();
	
}