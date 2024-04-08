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
</head>

<body>
    <header>
        <nav class="flex-center">
            <a href="/userList">회원</a>
            <a href="/deviceList" class="on">디바이스</a>
            <a href="/logListUser">로그</a>
        </nav>
    </header>
    <main>
        <div class="mainTitle">
            <h2>디바이스</h2>
        </div>
        <div class="mainCont">
            <!-- 디바이스 상세내용 -->
            <div class="mainCont__tit">
                <h3 class="b">디바이스 상세내용</h3>
            </div>

            <table class="type-02">
                <colgroup>
                    <col style="width:15%;">
                    <col style="width:35%;">
                    <col style="width:15%;">
                    <col style="width:35%;">
                </colgroup>
                <tbody>
                    <tr>
                        <th>장치일련번호</th>
                        <td>${deviceInfo.eqpmnt_info_sn }</td>
                        <th>장치명</th>
                        <td>${deviceInfo.eqpmnt_nm }</td>
                    </tr>
                    <tr>
                        <th>소유자</th>
                        <td>${deviceInfo.mbr_sn }</td>
                        <th>이메일</th>
                        <td>${deviceInfo.eml_addr }</td>
                    </tr>
                    <tr>
                        <th>접속주소</th>
                        <td>${deviceInfo.eqpmnt_cntn_addr }</td>
                        <th>접속포트</th>
                        <td>${deviceInfo.eqpmnt_cntn_port }</td>
                    </tr>
                    <tr>
                        <th>시리얼번호</th>
                        <td>${deviceInfo.eqpmnt_serial_no }</td>
                        <th>안전코드</th>
                        <td>${deviceInfo.eqpmnt_safety_cd }</td>
                    </tr>
                </tbody>
            </table>

            <!-- 장치로그 -->
            <div class="mainCont__tit flex-both m_t_40">
                <h3 class="b">장치로그</h3>
                <ul class="switch type-02 flex">
                    <li id="rsnt" class="active">최신순</li>
                    <li id="past">오래된순</li>
                </ul>
            </div>
            
       		<form action="/deviceDetail" id="frm" method="POST">
				<input type="hidden" id="order" name="order" value="${order}">
				<input type="hidden" id="eqpmnt_info_sn" name="eqpmnt_info_sn" value="${eqpmnt_info_sn}">
			</form>

            <table class="type-02 taC">
                <colgroup>
                    <col style="width: 5%;">
                    <col style="width: auto;">
                    <col style="width: 25%;">
                </colgroup>
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>장치로그</th>
                        <th>date</th>
                    </tr>
                </thead>
                <tbody>
                	<c:choose>
	                	<c:when test="${not empty logList }">
			                <c:forEach var="itm" items="${logList }" varStatus="status">
			                    <tr>
			                        <td>${status.count}</td>
			                        <td>${itm.log_content }</td>
			                        <td>${itm.log_tm }</td>
			                    </tr>
			                </c:forEach>
		                </c:when>
		                <c:otherwise>
		                	<tr>
		                		<td colspan="3">로그가 존재하지 않습니다</td>
		                	</tr>
		                </c:otherwise>
	                </c:choose>
                </tbody>
            </table>
        </div>
    </main>

    <script>
    
	    $(function(){
        	var order = $("#order").val();
	
            $('.switch li').removeClass('active');
	
	        $("#" + order).addClass('active');
	    });
    
        //스위치
        $('.switch li').click(function () {
        	var order = $(this).attr('id');
        	
        	$("#order").val(order);
        	
       		$("#frm").submit();
        });
    </script>
</body>

</html>