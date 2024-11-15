package com.aladdinworks33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks33.domain.Generator;
import com.aladdinworks33.dto.GeneratorDTO;
import com.aladdinworks33.dto.GeneratorSearchDTO;
import com.aladdinworks33.dto.GeneratorPageDTO;
import com.aladdinworks33.dto.GeneratorConvertCriteriaDTO;
import com.aladdinworks33.service.GenericService;
import com.aladdinworks33.dto.common.RequestDTO;
import com.aladdinworks33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface GeneratorService extends GenericService<Generator, Integer> {

	List<Generator> findAll();

	ResultDTO addGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO);

	ResultDTO updateGenerator(GeneratorDTO generatorDTO, RequestDTO requestDTO);

    Page<Generator> getAllGenerators(Pageable pageable);

    Page<Generator> getAllGenerators(Specification<Generator> spec, Pageable pageable);

	ResponseEntity<GeneratorPageDTO> getGenerators(GeneratorSearchDTO generatorSearchDTO);
	
	List<GeneratorDTO> convertGeneratorsToGeneratorDTOs(List<Generator> generators, GeneratorConvertCriteriaDTO convertCriteria);

	GeneratorDTO getGeneratorDTOById(Integer generatorId);







}





