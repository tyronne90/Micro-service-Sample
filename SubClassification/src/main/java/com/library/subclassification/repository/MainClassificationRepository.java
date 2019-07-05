package com.library.subclassification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.subclassification.entity.MainClassification;

public interface MainClassificationRepository extends JpaRepository<MainClassification, Long> {
	List<MainClassification> save(List<MainClassification> mainClass);
}