package com.kh.oherp.repository;

import java.util.List;
import java.util.Map;

import com.kh.oherp.entity.MemberDto;
import com.kh.oherp.entity.SalaryDto;

public interface SalaryDao {
	void insert(SalaryDto salaryDto);
	
	List<MemberDto> get_member();
	
	List member_salary(Map<String,Object>map);

	List total_salary(Map<String,Object>map);
	
	int salary_count(Map<String,Object>map); //게시글 수
}

