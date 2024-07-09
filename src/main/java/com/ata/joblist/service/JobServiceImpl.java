package com.ata.joblist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.ata.joblist.dto.request.PageRequestDto;
import com.ata.joblist.dto.response.JobResponseDto;
import com.ata.joblist.entity.Job;
import com.ata.joblist.exception.InvalidParamException;
import com.ata.joblist.repository.JobRepo;
import com.ata.joblist.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService{
	
	@Autowired
	JobRepo jobRepo;
	
	
	@Override
	public void initDataset() {
		log.info("InitDataset");
		//check the job data already exist
		
		if(jobRepo.count()<=0) {
			log.info("No Dataset");
			List<Job> jobList = CommonUtils.JsonToJobEntity();
			log.debug("Job List {}", jobList.size());
			for(Job job: jobList) {
				jobRepo.save(job);
			}			
			
		}
		
		
	}


	@Override
	public List<JobResponseDto> getJobList(PageRequestDto pageRequestDto) {
		Pageable pageable = CommonUtils.createPageable(pageRequestDto);
		List<String> gteSalaryFilter = pageRequestDto.getFilters().get("salary[gte]");
//		List<String> salaryFilter = pageRequestDto.getFilters().get("salary");
		List<String> jobTitleFilter = pageRequestDto.getFilters().get("jobTitle");
		List<String> genderFilter = pageRequestDto.getFilters().get("gender");
		
		Double getSalary = null;
		if(gteSalaryFilter != null) {
			if(gteSalaryFilter!=null && gteSalaryFilter.size()==1) {
				getSalary = Double.parseDouble(gteSalaryFilter.get(0));
			}else {
				throw new InvalidParamException("Does not support Greater then salary field's value");
			}
		}
		
//		List<Double> salaryFilterDouble = salaryFilter.stream().map(Double::parseDouble)
//                .collect(Collectors.toList());
		
		
		return jobRepo.findJobByFilters(getSalary, jobTitleFilter, genderFilter, pageable);
	}

}
