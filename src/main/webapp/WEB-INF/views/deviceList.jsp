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
    <script>
    function fnView(eqpmnt_info_sn) {
        $("#eqpmnt_info_sn").val(eqpmnt_info_sn);
        $("#frm").attr("action", "/deviceDetail");
        $("#frm").submit();
    }
    function fnSearch(page_no) {
    	$("#PAGE_NUMBER").val(page_no);
        $("#frm").attr("action", "/deviceList");
        $("#frm").submit();
    }
    </script>
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
			<form action="" id="frm" method="POST">
	            <div class="mainCont__tit flex-both">
	                <h3 class="b">디바이스 리스트</h3>
	                <div class="inpBox flex">
						<span style="margin:0 0 0 10px;">조회내용&nbsp;
							<input type="text" class="w_314 m_r_10" id="searchText" name="searchText" placeholder="이메일" style="margin:0 0 0 5px;" value="${searchText }">
						</span>
						<button type="button" class="btn btn_gray" onclick="fnSearch()"><img alt="검색" src="/resources/img/ico_search.svg">검색</button>
	                </div>
	            </div>
				<input type="hidden" id="eqpmnt_info_sn" name="eqpmnt_info_sn">
				<input type="hidden" id="PAGE_NUMBER" name="PAGE_NUMBER">
	            <table class="type-01">
	                <colgroup>
	                    <col style="width: 5%;">
	                    <col style="width: 10%;">
	                    <col style="width: auto;">
	                    <col style="width: 20%;">
	                    <col style="width: 20%;">
	                    <col style="width: 10%;">
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th>NO</th>
	                        <th>장치일련번호</th>
	                        <th>이메일</th>
	                        <th>접속주소</th>
	                        <th>시리얼번호</th>
	                        <th>안전코드</th>
	                    </tr>
	                </thead>
	                <tbody>
					<c:choose>
						<c:when test="${not empty deviceEList }">
							<c:forEach var="itm" items="${deviceEList }" varStatus="status">
								<tr>
								    <td>${itm.ROWNUM}</td>
									<td><a href="#" onclick="fnView(${itm.eqpmnt_info_sn})">${itm.eqpmnt_info_sn}</a></td>
									<td>${itm.eml_addr}</td>
									<td>${itm.eqpmnt_cntn_addr}</td>
									<td>${itm.eqpmnt_serial_no}</td>
									<td>${itm.eqpmnt_safety_cd}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">일치하는 데이터가 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
	                </tbody>
	            </table>
			</form>
			<c:if test="${not empty deviceEList }">
				<div class="pagination">
				    <ul class="flex-center">
				        <li class="start">
							<a onclick="fnSearch(1)">처음으로</a>
				        </li>
				        <li class="prev ${pagingInfo.CURRENT_BLOCK == 1 ? 'disabled' :''}">
							<a onclick="fnSearch(${pagingInfo.PREV_PAGE})">이전</a>
				        </li>
					    <c:forEach var="i" begin="${pagingInfo.BEGIN_BLOCK}" end="${pagingInfo.END_BLOCK}">
					        <li class="${i == pagingInfo.PAGE_NUMBER ? 'active' : ''}">
					            <a onclick="fnSearch(${i})">${i}</a>
					        </li>
					    </c:forEach>
				        <li class="next ${pagingInfo.CURRENT_BLOCK == pagingInfo.TOTAL_BLOCK ? 'disabled' :''}">
							<a onclick="fnSearch(${pagingInfo.NEXT_PAGE})">이전</a>
				        </li>
				        <li class="end">
							<a onclick="fnSearch(${pagingInfo.TOTAL_PAGE})">끝으로</a>
				        </li>
				    </ul>
				</div>
			</c:if>
        </div>
    </main>
</body>

</html>