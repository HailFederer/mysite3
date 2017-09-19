<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("cp", request.getContextPath());
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${cp }/assets/css/board/created.css" rel="stylesheet" type="text/css">
<link href="${cp }/assets/css/board/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

    function sendIt(pageNo) {
       
		f = document.myForm;

    	str = f.title.value;
    	str = str.trim();
        if(!str) {
            alert("\n제목을 입력하세요. ");
            f.subject.focus();
            return;
        }
    	f.title.value = str;

    	str = f.content.value;
	    str = str.trim();
        if(!str) {
            alert("내용을 입력하세요. ");
            f.content.focus();
            return;
        }
    	f.content.value = str;
    	
    	f.action = "${cp}/board/write?pageNo="+pageNo;
        
        f.submit();
    }

</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<div id="bbs">	
		<form name="myForm" method="post" action="">
		<div id="bbsCreated">
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt>
					<dd>
					      <input type="text" name="title" value="" size="74" maxlength="100"  class="boxTF" />
					</dd>
				</dl>
			</div>
	
			<div id="bbsCreated_content">
				<dl>
					<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
					<dd>
					      <textarea name="content" cols="72" rows="12" class="boxTA"></textarea>
					</dd>
				</dl>
			</div>
		</div>

		<div id="bbsCreated_footer">
		
	        <input type="button" value=" 등록하기 " class="btn2" 
	        	onclick="sendIt(${pageNo});"/>
	        <input type="reset" value=" 다시입력 " class="btn2" 
	        	onclick="document.myForm.title.focus();"/>
	        <input type="button" value=" 작성취소 " class="btn2" 
	        	onclick="javascript:location.href='${cp }/board?pageNo=${pageNo }';"/>
		</div>
	
	    </form>
	</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>