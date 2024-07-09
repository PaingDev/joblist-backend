package com.ata.joblist.cotroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ata.joblist.dto.request.PageRequestDto;
import com.ata.joblist.dto.response.JobResponseDto;
import com.ata.joblist.service.JobFilterService;
import com.ata.joblist.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	JobFilterService jobFilterService;
	
	
	@PostMapping("")
	ResponseEntity<MappingJacksonValue> getJobList(@RequestBody PageRequestDto pageRequestDto){
		List<JobResponseDto> jobResponseDtoList = jobService.getJobList(pageRequestDto);		
		MappingJacksonValue mappingValue = jobFilterService.filterJobs(jobResponseDtoList, pageRequestDto.getFields());
		System.out.println(mappingValue);
		return ResponseEntity.ok(mappingValue);
	}
	

}
