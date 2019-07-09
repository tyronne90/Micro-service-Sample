package com.library.mainclassification.service;

import java.util.List;
import com.library.mainclassification.entity.MainClassification;

public interface MainClassificationService {
	MainClassification getMainClassificationById(Long mainClassId);
	
//	Optional<MainClassification> getMainClassificationByIdList(Long mainClassId);
	
	List<MainClassification> getMainClassificationByIdList(Long mainClassId);

	MainClassification save(MainClassification mainClass);

	List<MainClassification> getAllMainClass();

	MainClassification deleteMainClassificationById(Long mainClassId);

	void updateMainClassification(MainClassification mainClass);
}
