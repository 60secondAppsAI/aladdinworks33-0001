package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.PowerAlert;





public interface PowerAlertDAO extends GenericDAO<PowerAlert, Integer> {
  
	List<PowerAlert> findAll();
	






}


