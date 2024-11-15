package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.CapacityAlert;





public interface CapacityAlertDAO extends GenericDAO<CapacityAlert, Integer> {
  
	List<CapacityAlert> findAll();
	






}


