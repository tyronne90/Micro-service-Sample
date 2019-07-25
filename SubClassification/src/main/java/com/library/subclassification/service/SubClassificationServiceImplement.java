package com.library.subclassification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.subclassification.entity.MainClassification;
import com.library.subclassification.entity.SubClassification;
import com.library.subclassification.repository.MainClassificationRepository;
import com.library.subclassification.repository.SubClassificationReposotory;

@Service
public class SubClassificationServiceImplement implements SubClassificationService {
	
	@Autowired
	SubClassificationReposotory subClassificationRepository;
	
	@Autowired
	MainClassificationRepository mainClassRepo;
	
	// Find SubClassification By Id
	@Override
	public SubClassification getSubClassificationBySubClassId(Long subClassId) {
		return subClassificationRepository.findSubClassificationBySubClassId(subClassId);
	}

	//	Save SubClassifiaction 
	@Override
	public SubClassification save(SubClassification subClass) {
		return subClassificationRepository.save(subClass);
	}
	
	//	Get All SubClassification List
	@Override
	public List<SubClassification> getAllSubClass() {
		return subClassificationRepository.findAll();
	}

	//	Delete SubClassifcation 
	@Override
	public SubClassification deleteSubClassificationById(Long subClassId) {
		subClassificationRepository.deleteById(subClassId);
		return null;
	}

	//	Update SubClassification
	@Override
	public void updateSubClassification(SubClassification subClass) {
		Long subClassId = subClass.getSubClassId();
		boolean isExist = subClassificationRepository.findSubClassificationBySubClassId(subClassId) != null;
		if (isExist) {
			subClassificationRepository.save(subClass);
		}
	}

	//	Save MainClassification into SubClassification table get from MainClassification table -> 8082 <-> 8081
	@Override
	public void saveMainClass(List<MainClassification> mainClass) {
		int size = mainClass.size();
		int counter = 0;
		
		List<MainClassification> temp = new ArrayList<>();
		
		for (MainClassification emp : mainClass) {
			temp.add(emp);
			
			if ((counter + 1) % 500 == 0 || (counter + 1) == size) {
			mainClassRepo.saveAll(mainClass);
				temp.clear();
			}
			counter++;
		}
	}

	
	
	@Override
	public List<MainClassification> saveMainClassificationInTable(List<MainClassification> mainClass) {
		return mainClassRepo.saveAll(mainClass);
	}

	@Override
	public List<SubClassification> getAllMainClassId() {
		return subClassificationRepository.getAllMainClassId();
	}

	//	Get All MainClassification
	@Override
	public List<MainClassification> getAllMainClass() {
		return mainClassRepo.findAll();
	}

	//	Get all subClassification Id
	@Override
	public List<SubClassification> getAllSubClassId() {
		return subClassificationRepository.getAllSubClassId();
	}


	@Override
	public void saveSubClassificationInTable(List<SubClassification> subClass) {
		int size = subClass.size();
		int counter = 0;
		
		List<SubClassification> temp = new ArrayList<>();
		
		for (SubClassification emp : subClass) {
			temp.add(emp);
			
			if ((counter + 1) % 500 == 0 || (counter + 1) == size) {
			subClassificationRepository.saveAll(subClass);
				temp.clear();
			}
			counter++;
		}
	}
	
}