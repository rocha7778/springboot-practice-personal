package com.example.demo.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.vehicle.entity.model.UserXCompany;
import com.example.demo.vehicle.repository.UserXCompanyRepository;

@Service
public class UserXCompanyServiceImpl implements IUserXCompanyService {
	
	@Autowired
	private UserXCompanyRepository userXcompanyRepository;

	@Override
	public List<UserXCompany> findByUserAndCompany(Long  userId, Long compnayId) {
		return userXcompanyRepository.findByUserAndCompany(userId, compnayId);
	}
	

}
