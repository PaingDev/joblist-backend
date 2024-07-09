package com.ata.joblist.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ata.joblist.dto.response.JobResponseDto;
import com.ata.joblist.entity.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Long>{

//	@Query(value = "SELECT new com.ata.joblist.dto.response.JobResponseDto(j) FROM Job j WHERE"
//			+ "?1 IS NULL OR j.salary>?1 AND "
//			+ "?2 IS NULL OR j.salary IN ?2 AND "
//			+ "?3 IS NULL OR j.jobTitle IN ?3 AND "
//			+ "?4 IS NULL OR j.gender IN ?4")
//	List<JobResponseDto> findJobByFilters(Double gteSalary, List<Double> salary, List<String> jobTitle,
//			List<String> genderFilter, Pageable pageable);
	
	
	@Query(value = "SELECT new com.ata.joblist.dto.response.JobResponseDto(j) FROM Job j WHERE"
			+ "?1 IS NULL OR j.salary>?1 AND "			
			+ "?2 IS NULL OR j.jobTitle IN ?2 AND "
			+ "?3 IS NULL OR j.gender IN ?3")
	List<JobResponseDto> findJobByFilters(Double gteSalary, List<String> jobTitle,
			List<String> genderFilter, Pageable pageable);
	
//	"LEFT JOIN pv.product p "
//	+ "LEFT JOIN p.category c "
//	+ "LEFT JOIN p.varientGroups "
//	+ "WHERE (?1 IS NULL OR p.productName LIKE ?1%) AND "
//	+ "(?2 IS NULL OR c.category IN ?2) AND "
//	+ "(?3 IS NULL OR p.shop.shopName IN ?3) "
//	+ "GROUP BY pv.productVarientId")

}
