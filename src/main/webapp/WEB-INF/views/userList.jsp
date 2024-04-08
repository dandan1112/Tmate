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
    function fnView(mbr_sn) {
        $("#mbr_sn").val(mbr_sn);
        $("#frm").attr("action", "/userDetail");
        $("#frm").submit();
    }
    function fnSearch(page_no) {
        $("#PAGE_NUMBER").val(page_no);
        $("#frm").attr("action", "/userList");
        $("#frm").submit();
    }
    </script>
</head>

<body>
    <header>
        <nav class="flex-center">
            <a href="/userList" class="on">회원</a>
            <a href="/deviceList">디바이스</a>
            <a href="/logListUser">로그</a>
        </nav>
    </header>
    <main>
        <div class="mainTitle">
            <h2>회원</h2>
        </div>
        <div class="mainCont">
			<form action="" id="frm" method="POST">
	            <div class="mainCont__tit flex-both">
	                <h3 class="b">회원 리스트</h3>
	                <div class="inpBox flex">
	                    <span style="margin:0 0 0 10px;">조회내용&nbsp;
		                    <input type="text" class="w_314 m_r_10" id="searchText" name="searchText" placeholder="아이디,  별명,  이메일" style="margin:0 0 0 5px;" value="${searchText}">
	                    </span>
                    	<button type="button" class="btn btn_gray" onclick="fnSearch(1)"><img alt="검색" src="/resources/img/ico_search.svg">검색</button>
	                </div>
	            </div>
				<input type="hidden" id="mbr_sn" name="mbr_sn">
				<input type="hidden" id="PAGE_NUMBER" name="PAGE_NUMBER">
	            <table class="type-01">
	                <colgroup>
	                    <col style="width: 5%;">
	                    <col style="width: auto;">
	                    <col style="width: 15%;">
	                    <col style="width: 15%;">
	                    <col style="width: 15%;">
	                    <col style="width: 15%;">
	                    <col style="width: 10%;">
	                </colgroup>
	                <thead>
	                    <tr>
	                        <th>NO</th>
	                        <th>아이디</th>
	                        <th>별명</th>
	                        <th>이메일</th>
	                        <th>전화번호</th>
	                        <th>등록일</th>
	                        <th>등급</th>
	                    </tr>
	                </thead>
	                <tbody>
	                <c:choose>
		                <c:when test="${not empty userList }">
		                    <c:forEach var="itm" items="${userList }" varStatus="status">
			                    <tr>
			                        <td>${itm.ROWNUM}</td>
			                        <td><a href="#" onclick="fnView(${itm.mbr_sn})">${itm.mbr_id}</a></td>
			                        <td>${itm.mbr_name}</td>
			                        <td>${itm.eml_addr}</td>
			                        <td>${itm.mbr_tel}</td>
			                        <td>${itm.srvc_trms_agre_dt}</td>
			                        <td>${itm.mbr_type}</td>
			                    </tr>
		                    </c:forEach>
		                </c:when>
		                <c:otherwise>
			                <tr>
			                	<td colspan="7">일치하는 데이터가 존재하지 않습니다</td>
			                </tr>
		                </c:otherwise>
	                </c:choose>
	                </tbody>
	            </table>
            </form>
			<c:if test="${not empty userList }">
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