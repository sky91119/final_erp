package com.kh.oherp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.oherp.entity.MemberDto;
import com.kh.oherp.entity.SalaryDto;

@Repository
public class SalaryDaoImpl implements SalaryDao{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insert(SalaryDto salaryDto) {
		sqlSession.insert("salary.salary_add",salaryDto);
	}
	@Override
	public List<MemberDto> get_member() {
		List<MemberDto> member = sqlSession.selectList("salary.get_member");
		return member;
	}
	@Override
	public List member_salary(Map<String,Object>map) {
		List member_salary = sqlSession.selectList("salary.member_salary",map);
		return member_salary;
	}
	@Override
	public int salary_count(Map<String,Object>map) {
			int salary_count=sqlSession.selectOne("salary.salary_count",map);
			return salary_count;
	
	}
	@Override
	public List total_salary(Map<String, Object> map) {
		List total_salary = sqlSession.selectList("salary.total_salary",map);
		return total_salary;
	}
}
