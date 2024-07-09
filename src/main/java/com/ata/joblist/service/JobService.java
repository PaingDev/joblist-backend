package com.ata.joblist.service;

import java.util.List;

import com.ata.joblist.dto.request.PageRequestDto;
import com.ata.joblist.dto.response.JobResponseDto;
import com.ata.joblist.exception.InvalidParamException;

public interface JobService {

	void initDataset();

	List<JobResponseDto> getJobList(PageRequestDto pageRequestDto) throws InvalidParamException;

}
