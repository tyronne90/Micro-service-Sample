package com.library.subclassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.subclassification.entity.MainClassification;
import com.library.subclassification.entity.SubClassification;

public interface SubClassificationReposotory extends JpaRepository<SubClassification, Long> {
	SubClassification findSubClassificationBySubClassId(Long subClassId);
	void save(List<MainClassification> mainClass);
}