package com.library.mainclassification.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.mainclassification.entity.MainClassification;
import com.library.mainclassification.service.MainClassificationService;

@RestController
public class MainClassificationController {
	@Autowired
	MainClassificationService mainClassificationService;

	@PostMapping("/SaveMainClassification")
	public HttpStatus saveMainClass(@Valid @RequestBody MainClassification mainClass) {
		mainClassificationService.save(mainClass);
		return HttpStatus.CREATED;
	}

	@GetMapping("/GetAllMainClassification")
	public List<MainClassification> getAllMainClass() {
		return mainClassificationService.getAllMainClass();
	}

	@GetMapping("/GetOneMainClassification/{mainClassId}")
	public List<MainClassification> getMainClassList(@PathVariable("mainClassId") Long mainClassId) {
		return mainClassificationService.getMainClassificationByIdList(mainClassId);
	}

	@GetMapping("/GetAllMainClassificationOne/{mainClassId}")
	public MainClassification getAllMainClassOne(@PathVariable("mainClassId") Long mainClassId) {
		return mainClassificationService.getMainClassificationById(mainClassId);
	}

	@GetMapping("/GetMainClassificationById/{mainClassId}")
	public ResponseEntity<MainClassification> getMainClassificationById(@PathVariable("mainClassId") Long mainClassId) {
		return new ResponseEntity<MainClassification>(mainClassificationService.getMainClassificationById(mainClassId),
				HttpStatus.OK);
	}

	@PutMapping("UpdateMainClassification")
	public ResponseEntity<MainClassification> updateBook(@Valid @RequestBody MainClassification mainClass) {
		mainClassificationService.updateMainClassification(mainClass);
		return new ResponseEntity<MainClassification>(mainClass, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/DeleteMainClassById/{mainClassId}")
	public ResponseEntity<MainClassification> deleteMainClassificationById(
			@PathVariable("mainClassId") Long mainClassId) {
		return new ResponseEntity<MainClassification>(
				mainClassificationService.deleteMainClassificationById(mainClassId), HttpStatus.OK);
	}
}
