package com.aladdinworks33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PowerAlertPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<PowerAlertDTO> powerAlerts;
}





