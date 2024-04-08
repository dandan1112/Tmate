<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>맘이랜서</title>
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
    <header>
        <nav class="flex-center">
            <a href="/userList">회원</a>
            <a href="/deviceList">디바이스</a>
            <a href="/logListUser" class="on">로그</a>
        </nav>
    </header>
    <main>
        <div class="mainTitle flex-both">
            <h2>로그</h2>
            <!-- 탭 리스트 -->
            <ul class="tab-list switch type-01 flex">
                <li class="active" data-tab="tab1" id="s_tab1">
	                    회원로그
                </li>
                <li data-tab="tab2" id="s_tab2">
	                    디바이스 로그
                </li>
            </ul>
        </div>

		<form action="" id="frm" method="POST">
			<input type="hidden" id="tab_id" name="tab_id"  value="${tab_id}">
			<input type="hidden" id="searchText" name="searchText" value="${searchText }">
			<input type="hidden" id="startDate" name="startDate" value="${startDate }">
			<input type="hidden" id="endDate" name="endDate" value="${endDate }">
			<input type="hidden" id="PAGE_NUMBER" name="PAGE_NUMBER">
		</form>
	                
        <!-- 회원 로그 탭내용 -->
        <div class="mainCont tab-cont active" id="tab1">
            <div class="mainCont__tit flex-both">
                <h3 class="b">회원 로그</h3>
                <div class="inpBox flex">
                	
                	<span>조회기간&nbsp;
                		<input type="text" id="startDate1" placeholder="yyyy-mm-dd" style="margin:0 5px 0 5px; width: 120px;">
                	</span>
                	<span>&nbsp;&nbsp;~&nbsp;
                		<input type="text" id="endDate1" placeholder="yyyy-mm-dd" style="margin:0 5px 0 5px; width: 120px;">
                	</span>
					<span style="margin:0 0 0 10px;">조회내용&nbsp;
						<input type="text" class="w_314 m_r_10" id="searchText_U" name="searchText_U" placeholder="로그내용,  이메일" style="margin:0 0 0 5px;">
					</span>
                    <button type="button" class="btn btn_gray" onClick="fnSearch('U', 1)"><img alt="검색" src="/resources/img/ico_search.svg">검색</button>
                </div>
            </div>

            <table class="type-01">
                <colgroup>
                    <col style="width: 5%;">
                    <col style="width: auto;">
                    <col style="width: 15%;">
                    <col style="width: 15%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>로그내용</th>
                        <th>이메일주소</th>
                        <th>등록시간</th>
                    </tr>
                </thead>
                <tbody>
   					<c:choose>
						<c:when test="${not empty logList }">
		                    <c:forEach var="itm" items="${logList }" varStatus="status">
		                    <tr>
		                        <td>${itm.ROWNUM}</td>
		                        <td class="taL">${itm.log_content}</td>
		                        <td>${itm.eml_addr}</td>
		                        <td>${itm.log_tm}</td>
		                    </tr>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">일치하는 데이터가 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
                </tbody>
                </tbody>
            </table>

			<c:if test="${not empty logList }">
				<div class="pagination">
				    <ul class="flex-center">
					        <li class="start">
								<a onclick="fnSearch('U', 1)">처음으로</a>
					        </li>
					        <li class="prev ${pagingInfo.CURRENT_BLOCK == 1 ? 'disabled' :''}">
								<a onclick="fnSearch('U', ${pagingInfo.PREV_PAGE})">이전</a>
					        </li>
						    <c:forEach var="i" begin="${pagingInfo.BEGIN_BLOCK}" end="${pagingInfo.END_BLOCK}">
						        <li class="${i == pagingInfo.PAGE_NUMBER ? 'active' : ''}">
						            <a onclick="fnSearch('U', ${i})">${i}</a>
						        </li>
						    </c:forEach>
					        <li class="next ${pagingInfo.CURRENT_BLOCK == pagingInfo.TOTAL_BLOCK ? 'disabled' :''}">
								<a onclick="fnSearch('U', ${pagingInfo.NEXT_PAGE})">이전</a>
					        </li>
					        <li class="end">
								<a onclick="fnSearch('U', ${pagingInfo.TOTAL_PAGE})">끝으로</a>
					        </li>
				    </ul>
				</div>
			</c:if>
        </div>
        
        <!-- 디바이스 로그 탭내용 -->
        <div class="mainCont tab-cont" id="tab2">
            <div class="mainCont__tit flex-both">
                <h3 class="b">디바이스 로그</h3>
                <div class="inpBox flex">
                	<span>조회기간&nbsp;
                		<input type="text" id="startDate2" placeholder="yyyy-mm-dd" style="margin:0 5px 0 5px; width: 120px;">
                	</span>
                	<span>&nbsp;&nbsp;~&nbsp;
                		<input type="text" id="endDate2" placeholder="yyyy-mm-dd" style="margin:0 5px 0 5px; width: 120px;">
                	</span>
					<span style="margin:0 0 0 10px;">조회내용&nbsp;
						<input type="text" class="w_314 m_r_10" id="searchText_D" name="searchText_D" placeholder="로그내용,  이메일" style="margin:0 0 0 5px;">
					</span>
                    <button type="button" class="btn btn_gray" onclick="fnSearch('D', 1)"><img alt="검색" src="/resources/img/ico_search.svg">검색</button>
                </div>
            </div>

            <table class="type-01">
                <colgroup>
                    <col style="width: 5%;">
                    <col style="width: auto;">
                    <col style="width: 15%;">
                    <col style="width: 15%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>로그내용</th>
                        <th>이메일주소</th>
                        <th>등록시간</th>
                    </tr>
                </thead>
                <tbody>
   					<c:choose>
						<c:when test="${not empty logList }">
		                    <c:forEach var="itm" items="${logList }" varStatus="status">
		                    <tr>
		                        <td>${itm.ROWNUM}</td>
		                        <td class="taL">${itm.log_content}</td>
		                        <td>${itm.eml_addr}</td>
		                        <td>${itm.log_tm}</td>
		                    </tr>
		                    </c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">일치하는 데이터가 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
                </tbody>
            </table>
	    	<c:if test="${not empty logList }">
				<div class="pagination">
				    <ul class="flex-center">
					        <li class="start">
								<a onclick="fnSearch('D', 1)">처음으로</a>
					        </li>
					        <li class="prev ${pagingInfo.CURRENT_BLOCK == 1 ? 'disabled' :''}">
								<a onclick="fnSearch('D', ${pagingInfo.PREV_PAGE})">이전</a>  
					        </li>
						    <c:forEach var="i" begin="${pagingInfo.BEGIN_BLOCK}" end="${pagingInfo.END_BLOCK}">
						        <li class="${i == pagingInfo.PAGE_NUMBER ? 'active' : ''}">
						            <a onclick="fnSearch('D', ${i})">${i}</a>
						        </li>
						    </c:forEach>
					        <li class="next ${pagingInfo.CURRENT_BLOCK == pagingInfo.TOTAL_BLOCK ? 'disabled' :''}">
								<a onclick="fnSearch('D', ${pagingInfo.NEXT_PAGE})">이전</a>
					        </li>
					        <li class="end">
								<a onclick="fnSearch('D', ${pagingInfo.TOTAL_PAGE})">끝으로</a>
					        </li>
				    </ul>
				</div>
	        </c:if>
        </div>
    </main>

    <script>
    
	    $(function(){
	    	// datePicker 세팅
            $("#startDate1, #endDate1, #startDate2, #endDate2").datepicker({
                dateFormat: 'yy-mm-dd'
                ,showOtherMonths: true
                ,showMonthAfterYear:true
                ,changeYear: true
                ,changeMonth: true               
                ,showOn: "both"
                ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
                ,buttonImageOnly: false
                ,buttonText: "선택"          
                ,yearSuffix: "년"
                ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
                ,dayNamesMin: ['일','월','화','수','목','금','토'] 
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']
                ,minDate: "-5Y"
                ,maxDate: "+5Y"
            });                    
            
            $('#datepicker').datepicker('setDate', 'today');
            
            
            var tab_id = $("#tab_id").val();
            
            // 검색조건 세팅
            if(tab_id == "tab1") {
            	$("#startDate1").val($("#startDate").val());
            	$("#endDate1").val($("#endDate").val());
            	$("#searchText_U").val($("#searchText").val());
            } else if(tab_id == "tab2") {
            	$("#startDate2").val($("#startDate").val());
            	$("#endDate2").val($("#endDate").val());
            	$("#searchText_D").val($("#searchText").val());
            }
            
            // active 세팅
            $('.tab-list li').removeClass('active');
            $('.tab-cont').removeClass('active');

            $("#s_" + tab_id).addClass('active');
            $("#" + tab_id).addClass('active');
	    });
	    
        //탭
        $('.tab-list li').click(function () {
            var tab_id = $(this).attr('data-tab');
            
            // 폼 초기화
           	$("#startDate").val("");
           	$("#endDate").val("");
           	$("#searchText").val("");
            	
            if(tab_id == "tab1") {
            	$("#frm").attr("action","/logListUser");
            } else if(tab_id == "tab2") {
            	$("#frm").attr("action","/logListDevice");
            }
            	$("#frm").submit();
        });
        
        function fnSearch(log_type, page_no) {
        	
        	if(log_type == 'U') {
        		// 검색조건 폼에 세팅
        		var searchText = $("#searchText_U").val();
        		var startDate = $("#startDate1").val();
        		var endDate = $("#endDate1").val()
        		
        		$("#searchText").val(searchText);
        		$("#startDate").val(startDate);
        		$("#endDate").val(endDate);
        		
          		$("#frm").attr("action", "/logListUser");
        	} else if(log_type == 'D') {
        		// 검색조건 폼에 세팅
        		var searchText = $("#searchText_D").val()
        		var startDate = $("#startDate2").val()
        		var endDate = $("#endDate2").val()
        	
        		$("#searchText").val(searchText);
        		$("#startDate").val(startDate);
        		$("#endDate").val(endDate);
        		$("#PAGE_NUMBER").val(page_no);
        		
        	    $("#frm").attr("action", "/logListDevice");
        	}

            $("#PAGE_NUMBER").val(page_no);
        	
        	if($("#startDate").val() != '' || $("#endDate").val() != '') {
        		if(!($("#startDate").val() != '' && $("#endDate").val() != '')) {
	        		alert("시작일과 종료일을 모두 설정해주세요");
	   	    		return;
        		}
        	}
        	
            $("#frm").submit();
        }
        
    </script>
</body>

</html>