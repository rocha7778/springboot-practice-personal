package com.example.demo.vehicle.service;

import java.util.List;

import com.example.demo.vehicle.entity.model.UserXCompany;

public interface IUserXCompanyService {
	
	public List<UserXCompany> findByUserAndCompany(Long  userId, Long compnayId);

}
