package com.library.subclassification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.library.subclassification.entity.MainClassification;
import com.library.subclassification.entity.SubClassification;
import com.library.subclassification.entity.SubClassificationList;
import com.library.subclassification.service.SubClassificationService;

@RestController
public class SubClassificationController {
	@Autowired
	SubClassificationService subClassService;
	
	
	
	@RequestMapping("/mainClass/{mainClassId}")
    public MainClassification getMainClassification(@PathVariable("mainClassId") Long mainClassId) {
//		RestTemplate restTemplate = new RestTemplate();
//		
//		ResponseEntity<MainClassification> response = restTemplate.exchange(
//				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + mainClassId, HttpMethod.GET, null,
//				new ParameterizedTypeReference<MainClassification>() {
//				});
//		MainClassification mainClass = response.getBody();
		
		MainClassification mainClass = getMainClassificationObj(mainClassId);
        return new MainClassification(mainClassId, mainClass.getMainClassName());
    }

	
	
	public MainClassification getMainClassificationObj(Long mainClassId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MainClassification> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + mainClassId, HttpMethod.GET, null,
				new ParameterizedTypeReference<MainClassification>() {
				});
		MainClassification mainClass = response.getBody();
		return mainClass;
	}
	
	
//	@RequestMapping("/SubClassList/{subClassId}")
//    public SubClassificationList getSubClassificationList(@PathVariable("subClassId") Long subClassId) {
//        SubClassificationList subClassificationList = new SubClassificationList();
//        SubClassification subClassification = subClassService.getSubClassificationBySubClassId(subClassId);
//        
//        subClassificationList.setSubClassId(subClassification.getSubClassId());
//        subClassificationList.setSubClassName(subClassification.getSubClassName());
//        subClassificationList.setMainClassId(subClassification.getMainClass());
//        
//        RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<List<MainClassification>> response = restTemplate.exchange(
//				"http://localhost:8081/librarysystem/GetOneMainClassification/" + subClassification.getMainClass(), HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<MainClassification>>() {
//				});
//		List<MainClassification> mainClass = response.getBody();
//		
//		subClassificationList.setMainClass(mainClass);
//	
//        return subClassificationList;
//
//    }	
	
	
	@RequestMapping("/SubClassListObj/{subClassId}")
    public SubClassificationList getSubClassificationObj(@PathVariable("subClassId") Long subClassId) {
        SubClassificationList subClassificationList = new SubClassificationList();
        SubClassification subClassification = subClassService.getSubClassificationBySubClassId(subClassId);
        
        subClassificationList.setSubClassId(subClassification.getSubClassId());
        subClassificationList.setSubClassName(subClassification.getSubClassName());
//        subClassificationList.setMainClassId(subClassification.getMainClass());
        
        RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MainClassification> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + subClassification.getMainClass(), HttpMethod.GET, null,
				new ParameterizedTypeReference<MainClassification>() {
				});
		MainClassification mainClass = response.getBody();
		subClassificationList.setMainClassObj(mainClass);
        return subClassificationList;
    }
	
	@GetMapping("/GetAllSubClassification")
	public List<SubClassification> getAllSubClass() {
		return subClassService.getAllSubClass();
	}

	@GetMapping("/GetSubClassification/{subClassId}")
	public SubClassification getAllMainClassOne(@PathVariable("subClassId") Long subClassId) {
		return subClassService.getSubClassificationBySubClassId(subClassId);
	}
	
	@RequestMapping("/GetAllMainClassification")
	public List<MainClassification> getAllMainClassList() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<MainClassification>> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassification", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MainClassification>>() {
				});
		List<MainClassification> mainClass = response.getBody();
		return mainClass;
	}
	
	@PostMapping("/SaveMainClassification")
	public ResponseEntity<Void> saveMainClassification(@RequestBody List<MainClassification> mainClass) {
		subClassService.saveMainClass(mainClass);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/SaveMainClassificationTable")
	public ResponseEntity<Void> saveMainClassificationTable(@RequestBody List<MainClassification> mainClass) {
		subClassService.saveMainClassificationInTable(mainClass);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping("/GetMainClassificationById/{mainClassId}")
	public List<MainClassification> MainClassificationOneList(@PathVariable("mainClassId") Long mainClassId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<MainClassification>> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassification", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MainClassification>>() {
				});
		List<MainClassification> mainClass = response.getBody();
		return mainClass;
	}



}