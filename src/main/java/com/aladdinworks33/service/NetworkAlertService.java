package com.aladdinworks33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks33.domain.NetworkAlert;
import com.aladdinworks33.dto.NetworkAlertDTO;
import com.aladdinworks33.dto.NetworkAlertSearchDTO;
import com.aladdinworks33.dto.NetworkAlertPageDTO;
import com.aladdinworks33.dto.NetworkAlertConvertCriteriaDTO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NetworkAlertService extends GenericService<NetworkAlert, Integer> {

	List<NetworkAlert> findAll();

	ResultDTO addNetworkAlert(NetworkAlertDTO networkAlertDTO, RequestDTO requestDTO);

	ResultDTO updateNetworkAlert(NetworkAlertDTO networkAlertDTO, RequestDTO requestDTO);

    Page<NetworkAlert> getAllNetworkAlerts(Pageable pageable);

    Page<NetworkAlert> getAllNetworkAlerts(Specification<NetworkAlert> spec, Pageable pageable);

	ResponseEntity<NetworkAlertPageDTO> getNetworkAlerts(NetworkAlertSearchDTO networkAlertSearchDTO);
	
	List<NetworkAlertDTO> convertNetworkAlertsToNetworkAlertDTOs(List<NetworkAlert> networkAlerts, NetworkAlertConvertCriteriaDTO convertCriteria);

	NetworkAlertDTO getNetworkAlertDTOById(Integer networkAlertId);







}





