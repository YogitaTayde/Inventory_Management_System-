package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LowStockAlertDTO;
import com.example.demo.service.AlertService;
@RestController 
public class AlertController {
	
	@Autowired
	AlertService service;
	@GetMapping("/companies/{companyId}/alerts/low-stock")
	public ResponseEntity<?> getAlerts(@PathVariable Long companyId) {
	    List<LowStockAlertDTO> alerts = service.getLowStock(companyId);

	    return ResponseEntity.ok(Map.of(
	            "alerts", alerts,
	            "total_alerts", alerts.size()
	    ));
	}

}
