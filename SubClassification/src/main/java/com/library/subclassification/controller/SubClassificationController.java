package com.library.subclassification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

//	@RequestMapping("/mainClass/{mainClassId}")
//    public MainClassification getMainClassification(@PathVariable("mainClassId") Long mainClassId) {
////		RestTemplate restTemplate = new RestTemplate();
////		
////		ResponseEntity<MainClassification> response = restTemplate.exchange(
////				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + mainClassId, HttpMethod.GET, null,
////				new ParameterizedTypeReference<MainClassification>() {
////				});
////		MainClassification mainClass = response.getBody();
//		
//		MainClassification mainClass = getMainClassificationObj(mainClassId);
//        return new MainClassification(mainClassId, mainClass.getMainClassName());
//    }

	// Get One MainClassification from MainClassification Service : 8081
	public MainClassification getMainClassificationObj(Long mainClassId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MainClassification> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + mainClassId, HttpMethod.GET, null,
				new ParameterizedTypeReference<MainClassification>() {
				});
		MainClassification mainClass = response.getBody();
		return mainClass;
	}

	//Using SubClassification List
	//Get One SubClassification details with MainClassification -> Collabrate with 2 Service -> from 8082 -> 8081
	@RequestMapping("/SubClassListObj/{subClassId}")
	public SubClassificationList getSubClassificationObj(@PathVariable("subClassId") Long subClassId) {
		SubClassificationList subClassificationList = new SubClassificationList();
		SubClassification subClassification = subClassService.getSubClassificationBySubClassId(subClassId);

		subClassificationList.setSubClassId(subClassification.getSubClassId());
		subClassificationList.setSubClassName(subClassification.getSubClassName());
		subClassificationList.setMainClassId(subClassification.getMainClassId());

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MainClassification> response = restTemplate.exchange(
				"http://localhost:8081/librarysystem/GetAllMainClassificationOne/" + subClassification.getMainClassId(),
				HttpMethod.GET, null, new ParameterizedTypeReference<MainClassification>() {
				});
		MainClassification mainClass = response.getBody();
		subClassificationList.setMainClassObj(mainClass);
		return subClassificationList;
	}

	
	//Get All SubClassification Service Using SubClassification Entity
	public List<SubClassification> SubClassList() {
		return subClassService.getAllSubClass();
	}

//	@GetMapping("/SubClassListAllWithMainClass")
//	public static List<Object> SubClassListAllWithMainClass() {
//	    List<String> itemsList1 = new ArrayList(Arrays.asList("One", "Two"));
//	    List<String> itemsList2 = new ArrayList(Arrays.asList("Three", "Four"));
//	    List<String> mergedList2 = Stream.concat(itemsList1.stream(), itemsList2.stream())
//	               .distinct()
//	              .collect(Collectors.toList());
//	    
//	    List<Object> mergedList3 = new ArrayList<>();
//	    Stream.of(itemsList1, itemsList2).forEach(mergedList3::addAll);
//		
//		return mergedList3;
//}
	
	
	// Get All SubClassification
	@GetMapping("/GetAllSubClassification")
	public List<SubClassification> getAllSubClass() {
		return subClassService.getAllSubClass();
	}

	// Get All MainClassification
	@GetMapping("/GetAllMainClassification")
	public List<MainClassification> getAllMainClass() {
		return subClassService.getAllMainClass();
	}

	// Get All SubClassification Id
	@GetMapping("/GetAllSUbClassificationId")
	public List<SubClassification> getAllSubClassId() {
		return subClassService.getAllSubClassId();
	}

	// Get All MainClassification Id from MainClassification Service -> from 8081
	//Return as String
	@GetMapping("/GetAllMainClassificationIds")
	public String getAllMainClassIds() {
		
		RestTemplate restTemplate = new RestTemplate();
		List<SubClassification> listMainClassId = subClassService.getAllMainClassId();
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<List<SubClassification>> entity = new HttpEntity<List<SubClassification>>(listMainClassId,headers);
	      
	      String mainClass =  restTemplate.exchange(
	         "http://localhost:8081/librarysystem/GetMainClassificationId", HttpMethod.POST, entity, String.class).getBody();
	   
		return mainClass;
	}
	
	
	// Get All MainClassification Id from MainClassification Service -> from 8081
	//Return as List<Object>
	@GetMapping("/GetAllMainClassificationId")
	public ResponseEntity<List<MainClassification>> getAllMainClassId() {
		String theUrl = "http://localhost:8081/librarysystem/GetMainClassificationId";
		RestTemplate restTemplate = new RestTemplate();
		List<SubClassification> listMainClassId = subClassService.getAllMainClassId();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<SubClassification>> entity = new HttpEntity<List<SubClassification>>(listMainClassId, headers);

		ResponseEntity<List<MainClassification>> mainClass = restTemplate.exchange(theUrl, HttpMethod.POST, entity,
				new ParameterizedTypeReference<List<MainClassification>>() {
				});

		return mainClass;
	}


	// Get All SubClassification List -> Collaborate with 2 services -> 8081 -> 8082
	@GetMapping("/GetAllSubClassList")
	public List<SubClassificationList> getAllSubClassList() {
		RestTemplate restTemplate = new RestTemplate();
		List<SubClassification> subClassList = subClassService.getAllSubClassId();
		int length = subClassList.size();
		System.out.println(length);
		List<SubClassificationList> retrievedSubClass = new ArrayList<SubClassificationList>();
		for (int i = 0; i < length; i++) {
			SubClassificationList subClassificationList = new SubClassificationList();
			Long subClassId = Long.parseLong(String.valueOf(subClassList.get(i)));
			SubClassification subClassification = subClassService.getSubClassificationBySubClassId(subClassId);
			subClassificationList.setSubClassId(subClassification.getSubClassId());
			subClassificationList.setSubClassName(subClassification.getSubClassName());
			subClassificationList.setMainClassId(subClassification.getMainClassId());	        
			ResponseEntity<MainClassification> response = restTemplate.exchange(
					"http://localhost:8081/librarysystem/GetAllMainClassificationOne/"
							+ subClassification.getMainClassId(),
					HttpMethod.GET, null, new ParameterizedTypeReference<MainClassification>() {
					});
			MainClassification mainClass = response.getBody();
			subClassificationList.setMainClassObj(mainClass);
			retrievedSubClass.add(subClassificationList);
		}
		return retrievedSubClass;
	}
	
	
//	Get All SubClassification Id from SubClassification Service ->  8082
	@GetMapping("/GetSubClassification/{subClassId}")
	public SubClassification getAllMainClassOne(@PathVariable("subClassId") Long subClassId) {
		return subClassService.getSubClassificationBySubClassId(subClassId);
	}

	
// Get All MainClassification List from MainClassification Service -> 8081
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

//	@RequestMapping("/SendAllMainClassificationId")
//	public List<MainClassification> sendAllMainClassListId(@RequestBody List<MainClassification> mainClass) {
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<List<MainClassification>> response = restTemplate.exchange(
//				"http://localhost:8081/librarysystem/GetAllMainClassification", HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<MainClassification>>() {
//				});
//		List<MainClassification> mainClass = response.getBody();
//		return mainClass;
//	}
//	
	
	// Save MainClassification into SubClassification Service -> 8082
	@PostMapping("/SaveMainClassification")
	public ResponseEntity<Void> saveMainClassification(@RequestBody List<MainClassification> mainClass) {
		subClassService.saveMainClass(mainClass);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//	
	@PostMapping("/SaveMainClassificationTable")
	public ResponseEntity<Void> saveMainClassificationTable(@RequestBody List<MainClassification> mainClass) {
		subClassService.saveMainClassificationInTable(mainClass);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//	Get MainClassification IDs from MainClassification Service -> 8081
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