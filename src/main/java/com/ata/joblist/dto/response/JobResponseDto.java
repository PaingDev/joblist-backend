package com.ata.joblist.dto.response;

import java.time.LocalDateTime;

import com.ata.joblist.entity.Job;
import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("JobFilter")
public class JobResponseDto {

	private long jobId;

	private LocalDateTime timestamp;

	private  String employer;

	private String location;

	private String jobTitle;

	private String yearOfEmployer;

	private String yearOfExperience;

	private double salary;

	private String signingBonus;

	private String annualBonus;

	private String annualStockValue;

	private String gender;

	private String additionalComments;
	
	public JobResponseDto(Job job) {
		this.jobId = job.getJobId();
		this.timestamp = job.getTimestamp();
		this.employer = job.getEmployer();
		this.location = job.getLocation();
		this.jobTitle = job.getJobTitle();
		this.yearOfEmployer = job.getYearOfEmployer();
		this.yearOfExperience = job.getYearOfExperience();
		this.salary = job.getSalary();
		this.signingBonus = job.getSigningBonus();
		this.annualBonus = job.getAnnualBonus();
		this.annualStockValue = job.getAnnualStockValue();
		this.gender = job.getGender();
		this.additionalComments = job.getAdditionalComments();
	}

}
