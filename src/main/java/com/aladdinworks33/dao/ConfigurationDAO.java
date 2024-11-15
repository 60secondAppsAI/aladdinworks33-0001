package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.Configuration;





public interface ConfigurationDAO extends GenericDAO<Configuration, Integer> {
  
	List<Configuration> findAll();
	






}


