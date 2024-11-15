package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.CoolingAlert;





public interface CoolingAlertDAO extends GenericDAO<CoolingAlert, Integer> {
  
	List<CoolingAlert> findAll();
	






}


