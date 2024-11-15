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

import com.aladdinworks33.domain.NetworkAlert;
import com.aladdinworks33.dto.NetworkAlertDTO;
import com.aladdinworks33.dto.NetworkAlertSearchDTO;
import com.aladdinworks33.dto.NetworkAlertPageDTO;
import com.aladdinworks33.service.NetworkAlertService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/networkAlert")
@RestController
public class NetworkAlertController {

	private final static Logger logger = LoggerFactory.getLogger(NetworkAlertController.class);

	@Autowired
	NetworkAlertService networkAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<NetworkAlert> getAll() {

		List<NetworkAlert> networkAlerts = networkAlertService.findAll();
		
		return networkAlerts;	
	}

	@GetMapping(value = "/{networkAlertId}")
	@ResponseBody
	public NetworkAlertDTO getNetworkAlert(@PathVariable Integer networkAlertId) {
		
		return (networkAlertService.getNetworkAlertDTOById(networkAlertId));
	}

 	@RequestMapping(value = "/addNetworkAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addNetworkAlert(@RequestBody NetworkAlertDTO networkAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkAlertService.addNetworkAlert(networkAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/networkAlerts")
	public ResponseEntity<NetworkAlertPageDTO> getNetworkAlerts(NetworkAlertSearchDTO networkAlertSearchDTO) {
 
		return networkAlertService.getNetworkAlerts(networkAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateNetworkAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateNetworkAlert(@RequestBody NetworkAlertDTO networkAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkAlertService.updateNetworkAlert(networkAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
