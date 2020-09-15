package com.kh.oherp.repository;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.oherp.entity.Attendance_detailDto;
import com.kh.oherp.entity.Attendance_detailListVo;

public interface Attendance_detailDao {
// 근태상세 등록을 하겠뜸
	public boolean regist(Attendance_detailDto attendance_detailDto);
	
// 근태상세의 관리자만 조회가능한 페이지를 만들겠뜸
	 List<Attendance_detailListVo> list(Model model );
	 
// 근태상세에서 클릭시 오름.내림차순 부여	 
	 public List<Attendance_detailListVo> list(String col, String order);
	 
// admin_page에 있는 데이터 삭제
	 public void delete(int attendance_no); //번호를 알려줄테니깐 지우렴


//admin_page 조회검색
	 public List<Attendance_detailListVo> search(
			 @RequestParam (required = false) String type,
				@RequestParam (required = false) String keyword,
				Model model
				);
	 
//합계 증가를 만들거임
//	public Attendance_detailDto total(int attendance_total);
	 }