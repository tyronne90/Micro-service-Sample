package com.library.subclassification.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

	@Override
	public SubClassification getSubClassificationBySubClassId(Long subClassId) {
		return subClassificationRepository.findSubClassificationBySubClassId(subClassId);
	}

//	@Override
//	public List<SubClassification> getSubClassificationBySubClassId(Long subClassId) {
////		return subClassificationRepository.findSubClassificationBySubClassId(subClassId);
//	}
//
//	@Override
//	public SubClassification save(SubClassification subClass) {
////		return subClassificationRepository.save(subClass);
//	}
//
	@Override
	public List<SubClassification> getAllSubClass() {
		return subClassificationRepository.findAll();
	}
//
//	@Override
//	public SubClassification deleteSubClassificationById(Long subClassId) {
////		subClassificationRepository.deleteById(subClassId);
//		return null;
//	}
//
//	@Override
//	public void updateSubClassification(SubClassification subClass) {
////		Long subClassId = subClass.getSubClassId();
////		boolean isExist = subClassificationRepository.findSubClassificationBySubClassId(subClassId) != null;
////		if (isExist) {
////			subClassificationRepository.save(subClass);
////		}
//	}

	@Override
	public void saveMainClass(List<MainClassification> mainClass) {
		int size = mainClass.size();
		int counter = 0;
		
		List<MainClassification> temp = new ArrayList<>();
		
		for (MainClassification emp : mainClass) {
			temp.add(emp);
			
			if ((counter + 1) % 500 == 0 || (counter + 1) == size) {
				mainClassRepo.save(temp);
				temp.clear();
			}
			counter++;
		}
	}
	
}