package com.aladdinworks33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks33.domain.CoolingAlert;
import com.aladdinworks33.dto.CoolingAlertDTO;
import com.aladdinworks33.dto.CoolingAlertSearchDTO;
import com.aladdinworks33.dto.CoolingAlertPageDTO;
import com.aladdinworks33.dto.CoolingAlertConvertCriteriaDTO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CoolingAlertService extends GenericService<CoolingAlert, Integer> {

	List<CoolingAlert> findAll();

	ResultDTO addCoolingAlert(CoolingAlertDTO coolingAlertDTO, RequestDTO requestDTO);

	ResultDTO updateCoolingAlert(CoolingAlertDTO coolingAlertDTO, RequestDTO requestDTO);

    Page<CoolingAlert> getAllCoolingAlerts(Pageable pageable);

    Page<CoolingAlert> getAllCoolingAlerts(Specification<CoolingAlert> spec, Pageable pageable);

	ResponseEntity<CoolingAlertPageDTO> getCoolingAlerts(CoolingAlertSearchDTO coolingAlertSearchDTO);
	
	List<CoolingAlertDTO> convertCoolingAlertsToCoolingAlertDTOs(List<CoolingAlert> coolingAlerts, CoolingAlertConvertCriteriaDTO convertCriteria);

	CoolingAlertDTO getCoolingAlertDTOById(Integer coolingAlertId);







}





