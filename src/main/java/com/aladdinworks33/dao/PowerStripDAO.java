package com.aladdinworks33.dao;

import java.util.List;

import com.aladdinworks33.dao.GenericDAO;
import com.aladdinworks33.domain.PowerStrip;





public interface PowerStripDAO extends GenericDAO<PowerStrip, Integer> {
  
	List<PowerStrip> findAll();
	






}


