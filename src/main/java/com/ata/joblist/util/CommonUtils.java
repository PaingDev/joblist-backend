package com.ata.joblist.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import com.ata.joblist.dto.request.PageRequestDto;
import com.ata.joblist.entity.Job;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {

	public static List<Job> JsonToJobEntity() {
		// read json file
		InputStream is = CommonUtils.class.getResourceAsStream("salary_survey-3.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> jsonList = mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {
			});

			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss");

			List<Job> jobList = new ArrayList<>();

			for (Map<String, Object> map : jsonList) {
				Job job = new Job();
				job.setAnnualStockValue(map.getOrDefault("Annual Stock Value/Bonus", "").toString());
				job.setAnnualBonus(map.getOrDefault("Annual Bonus", "").toString());
				job.setSigningBonus(map.getOrDefault("Signing Bonus", "").toString());

				job.setYearOfEmployer(map.getOrDefault("Years of Experience", "").toString());
				job.setYearOfExperience(map.getOrDefault("Years at Employer", "").toString());
				job.setJobTitle(map.getOrDefault("Job Title", "").toString());
				job.setLocation(map.getOrDefault("Location", "").toString());
				job.setEmployer(map.getOrDefault("Employer", "").toString());
				try {
					job.setTimestamp(LocalDateTime.parse(map.getOrDefault("Timestamp", "").toString(), formatter1));
				} catch (DateTimeParseException e) {
					job.setTimestamp(LocalDateTime.parse(map.getOrDefault("Timestamp", "").toString(), formatter2));
				}

				job.setGender(map.getOrDefault("Gender", "").toString());
				job.setAdditionalComments(map.getOrDefault("Additional Comments", "").toString());
				String salary = map.getOrDefault("Salary", "0").toString();
				salary = salary.replace("k", "000").replaceAll("[ ,]", "");

				try {
					job.setSalary(new BigDecimal(salary).doubleValue());
				} catch (NumberFormatException e) {
					// can't convert value, let decide as 0
					job.setSalary(new BigDecimal(0).doubleValue());
				}

				jobList.add(job);
			}
			return jobList;
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static Pageable createPageable(PageRequestDto pageRequestDto) {
		List<Sort.Order> sortOrders = new ArrayList<>();

		if (!CollectionUtils.isEmpty(pageRequestDto.getSorts())) {
			for (Map.Entry<String, String> e : pageRequestDto.getSorts().entrySet()) {
				if (e.getValue().equalsIgnoreCase("DESC")) {
					sortOrders.add(new Sort.Order(Sort.Direction.DESC, e.getKey()));
				} else if (e.getValue().equalsIgnoreCase("ASC")) {
					sortOrders.add(new Sort.Order(Sort.Direction.ASC, e.getKey()));
				} else {
					sortOrders.add(new Sort.Order(null, e.getKey()));
				}
			}
		}

		return new UnpagedSorted(Sort.by(sortOrders));

	}

}
