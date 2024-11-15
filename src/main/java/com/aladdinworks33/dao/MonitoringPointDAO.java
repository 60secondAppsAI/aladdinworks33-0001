package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.MonitoringPoint;





public interface MonitoringPointDAO extends GenericDAO<MonitoringPoint, Integer> {
  
	List<MonitoringPoint> findAll();
	






}


