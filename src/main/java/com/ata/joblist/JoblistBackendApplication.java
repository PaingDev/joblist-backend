package com.ata.joblist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ata.joblist.service.JobService;

@SpringBootApplication
public class JoblistBackendApplication implements CommandLineRunner{

	@Autowired
	JobService jobService;
	
	public static void main(String[] args) {		
		SpringApplication.run(JoblistBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jobService.initDataset();
	}

}
