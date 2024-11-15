package com.aladdinworks33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks33.domain.Configuration;
import com.aladdinworks33.dto.ConfigurationDTO;
import com.aladdinworks33.dto.ConfigurationSearchDTO;
import com.aladdinworks33.dto.ConfigurationPageDTO;
import com.aladdinworks33.dto.ConfigurationConvertCriteriaDTO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ConfigurationService extends GenericService<Configuration, Integer> {

	List<Configuration> findAll();

	ResultDTO addConfiguration(ConfigurationDTO configurationDTO, RequestDTO requestDTO);

	ResultDTO updateConfiguration(ConfigurationDTO configurationDTO, RequestDTO requestDTO);

    Page<Configuration> getAllConfigurations(Pageable pageable);

    Page<Configuration> getAllConfigurations(Specification<Configuration> spec, Pageable pageable);

	ResponseEntity<ConfigurationPageDTO> getConfigurations(ConfigurationSearchDTO configurationSearchDTO);
	
	List<ConfigurationDTO> convertConfigurationsToConfigurationDTOs(List<Configuration> configurations, ConfigurationConvertCriteriaDTO convertCriteria);

	ConfigurationDTO getConfigurationDTOById(Integer configurationId);







}





