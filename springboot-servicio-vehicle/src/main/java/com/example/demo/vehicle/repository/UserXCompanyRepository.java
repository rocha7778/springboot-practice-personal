package com.example.demo.vehicle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vehicle.entity.model.UserXCompany;

@Repository
public interface UserXCompanyRepository  extends CrudRepository<UserXCompany, Long>{
	
	public List<UserXCompany> findByUserAndCompany(Long  user, Long compnay);

}
