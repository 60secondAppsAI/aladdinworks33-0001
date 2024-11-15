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
import com.aladdinworks33.dao.NetworkAlertDAO;
import com.aladdinworks33.domain.NetworkAlert;
import com.aladdinworks33.dto.NetworkAlertDTO;
import com.aladdinworks33.dto.NetworkAlertSearchDTO;
import com.aladdinworks33.dto.NetworkAlertPageDTO;
import com.aladdinworks33.dto.NetworkAlertConvertCriteriaDTO;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import com.aladdinworks33.service.NetworkAlertService;
import com.aladdinworks33.util.ControllerUtils;





@Service
public class NetworkAlertServiceImpl extends GenericServiceImpl<NetworkAlert, Integer> implements NetworkAlertService {

    private final static Logger logger = LoggerFactory.getLogger(NetworkAlertServiceImpl.class);

	@Autowired
	NetworkAlertDAO networkAlertDao;

	


	@Override
	public GenericDAO<NetworkAlert, Integer> getDAO() {
		return (GenericDAO<NetworkAlert, Integer>) networkAlertDao;
	}
	
	public List<NetworkAlert> findAll () {
		List<NetworkAlert> networkAlerts = networkAlertDao.findAll();
		
		return networkAlerts;	
		
	}

	public ResultDTO addNetworkAlert(NetworkAlertDTO networkAlertDTO, RequestDTO requestDTO) {

		NetworkAlert networkAlert = new NetworkAlert();

		networkAlert.setNetworkAlertId(networkAlertDTO.getNetworkAlertId());


		networkAlert.setTrafficLoad(networkAlertDTO.getTrafficLoad());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		networkAlert = networkAlertDao.save(networkAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<NetworkAlert> getAllNetworkAlerts(Pageable pageable) {
		return networkAlertDao.findAll(pageable);
	}

	public Page<NetworkAlert> getAllNetworkAlerts(Specification<NetworkAlert> spec, Pageable pageable) {
		return networkAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<NetworkAlertPageDTO> getNetworkAlerts(NetworkAlertSearchDTO networkAlertSearchDTO) {
	
			Integer networkAlertId = networkAlertSearchDTO.getNetworkAlertId(); 
  			String sortBy = networkAlertSearchDTO.getSortBy();
			String sortOrder = networkAlertSearchDTO.getSortOrder();
			String searchQuery = networkAlertSearchDTO.getSearchQuery();
			Integer page = networkAlertSearchDTO.getPage();
			Integer size = networkAlertSearchDTO.getSize();

	        Specification<NetworkAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, networkAlertId, "networkAlertId"); 
			
			

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

		Page<NetworkAlert> networkAlerts = this.getAllNetworkAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(networkAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(networkAlerts.getTotalPages()));
		
		List<NetworkAlert> networkAlertsList = networkAlerts.getContent();
		
		NetworkAlertConvertCriteriaDTO convertCriteria = new NetworkAlertConvertCriteriaDTO();
		List<NetworkAlertDTO> networkAlertDTOs = this.convertNetworkAlertsToNetworkAlertDTOs(networkAlertsList,convertCriteria);
		
		NetworkAlertPageDTO networkAlertPageDTO = new NetworkAlertPageDTO();
		networkAlertPageDTO.setNetworkAlerts(networkAlertDTOs);
		networkAlertPageDTO.setTotalElements(networkAlerts.getTotalElements());
		return ResponseEntity.ok(networkAlertPageDTO);
	}

	public List<NetworkAlertDTO> convertNetworkAlertsToNetworkAlertDTOs(List<NetworkAlert> networkAlerts, NetworkAlertConvertCriteriaDTO convertCriteria) {
		
		List<NetworkAlertDTO> networkAlertDTOs = new ArrayList<NetworkAlertDTO>();
		
		for (NetworkAlert networkAlert : networkAlerts) {
			networkAlertDTOs.add(convertNetworkAlertToNetworkAlertDTO(networkAlert,convertCriteria));
		}
		
		return networkAlertDTOs;

	}
	
	public NetworkAlertDTO convertNetworkAlertToNetworkAlertDTO(NetworkAlert networkAlert, NetworkAlertConvertCriteriaDTO convertCriteria) {
		
		NetworkAlertDTO networkAlertDTO = new NetworkAlertDTO();
		
		networkAlertDTO.setNetworkAlertId(networkAlert.getNetworkAlertId());

	
		networkAlertDTO.setTrafficLoad(networkAlert.getTrafficLoad());

	

		
		return networkAlertDTO;
	}

	public ResultDTO updateNetworkAlert(NetworkAlertDTO networkAlertDTO, RequestDTO requestDTO) {
		
		NetworkAlert networkAlert = networkAlertDao.getById(networkAlertDTO.getNetworkAlertId());

		networkAlert.setNetworkAlertId(ControllerUtils.setValue(networkAlert.getNetworkAlertId(), networkAlertDTO.getNetworkAlertId()));

		networkAlert.setTrafficLoad(ControllerUtils.setValue(networkAlert.getTrafficLoad(), networkAlertDTO.getTrafficLoad()));



        networkAlert = networkAlertDao.save(networkAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NetworkAlertDTO getNetworkAlertDTOById(Integer networkAlertId) {
	
		NetworkAlert networkAlert = networkAlertDao.getById(networkAlertId);
			
		
		NetworkAlertConvertCriteriaDTO convertCriteria = new NetworkAlertConvertCriteriaDTO();
		return(this.convertNetworkAlertToNetworkAlertDTO(networkAlert,convertCriteria));
	}







}
