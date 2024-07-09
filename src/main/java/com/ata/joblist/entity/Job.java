package com.ata.joblist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="job_id")
	private long jobId;
	
	private LocalDateTime timestamp;
	
	private String employer;
	
	private String location;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="year_of_employee")
	private String yearOfEmployer;
	
	@Column(name="year_of_experience")
	private String yearOfExperience;
	
	
	private double salary;
	
	@Column(name="signing_bonus")
	private String signingBonus;
	
	@Column(name="annual_bonus")
	private String annualBonus;
	
	@Column(name="annual_stock_value")
	private String annualStockValue;
	
	private String gender;
	
	@Column(name="additional_comments", columnDefinition="TEXT")
	private String additionalComments;
	

}
