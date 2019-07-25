package com.library.subclassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.subclassification.entity.SubClassification;

public interface SubClassificationReposotory extends JpaRepository<SubClassification, Long> {
	SubClassification findSubClassificationBySubClassId(Long subClassId);
//	List<MainClassification> save(List<MainClassification> mainClass);
	
	String fetchAllMainClassId = "SELECT main_class_id FROM subclass";
	@Query(value = fetchAllMainClassId, nativeQuery=true)
	<T> List<T> getAllMainClassId();
	
	String fetchAllSubClassId = "SELECT sub_class_id FROM subclass";
	@Query(value = fetchAllSubClassId, nativeQuery=true)
	<T> List<T> getAllSubClassId();
	
	List<SubClassification> save(List<SubClassification> subclass);
	
}