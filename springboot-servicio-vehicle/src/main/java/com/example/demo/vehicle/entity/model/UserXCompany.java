/**
 * 
 */
package com.example.demo.vehicle.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sun.istack.Nullable;

import lombok.Getter;
import lombok.Setter;

/*
* */

@Getter
@Setter
@Entity
@Table(name = "user_x_company")
@NamedQuery(
		name = "UserXCompany.countByIdCompany",
		query = "Select count(p) from UserXCompany p where p.company = ?1 and enabled = 1"
		)
public class UserXCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "user")
	private Long user;

	@NotNull
	@Column(name = "company")
	private Long company;

	@NotNull
	@Column(name = "enabled")
	private int enabled;
	
	@Nullable
	@Column(name= "isallowedbike" )
	private int isallowedbike;
	
	@Column(name= "isallowedtsc" )
	private int isallowedtsc;
	
	@Column(name= "isallowedskateboard" )
	private int isallowedskateboard;
}