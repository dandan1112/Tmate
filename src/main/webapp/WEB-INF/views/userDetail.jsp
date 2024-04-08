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
    $(function(){
    	var type = $("#mbr_type").val();
    	$('select[name=mbr_type]').val(type).prop("selected", true);
    });
    
    function changeDetail() {
    	if($("#pswd").val() != $("#pwsd_chk").val()){
    		alert("비밀번호가 일치하지 않습니다");
    		return;
    	}
    	
     	$.ajax({
    		url: "/changeDetail",
    		type: "get",
    		data: $("#frm").serialize(),
    		dataType:"json",
    		success :function(data){
    			if(data.rtnCode == '0'){
    				$("#pswd").val('');
    				$("#pwsd_chk").val('');
    			}
   				alert(data.rtnMsg);
    		},
    		error:function(xhr, status, error){
    	        alert(status);
    	    }
    	});
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
        
		<form id="frm" method="post">
        <input type="hidden" id="mbr_sn" name="mbr_sn" value="${userInfo.mbr_sn }">
        <input type="hidden" id="mbr_type" value="${userInfo.mbr_type }">
        
        <div class="mainCont">
            <div class="mainCont__tit flex-both">
                <h3 class="b">회원 상세내용</h3>
                <button type="button" class="btn btn_gray" onClick="changeDetail()">
                <img alt="검색" src="/resources/img/ico_search.svg">변경</button>
            </div>

            <table class="type-02">
                <colgroup>
                    <col style="width: 190px;">
                    <col style="width: auto;">
                </colgroup>
                
                <tbody>
                    <tr>
                        <th>No</th>
                        <td>${userInfo.mbr_sn }</td>
                    </tr>
                    <tr>
                        <th>User Login</th>
                        <td>${userInfo.mbr_id }</td>
                    </tr>
                    <tr>
                        <th>별명</th>
                        <td>
                            <div class="inpBox flex">
                                <input type="text" class="w_314 m_r_10" id="mbr_name" name="mbr_name" value="${userInfo.mbr_name }">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>${userInfo.eml_addr }</td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>${userInfo.mbr_tel }</td>
                    </tr>
                    <tr>
                        <th>등급</th>
                        <td>
	                    	<select name="mbr_type">
	                    		<option value="ADMIN">최고관리자</option>
	                    		<option value="MANAGER">매니저</option>
	                    		<option value="USER">일반</option>
	                    	</select>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <div class="inpBox flex">
                                <input type="password" class="w_152 m_r_10" placeholder="비밀번호" id="pswd" name="pswd">
                                <input type="password" class="w_152 m_r_10" placeholder="비밀번호 확인" id="pwsd_chk">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>연결된 장치 목록</th>
                        <td class="p_20px">
                            <table class="type-02 taC">
                                <colgroup>
                                    <col style="width: 33.33%;">
                                    <col style="width: 33.33%;">
                                    <col style="width: 33.33%;">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>장치명</th>
                                        <th>장치유형코드</th>
                                        <th>장치접속주소</th>
                                        <th>장치접속포트</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                	<c:when test="${not empty deviceList }">
		                                <c:forEach var="itm" items="${deviceList }">
		                                    <tr>
		                                        <td>${itm.eqpmnt_nm }</td>
		                                        <td>${itm.eqpmnt_type_ct }</td>
		                                        <td>${itm.eqpmnt_cntn_addr }</td>
		                                        <td>${itm.eqpmnt_cntn_port }</td>
		                                    </tr>
		                                </c:forEach>
                                	</c:when>
                                	<c:otherwise>
                                	<tr>
                                		<td colspan="4">등록된 장치가 없습니다</td>
                                	</tr>
                                	</c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        </form>
    </main>
</body>

</html>