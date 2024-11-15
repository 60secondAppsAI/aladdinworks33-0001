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

import com.aladdinworks33.domain.Configuration;
import com.aladdinworks33.dto.ConfigurationDTO;
import com.aladdinworks33.dto.ConfigurationSearchDTO;
import com.aladdinworks33.dto.ConfigurationPageDTO;
import com.aladdinworks33.service.ConfigurationService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/configuration")
@RestController
public class ConfigurationController {

	private final static Logger logger = LoggerFactory.getLogger(ConfigurationController.class);

	@Autowired
	ConfigurationService configurationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Configuration> getAll() {

		List<Configuration> configurations = configurationService.findAll();
		
		return configurations;	
	}

	@GetMapping(value = "/{configurationId}")
	@ResponseBody
	public ConfigurationDTO getConfiguration(@PathVariable Integer configurationId) {
		
		return (configurationService.getConfigurationDTOById(configurationId));
	}

 	@RequestMapping(value = "/addConfiguration", method = RequestMethod.POST)
	public ResponseEntity<?> addConfiguration(@RequestBody ConfigurationDTO configurationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = configurationService.addConfiguration(configurationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/configurations")
	public ResponseEntity<ConfigurationPageDTO> getConfigurations(ConfigurationSearchDTO configurationSearchDTO) {
 
		return configurationService.getConfigurations(configurationSearchDTO);
	}	

	@RequestMapping(value = "/updateConfiguration", method = RequestMethod.POST)
	public ResponseEntity<?> updateConfiguration(@RequestBody ConfigurationDTO configurationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = configurationService.updateConfiguration(configurationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
