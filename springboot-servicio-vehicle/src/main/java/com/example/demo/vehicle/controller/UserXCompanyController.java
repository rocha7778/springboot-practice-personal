package com.example.demo.vehicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vehicle.entity.model.UserXCompany;
import com.example.demo.vehicle.service.IUserXCompanyService;

@RestController
public class UserXCompanyController {
	@Autowired
	private IUserXCompanyService service;
	
	@GetMapping("/users/vehicles/action/query-vehicles-enabled")
	public List<UserXCompany> findVehicleEnableByUser(@RequestParam Long userId, @RequestParam Long companyId){
		return service.findByUserAndCompany(userId, companyId);
	}
}
