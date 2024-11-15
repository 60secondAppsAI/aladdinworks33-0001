package com.aladdinworks33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks33.domain.MaintenanceTask;
import com.aladdinworks33.dto.MaintenanceTaskDTO;
import com.aladdinworks33.dto.MaintenanceTaskSearchDTO;
import com.aladdinworks33.dto.MaintenanceTaskPageDTO;
import com.aladdinworks33.dto.MaintenanceTaskConvertCriteriaDTO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceTaskService extends GenericService<MaintenanceTask, Integer> {

	List<MaintenanceTask> findAll();

	ResultDTO addMaintenanceTask(MaintenanceTaskDTO maintenanceTaskDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceTask(MaintenanceTaskDTO maintenanceTaskDTO, RequestDTO requestDTO);

    Page<MaintenanceTask> getAllMaintenanceTasks(Pageable pageable);

    Page<MaintenanceTask> getAllMaintenanceTasks(Specification<MaintenanceTask> spec, Pageable pageable);

	ResponseEntity<MaintenanceTaskPageDTO> getMaintenanceTasks(MaintenanceTaskSearchDTO maintenanceTaskSearchDTO);
	
	List<MaintenanceTaskDTO> convertMaintenanceTasksToMaintenanceTaskDTOs(List<MaintenanceTask> maintenanceTasks, MaintenanceTaskConvertCriteriaDTO convertCriteria);

	MaintenanceTaskDTO getMaintenanceTaskDTOById(Integer maintenanceTaskId);







}





