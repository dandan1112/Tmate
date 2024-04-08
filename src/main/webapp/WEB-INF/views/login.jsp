<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>tmate</title>
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
    <script>
    $(function(){
        $("#mbr_id").on("propertychange change paste input", function() {
        	if($("#mbr_id").val()!="" && $("#pswd").val()!=""){
        		$("#login").prop("disabled", false);
        	} else {
        		$("#login").prop("disabled", true);
        	}
        });
        
        $("#pswd").on("propertychange change paste input", function() {
        	if($("#mbr_id").val()!="" && $("#pswd").val()!=""){
        		$("#login").prop("disabled", false);
        	} else {
        		$("#login").prop("disabled", true);
        	}
        });
    });
    
    function checkLogin() {
     	$.ajax({
    		url: "/login",
    		type: "GET",
    		data: {
    			mbr_id: $("#mbr_id").val(),
    			pswd: $("#pswd").val()
    		},
    		dataType:"json",
    		success :function(data){
    			if(data.rtnCode == 1 || data.rtnCode == 10){
    				location.href = "/userList"
    			}else{
    				alert(data.rtnMsg);
    			}
    		},
    		error:function(xhr, status, error){
    	        alert(status);
    	    }
    	});
    }
    </script>
</head>

<body>
    <div class="login-frame">
        <div class="taC m_b_40">
            <h2 class="m_b_4">관리자 로그인</h2>
            <h5>아이디와 비밀번호를 입력해주세요. (영문 대소문자 구분)</h5>
        </div>
            <div class="m_b_20">
                <input type="text" placeholder="아이디" class="w_100pct" id="mbr_id">
            </div>
            <div class="m_b_40">
                <input type="password" placeholder="비밀번호" class="w_100pct" id="pswd">
            </div>
            <button type="button" class="w_100pct btn btn_login" id="login" onclick="checkLogin()" disabled>로그인</button>
    </div>
</body>

</html>