package com.kh.oherp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.oherp.entity.MemberDto;

@Controller
@RequestMapping("/")
public class IndexController {
	
	
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private PasswordEncoder encoder;
	
   @GetMapping("/")
   public String index() {
      return "index";
   }
   
   @PostMapping("/login")
	public String login(
			@ModelAttribute MemberDto memberDto,
			HttpSession session) {
//		[1] DB에서 해당 회원의 정보를 모두 불러온다
		MemberDto find = sqlSession.selectOne(
						"member.getMemberCode", memberDto.getMember_code());
		if(find != null) {//아이디가 있으면
//			[2] 아이디가 있을 경우 비밀번호 비교를 수행한다(encoder 사용)
//			encoder.matches(입력 PW, DB PW) --> boolean
			boolean pass = encoder.matches(
					memberDto.getMember_pw(), find.getMember_pw());
			if(pass) {//비밀번호 일치
//				[3] 비밀번호도 맞을 경우 메인 페이지로 이동 및 세션 작업
				session.setAttribute("userinfo", find);
				return "redirect:/";
			}
		}

		//그 외의 모든 경우는 오류로 간주
		return "redirect:login?error";
	}
   
   @GetMapping("/logout")
  String logout() {
	   return "member/logout";
   }

}