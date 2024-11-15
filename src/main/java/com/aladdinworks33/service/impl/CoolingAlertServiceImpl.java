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
import com.aladdinworks33.dao.CoolingAlertDAO;
import com.aladdinworks33.domain.CoolingAlert;
import com.aladdinworks33.dto.CoolingAlertDTO;
import com.aladdinworks33.dto.CoolingAlertSearchDTO;
import com.aladdinworks33.dto.CoolingAlertPageDTO;
import com.aladdinworks33.dto.CoolingAlertConvertCriteriaDTO;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import com.aladdinworks33.service.CoolingAlertService;
import com.aladdinworks33.util.ControllerUtils;





@Service
public class CoolingAlertServiceImpl extends GenericServiceImpl<CoolingAlert, Integer> implements CoolingAlertService {

    private final static Logger logger = LoggerFactory.getLogger(CoolingAlertServiceImpl.class);

	@Autowired
	CoolingAlertDAO coolingAlertDao;

	


	@Override
	public GenericDAO<CoolingAlert, Integer> getDAO() {
		return (GenericDAO<CoolingAlert, Integer>) coolingAlertDao;
	}
	
	public List<CoolingAlert> findAll () {
		List<CoolingAlert> coolingAlerts = coolingAlertDao.findAll();
		
		return coolingAlerts;	
		
	}

	public ResultDTO addCoolingAlert(CoolingAlertDTO coolingAlertDTO, RequestDTO requestDTO) {

		CoolingAlert coolingAlert = new CoolingAlert();

		coolingAlert.setCoolingAlertId(coolingAlertDTO.getCoolingAlertId());


		coolingAlert.setTemperature(coolingAlertDTO.getTemperature());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		coolingAlert = coolingAlertDao.save(coolingAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CoolingAlert> getAllCoolingAlerts(Pageable pageable) {
		return coolingAlertDao.findAll(pageable);
	}

	public Page<CoolingAlert> getAllCoolingAlerts(Specification<CoolingAlert> spec, Pageable pageable) {
		return coolingAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<CoolingAlertPageDTO> getCoolingAlerts(CoolingAlertSearchDTO coolingAlertSearchDTO) {
	
			Integer coolingAlertId = coolingAlertSearchDTO.getCoolingAlertId(); 
  			String sortBy = coolingAlertSearchDTO.getSortBy();
			String sortOrder = coolingAlertSearchDTO.getSortOrder();
			String searchQuery = coolingAlertSearchDTO.getSearchQuery();
			Integer page = coolingAlertSearchDTO.getPage();
			Integer size = coolingAlertSearchDTO.getSize();

	        Specification<CoolingAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, coolingAlertId, "coolingAlertId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<CoolingAlert> coolingAlerts = this.getAllCoolingAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(coolingAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(coolingAlerts.getTotalPages()));
		
		List<CoolingAlert> coolingAlertsList = coolingAlerts.getContent();
		
		CoolingAlertConvertCriteriaDTO convertCriteria = new CoolingAlertConvertCriteriaDTO();
		List<CoolingAlertDTO> coolingAlertDTOs = this.convertCoolingAlertsToCoolingAlertDTOs(coolingAlertsList,convertCriteria);
		
		CoolingAlertPageDTO coolingAlertPageDTO = new CoolingAlertPageDTO();
		coolingAlertPageDTO.setCoolingAlerts(coolingAlertDTOs);
		coolingAlertPageDTO.setTotalElements(coolingAlerts.getTotalElements());
		return ResponseEntity.ok(coolingAlertPageDTO);
	}

	public List<CoolingAlertDTO> convertCoolingAlertsToCoolingAlertDTOs(List<CoolingAlert> coolingAlerts, CoolingAlertConvertCriteriaDTO convertCriteria) {
		
		List<CoolingAlertDTO> coolingAlertDTOs = new ArrayList<CoolingAlertDTO>();
		
		for (CoolingAlert coolingAlert : coolingAlerts) {
			coolingAlertDTOs.add(convertCoolingAlertToCoolingAlertDTO(coolingAlert,convertCriteria));
		}
		
		return coolingAlertDTOs;

	}
	
	public CoolingAlertDTO convertCoolingAlertToCoolingAlertDTO(CoolingAlert coolingAlert, CoolingAlertConvertCriteriaDTO convertCriteria) {
		
		CoolingAlertDTO coolingAlertDTO = new CoolingAlertDTO();
		
		coolingAlertDTO.setCoolingAlertId(coolingAlert.getCoolingAlertId());

	
		coolingAlertDTO.setTemperature(coolingAlert.getTemperature());

	

		
		return coolingAlertDTO;
	}

	public ResultDTO updateCoolingAlert(CoolingAlertDTO coolingAlertDTO, RequestDTO requestDTO) {
		
		CoolingAlert coolingAlert = coolingAlertDao.getById(coolingAlertDTO.getCoolingAlertId());

		coolingAlert.setCoolingAlertId(ControllerUtils.setValue(coolingAlert.getCoolingAlertId(), coolingAlertDTO.getCoolingAlertId()));

		coolingAlert.setTemperature(ControllerUtils.setValue(coolingAlert.getTemperature(), coolingAlertDTO.getTemperature()));



        coolingAlert = coolingAlertDao.save(coolingAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CoolingAlertDTO getCoolingAlertDTOById(Integer coolingAlertId) {
	
		CoolingAlert coolingAlert = coolingAlertDao.getById(coolingAlertId);
			
		
		CoolingAlertConvertCriteriaDTO convertCriteria = new CoolingAlertConvertCriteriaDTO();
		return(this.convertCoolingAlertToCoolingAlertDTO(coolingAlert,convertCriteria));
	}







}
