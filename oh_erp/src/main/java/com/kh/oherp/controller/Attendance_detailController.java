package com.kh.oherp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.oherp.entity.Attendance_detailDto;
import com.kh.oherp.entity.Attendance_detailListVo;
import com.kh.oherp.entity.Attendance_countDto;
import com.kh.oherp.repository.Attendance_detailDao;


@Controller
@RequestMapping("/attendance_detail")
@CrossOrigin("*") //아무나 들어와도 데이터를 주겠다. 
public class Attendance_detailController {

   
   @Autowired
   private Attendance_detailDao attendance_detailDao; 
   
   //등록
   @GetMapping("/regist")
   public String regist() {
      return "attendance_detail/regist";
   } 

   @PostMapping("/regist")
   public String regist(
         @ModelAttribute Attendance_detailDto attendance_detailDto) {
   //String no = attendance_detailDao.detail_regist(attendance_detailDto);
   //   return "redirect:attendance?attendance_no="+no;
   boolean result = attendance_detailDao.regist(attendance_detailDto);
   if(result) {
      return "redirect:admin_page";   
   }   
   else {
      return "redirect:regist?error";   
   }
   }
   ////////////////////////////////////////////////////////////////////////////
   //관리자 페이지
@GetMapping("/admin_page")
   public String admin_page(Model model,
        @RequestParam (required = false, defaultValue = "member_code") String col, //defaultValue =기본값 설정을 member_code라고 주겠다. 
                                                              
        @RequestParam (required = false, defaultValue= "asc") String order //너가 아무말도 없으면 내가 코드를 오름차순으로 보여주겠다. 
         ){ //나는 col과 order라는 값을 추가로 받겠다. 

    List<Attendance_detailListVo> list = attendance_detailDao.list(col, order);
     model.addAttribute("list", list);
   
      return "/attendance_detail/admin_page";
 }

//출근누락페이지 
@GetMapping("/in")
public String in(Model model) {
 
List<Attendance_detailListVo> list = attendance_detailDao.in(model);
 model.addAttribute("list", list);

  return "/attendance_detail/in";
}

//퇴근누락페이지 
@GetMapping("/out")
public String out(Model model) {

List<Attendance_detailListVo> list = attendance_detailDao.out(model);
model.addAttribute("list", list);

return "/attendance_detail/out";
}


   
   //기록나누기실시
 //@GetMapping("/admin_page")
 //public String admin_page(Model model,
 //    @RequestParam (required = false, defaultValue = "member_code"); //defaultValue =기본값 설정을 member_code라고 주겠다. 
                                                              
 //          @RequestParam (required = false, defaultValue= "asc") String order //너가 아무말도 없으면 내가 코드를 오름차순으로 보여주겠다. 
 //          ){ //나는 col과 order라는 값을 추가로 받겠다. 

//       List<Attendance_detailListVo> list = attendance_detailDao.list(col, order);
 //       model.addAttribute("list", list);
   
  //      return "/attendance_detail/admin_page";
// }
   

// @GetMapping("/admin_page")
//  public String admin_page(Model model){ 
 
//   List<Attendance_detailListVo> in_null = Attendance_detailDao.in_null(model);
// model.addAttribute("in_null", in_null);
//  return "attendance_detail/admin_page";


//수정
 @GetMapping("/edit/{attendance_no}")
 public String edit(@PathVariable int attendance_no,
		 Model model) {
	 model.addAttribute("attendance_no", attendance_no);
	 return "/attendance_detail/edit";
 }


 @PostMapping("/{attendance_no}")
 public String edit(@PathVariable int attendance_no, 
		 @ModelAttribute Attendance_detailDto attendance_detailDto) {
	 attendance_detailDao.edit(attendance_detailDto);
	 return "redirect:/attendance_detail/admin_page";
 }
 
 //삭제
	 
@GetMapping("/delete/{attendance_no}")
public String delete(@PathVariable int attendance_no) {
	attendance_detailDao.delete(attendance_no);
   return "redirect:/attendance_detail/admin_page";
  
}

//검색
@RequestMapping("/search")
public String search(
		@RequestParam (required = false) String type,
		@RequestParam (required = false) String keyword,
		Model model
		) {
	attendance_detailDao.search(type, keyword, model);

	return "attendance_detail/admin_page";
}


//@GetMapping("/admin_page")
//public String admin_page() {
//	return "redirect:/attendance_detail/admin_page";
//}

@GetMapping("/rank_page")
public String rank_page() {
	return "attendane_detail/rank_page";
}


//rank_page
//@GetMapping("/rank_page")
//public String rank_page() {
//	return "attendance_detail/rank_page";
//}

//count_page
//@GetMapping("/count_page")
//public String count_page(Model model
//		) {
//	List<Attendance_countDto> count = attendance_detailDao.count(model);
//	model.addAttribute("count", count);
//	return "attendance_detail/count_page";
	
//}

//count_page 통계
//@GetMapping("/count_page")
//public Map<String, Integer> count_page(){
//	Map<String, Integer> map = new HashMap<>();
//	map.put("2020-08", 3);
 //   map.put("2020-09", 5);	
 //   return map;
//}

//@GetMapping("/rank_page")
//public List<Attendance_StatsVo> rank_page(){
//	List<Attendance_StatsVo> list = new ArrayList<>();
//	list.add(Attendance_StatsVo.builder().month("2020-01").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-02").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-03").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-04").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-05").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-06").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-07").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-08").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-09").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-10").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-11").count(3).build());
//	list.add(Attendance_StatsVo.builder().month("2020-12").count(3).build());
//	return list;
	
//}


//@GetMapping("/admin_page")
//   public String admin_page(Model model,
//
//    ){ 
//  model.addAttribute("list", list);
// attendance_detailDao.list(model);
// return "attendance_detail/admin_page";
//}



//관리자만 볼 수 있는 조회페이지
//@PostMapping("/admin_page")
//public String admin_page(
//List<Attendance_detailDto> admin_page = attendance_detailDao.
      
      
//@ModelAttribute Attendance_detailDto attendance_detailDto) {
//String no = attendance_detailDao.admin_page(attendance_detailDto);
//   
//         return "redirect:attendance/admin_page";
//   }
   
   


}