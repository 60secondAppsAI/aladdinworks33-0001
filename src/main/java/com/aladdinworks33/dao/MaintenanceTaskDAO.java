package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.MaintenanceTask;





public interface MaintenanceTaskDAO extends GenericDAO<MaintenanceTask, Integer> {
  
	List<MaintenanceTask> findAll();
	






}


