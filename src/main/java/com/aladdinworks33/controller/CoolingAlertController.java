package com.aladdinworks33.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks33.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks33.domain.CoolingAlert;
import com.aladdinworks33.dto.CoolingAlertDTO;
import com.aladdinworks33.dto.CoolingAlertSearchDTO;
import com.aladdinworks33.dto.CoolingAlertPageDTO;
import com.aladdinworks33.service.CoolingAlertService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/coolingAlert")
@RestController
public class CoolingAlertController {

	private final static Logger logger = LoggerFactory.getLogger(CoolingAlertController.class);

	@Autowired
	CoolingAlertService coolingAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CoolingAlert> getAll() {

		List<CoolingAlert> coolingAlerts = coolingAlertService.findAll();
		
		return coolingAlerts;	
	}

	@GetMapping(value = "/{coolingAlertId}")
	@ResponseBody
	public CoolingAlertDTO getCoolingAlert(@PathVariable Integer coolingAlertId) {
		
		return (coolingAlertService.getCoolingAlertDTOById(coolingAlertId));
	}

 	@RequestMapping(value = "/addCoolingAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addCoolingAlert(@RequestBody CoolingAlertDTO coolingAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingAlertService.addCoolingAlert(coolingAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/coolingAlerts")
	public ResponseEntity<CoolingAlertPageDTO> getCoolingAlerts(CoolingAlertSearchDTO coolingAlertSearchDTO) {
 
		return coolingAlertService.getCoolingAlerts(coolingAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateCoolingAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateCoolingAlert(@RequestBody CoolingAlertDTO coolingAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = coolingAlertService.updateCoolingAlert(coolingAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
