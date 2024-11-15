package com.aladdinworks33.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.service.impl.GenericServiceImpl;
import com.aladdinworks33.dao.ConfigurationDAO;
import com.aladdinworks33.domain.Configuration;
import com.aladdinworks33.dto.ConfigurationDTO;
import com.aladdinworks33.dto.ConfigurationSearchDTO;
import com.aladdinworks33.dto.ConfigurationPageDTO;
import com.aladdinworks33.dto.ConfigurationConvertCriteriaDTO;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import com.aladdinworks33.service.ConfigurationService;
import com.aladdinworks33.util.ControllerUtils;





@Service
public class ConfigurationServiceImpl extends GenericServiceImpl<Configuration, Integer> implements ConfigurationService {

    private final static Logger logger = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

	@Autowired
	ConfigurationDAO configurationDao;

	


	@Override
	public GenericDAO<Configuration, Integer> getDAO() {
		return (GenericDAO<Configuration, Integer>) configurationDao;
	}
	
	public List<Configuration> findAll () {
		List<Configuration> configurations = configurationDao.findAll();
		
		return configurations;	
		
	}

	public ResultDTO addConfiguration(ConfigurationDTO configurationDTO, RequestDTO requestDTO) {

		Configuration configuration = new Configuration();

		configuration.setConfigurationId(configurationDTO.getConfigurationId());


		configuration.setName(configurationDTO.getName());


		configuration.setValue(configurationDTO.getValue());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		configuration = configurationDao.save(configuration);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Configuration> getAllConfigurations(Pageable pageable) {
		return configurationDao.findAll(pageable);
	}

	public Page<Configuration> getAllConfigurations(Specification<Configuration> spec, Pageable pageable) {
		return configurationDao.findAll(spec, pageable);
	}

	public ResponseEntity<ConfigurationPageDTO> getConfigurations(ConfigurationSearchDTO configurationSearchDTO) {
	
			Integer configurationId = configurationSearchDTO.getConfigurationId(); 
 			String name = configurationSearchDTO.getName(); 
 			String value = configurationSearchDTO.getValue(); 
 			String sortBy = configurationSearchDTO.getSortBy();
			String sortOrder = configurationSearchDTO.getSortOrder();
			String searchQuery = configurationSearchDTO.getSearchQuery();
			Integer page = configurationSearchDTO.getPage();
			Integer size = configurationSearchDTO.getSize();

	        Specification<Configuration> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, configurationId, "configurationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, value, "value"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("value")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Configuration> configurations = this.getAllConfigurations(spec, pageable);
		
		//System.out.println(String.valueOf(configurations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(configurations.getTotalPages()));
		
		List<Configuration> configurationsList = configurations.getContent();
		
		ConfigurationConvertCriteriaDTO convertCriteria = new ConfigurationConvertCriteriaDTO();
		List<ConfigurationDTO> configurationDTOs = this.convertConfigurationsToConfigurationDTOs(configurationsList,convertCriteria);
		
		ConfigurationPageDTO configurationPageDTO = new ConfigurationPageDTO();
		configurationPageDTO.setConfigurations(configurationDTOs);
		configurationPageDTO.setTotalElements(configurations.getTotalElements());
		return ResponseEntity.ok(configurationPageDTO);
	}

	public List<ConfigurationDTO> convertConfigurationsToConfigurationDTOs(List<Configuration> configurations, ConfigurationConvertCriteriaDTO convertCriteria) {
		
		List<ConfigurationDTO> configurationDTOs = new ArrayList<ConfigurationDTO>();
		
		for (Configuration configuration : configurations) {
			configurationDTOs.add(convertConfigurationToConfigurationDTO(configuration,convertCriteria));
		}
		
		return configurationDTOs;

	}
	
	public ConfigurationDTO convertConfigurationToConfigurationDTO(Configuration configuration, ConfigurationConvertCriteriaDTO convertCriteria) {
		
		ConfigurationDTO configurationDTO = new ConfigurationDTO();
		
		configurationDTO.setConfigurationId(configuration.getConfigurationId());

	
		configurationDTO.setName(configuration.getName());

	
		configurationDTO.setValue(configuration.getValue());

	

		
		return configurationDTO;
	}

	public ResultDTO updateConfiguration(ConfigurationDTO configurationDTO, RequestDTO requestDTO) {
		
		Configuration configuration = configurationDao.getById(configurationDTO.getConfigurationId());

		configuration.setConfigurationId(ControllerUtils.setValue(configuration.getConfigurationId(), configurationDTO.getConfigurationId()));

		configuration.setName(ControllerUtils.setValue(configuration.getName(), configurationDTO.getName()));

		configuration.setValue(ControllerUtils.setValue(configuration.getValue(), configurationDTO.getValue()));



        configuration = configurationDao.save(configuration);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ConfigurationDTO getConfigurationDTOById(Integer configurationId) {
	
		Configuration configuration = configurationDao.getById(configurationId);
			
		
		ConfigurationConvertCriteriaDTO convertCriteria = new ConfigurationConvertCriteriaDTO();
		return(this.convertConfigurationToConfigurationDTO(configuration,convertCriteria));
	}







}
