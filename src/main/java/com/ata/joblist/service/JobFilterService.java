package com.ata.joblist.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.ata.joblist.dto.response.JobResponseDto;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class JobFilterService {

	private static final String JOB_FILTER = "JobFilter";

	public MappingJacksonValue filterJobs(List<JobResponseDto> jobs, String fields) {
		Set<String> fieldSet = Arrays.stream(fields.split(",")).collect(Collectors.toSet());

		SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter(JOB_FILTER,
				SimpleBeanPropertyFilter.filterOutAllExcept(fieldSet));

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(jobs);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
}