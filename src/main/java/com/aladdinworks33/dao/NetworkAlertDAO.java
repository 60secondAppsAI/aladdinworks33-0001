package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.NetworkAlert;





public interface NetworkAlertDAO extends GenericDAO<NetworkAlert, Integer> {
  
	List<NetworkAlert> findAll();
	






}


