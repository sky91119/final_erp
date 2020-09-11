<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 
 <style>
/*마진*/	
	.margin20 {
		margin: 20px 20px 20px 20px;
	}
	
/*패딩*/
	.padding32 {
		padding:32px;
	}	
	.padding32-bot{
		padding-bottom:32px;
	}
	.padding10{
		padding:10px;
	}
/*제목*/
	.title{
		font-size: 25px;
		text-align:left;
		padding-bottom:15px;
        }
/*총 요청수*/ 
	.sum-request{
		font-size:16px;
		text-align:right;
	}       
/*요청관리 상태*/
	.waiting{
         background-color: #D27300;
         margin: 0px 3px 0px 0px;
         padding: 2px 8px;
         color: #ffffff;
         text-align: center;
         font-size: 0.75rem;
         border-radius: 2px;
	}
	.ok{
	     background-color: #01853d;
         margin: 0px 3px 0px 0px;
         padding: 2px 8px;
         color: #ffffff;
         text-align: center;
         font-size: 0.75rem;
         border-radius: 2px;
	}
	.no{
	 	 background-color: #bf0920;
         margin: 0px 3px 0px 0px;
         padding: 2px 8px;
         color: #ffffff;
         text-align: center;
         font-size: 0.75rem;
         border-radius: 2px;
         #bf0920
	}
/*텍스트*/
	.left-font{
		text-align:left;
	}	
	
</style>

<div class="container-fluid">
	<div>
		<p class="title">근태관리목록</p>
	</div>
	
     <div class="row padding32-bot">
    	<div class="col-2">
    		<input type="date" class="form-control">
    	</div>
    
    <div class="col-2">
			<form action=${pageContext.request.contextPath}/attendance_detail/admin_page method="post">
				<select class="form-control" name="type" onchange="this.form.submit()">
					<option value="정상근태">
					정상적인 기록<br><br>
                                        근무중이거나 퇴근한 직원들의 기록을 볼 수 있습니다.</option>
					<option value="출근누락">
					출근 누락된 기록<br><br>
                                        결근한 직원들의 근무일정을 볼 수 있습니다.</option>
					<option value="퇴근누락">
					퇴근 누락된 기록<br><br>
                                         퇴근이 누락된 직원들의 출퇴근기록을 볼 수 있습니다.</option>
				</select>
				
			</form>
        </div>
        
          <div class="col-8 sum-request">
        	<span>총 시간 : 3</span><br>
        	<span>총 급여 : 3</span>
        </div>
        
        </div>
     <div>
     <br>
     	<table class="table left-font">
    <thead>
     			<tr>
	
	<th><a href="admin_page?col=attendance_no&order=desc">근태번호</a></th>
    <th><a href="admin_page?col=member_code&order=desc">사원번호</a></th>
   <!-- <th><a href="admin_page?col=member_name&order=desc">성명</a></th>-->
    <th><a href="admin_page?col=attendance_date&order=desc">날짜</a></th>
   <th>출근시간</a></th>
   <th>퇴근시간</a></th>
   <th>근무노트</th>
   <th>합계</th>
  

  </tr>
  </thead>
  <tbody>
   
		<c:forEach var="Attendance_detailListVo" items="${list}">
			<tr>
				<td>${Attendance_detailListVo.attendance_no}</td>
				
				<td><a href ="admin_edit/${attendance_detailListVo.member_code}">
				${Attendance_detailListVo.member_code}</a></td>
				
				<!-- <td>${Attendance_detailListVo.member_name}</td>-->
				
				<td>${Attendance_detailListVo.attendance_date}</td>
				
				<!--<td>
     						<fmt:parseDate value="${Attendance_detailListVo.attendance_date}" 
							var="time" pattern="yyyy-MM-dd"/>
							<fmt:formatDate value="${time}" pattern="MM/dd hh:mm a"/>
     					</td>-->
				
				<td>${Attendance_detailListVo.attendance_in_time1} :
				${Attendance_detailListVo.attendance_in_time2}
				${Attendance_detailListVo.attendance_in_time3}</td>
				
				<td>${Attendance_detailListVo.attendance_out_time1} :
				${Attendance_detailListVo.attendance_out_time2}
				${Attendance_detailListVo.attendance_out_time3}</td>
				
				<td>${Attendance_detailListVo.attendance_note}</td>
				<td>${Attendance_detailListVo.attendance_total}</td>
				
				<td>
				<a href = "admin_delete/${attendance_detailListVo.member_code}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</div>
</div>
