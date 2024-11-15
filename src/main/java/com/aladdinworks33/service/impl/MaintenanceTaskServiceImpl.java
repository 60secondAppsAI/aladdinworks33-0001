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
import com.aladdinworks33.dao.MaintenanceTaskDAO;
import com.aladdinworks33.domain.MaintenanceTask;
import com.aladdinworks33.dto.MaintenanceTaskDTO;
import com.aladdinworks33.dto.MaintenanceTaskSearchDTO;
import com.aladdinworks33.dto.MaintenanceTaskPageDTO;
import com.aladdinworks33.dto.MaintenanceTaskConvertCriteriaDTO;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import com.aladdinworks33.service.MaintenanceTaskService;
import com.aladdinworks33.util.ControllerUtils;





@Service
public class MaintenanceTaskServiceImpl extends GenericServiceImpl<MaintenanceTask, Integer> implements MaintenanceTaskService {

    private final static Logger logger = LoggerFactory.getLogger(MaintenanceTaskServiceImpl.class);

	@Autowired
	MaintenanceTaskDAO maintenanceTaskDao;

	


	@Override
	public GenericDAO<MaintenanceTask, Integer> getDAO() {
		return (GenericDAO<MaintenanceTask, Integer>) maintenanceTaskDao;
	}
	
	public List<MaintenanceTask> findAll () {
		List<MaintenanceTask> maintenanceTasks = maintenanceTaskDao.findAll();
		
		return maintenanceTasks;	
		
	}

	public ResultDTO addMaintenanceTask(MaintenanceTaskDTO maintenanceTaskDTO, RequestDTO requestDTO) {

		MaintenanceTask maintenanceTask = new MaintenanceTask();

		maintenanceTask.setMaintenanceTaskId(maintenanceTaskDTO.getMaintenanceTaskId());


		maintenanceTask.setDescription(maintenanceTaskDTO.getDescription());


		maintenanceTask.setDueDate(maintenanceTaskDTO.getDueDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		maintenanceTask = maintenanceTaskDao.save(maintenanceTask);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MaintenanceTask> getAllMaintenanceTasks(Pageable pageable) {
		return maintenanceTaskDao.findAll(pageable);
	}

	public Page<MaintenanceTask> getAllMaintenanceTasks(Specification<MaintenanceTask> spec, Pageable pageable) {
		return maintenanceTaskDao.findAll(spec, pageable);
	}

	public ResponseEntity<MaintenanceTaskPageDTO> getMaintenanceTasks(MaintenanceTaskSearchDTO maintenanceTaskSearchDTO) {
	
			Integer maintenanceTaskId = maintenanceTaskSearchDTO.getMaintenanceTaskId(); 
 			String description = maintenanceTaskSearchDTO.getDescription(); 
   			String sortBy = maintenanceTaskSearchDTO.getSortBy();
			String sortOrder = maintenanceTaskSearchDTO.getSortOrder();
			String searchQuery = maintenanceTaskSearchDTO.getSearchQuery();
			Integer page = maintenanceTaskSearchDTO.getPage();
			Integer size = maintenanceTaskSearchDTO.getSize();

	        Specification<MaintenanceTask> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, maintenanceTaskId, "maintenanceTaskId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MaintenanceTask> maintenanceTasks = this.getAllMaintenanceTasks(spec, pageable);
		
		//System.out.println(String.valueOf(maintenanceTasks.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(maintenanceTasks.getTotalPages()));
		
		List<MaintenanceTask> maintenanceTasksList = maintenanceTasks.getContent();
		
		MaintenanceTaskConvertCriteriaDTO convertCriteria = new MaintenanceTaskConvertCriteriaDTO();
		List<MaintenanceTaskDTO> maintenanceTaskDTOs = this.convertMaintenanceTasksToMaintenanceTaskDTOs(maintenanceTasksList,convertCriteria);
		
		MaintenanceTaskPageDTO maintenanceTaskPageDTO = new MaintenanceTaskPageDTO();
		maintenanceTaskPageDTO.setMaintenanceTasks(maintenanceTaskDTOs);
		maintenanceTaskPageDTO.setTotalElements(maintenanceTasks.getTotalElements());
		return ResponseEntity.ok(maintenanceTaskPageDTO);
	}

	public List<MaintenanceTaskDTO> convertMaintenanceTasksToMaintenanceTaskDTOs(List<MaintenanceTask> maintenanceTasks, MaintenanceTaskConvertCriteriaDTO convertCriteria) {
		
		List<MaintenanceTaskDTO> maintenanceTaskDTOs = new ArrayList<MaintenanceTaskDTO>();
		
		for (MaintenanceTask maintenanceTask : maintenanceTasks) {
			maintenanceTaskDTOs.add(convertMaintenanceTaskToMaintenanceTaskDTO(maintenanceTask,convertCriteria));
		}
		
		return maintenanceTaskDTOs;

	}
	
	public MaintenanceTaskDTO convertMaintenanceTaskToMaintenanceTaskDTO(MaintenanceTask maintenanceTask, MaintenanceTaskConvertCriteriaDTO convertCriteria) {
		
		MaintenanceTaskDTO maintenanceTaskDTO = new MaintenanceTaskDTO();
		
		maintenanceTaskDTO.setMaintenanceTaskId(maintenanceTask.getMaintenanceTaskId());

	
		maintenanceTaskDTO.setDescription(maintenanceTask.getDescription());

	
		maintenanceTaskDTO.setDueDate(maintenanceTask.getDueDate());

	

		
		return maintenanceTaskDTO;
	}

	public ResultDTO updateMaintenanceTask(MaintenanceTaskDTO maintenanceTaskDTO, RequestDTO requestDTO) {
		
		MaintenanceTask maintenanceTask = maintenanceTaskDao.getById(maintenanceTaskDTO.getMaintenanceTaskId());

		maintenanceTask.setMaintenanceTaskId(ControllerUtils.setValue(maintenanceTask.getMaintenanceTaskId(), maintenanceTaskDTO.getMaintenanceTaskId()));

		maintenanceTask.setDescription(ControllerUtils.setValue(maintenanceTask.getDescription(), maintenanceTaskDTO.getDescription()));

		maintenanceTask.setDueDate(ControllerUtils.setValue(maintenanceTask.getDueDate(), maintenanceTaskDTO.getDueDate()));



        maintenanceTask = maintenanceTaskDao.save(maintenanceTask);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MaintenanceTaskDTO getMaintenanceTaskDTOById(Integer maintenanceTaskId) {
	
		MaintenanceTask maintenanceTask = maintenanceTaskDao.getById(maintenanceTaskId);
			
		
		MaintenanceTaskConvertCriteriaDTO convertCriteria = new MaintenanceTaskConvertCriteriaDTO();
		return(this.convertMaintenanceTaskToMaintenanceTaskDTO(maintenanceTask,convertCriteria));
	}







}
