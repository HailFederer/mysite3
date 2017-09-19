<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board/created.css" rel="stylesheet" type="text/css">
<link href="${pageContext.servletContext.contextPath }/assets/css/board/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

    function sendIt(no, pageNo) {
		
		var deleteYN = confirm("정말로 삭제하시겠습니까?");
		
		if(deleteYN == false){
			return;
		} else {
			document.location.href="${pageContext.servletContext.contextPath }/board/delete?boardNo="+no+"&pageNo="+pageNo;
		}
    }

</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		
		<div id="bbs">	
		<form name="myForm" method="post" action="${pageContext.servletContext.contextPath }/board/modify">
		<div id="bbsCreated">
			<div class="bbsCreated_bottomLine">
				<dl>
					<dt>제&nbsp;&nbsp;&nbsp;&nbsp;목</dt>
					<dd>
						<input style="border-width: 0;" type="text" name="title" value="${vo.title }" size="74" maxlength="100"  class="boxTF" />
					</dd>
				</dl>
			</div>
	
			<div id="bbsCreated_content">
				<dl>
					<dt>내&nbsp;&nbsp;&nbsp;&nbsp;용</dt>
					<dd>
					      <textarea name="content" style="border-width: 0;" cols="72" rows="12" class="boxTA">${vo.content }</textarea>
					</dd>
				</dl>
			</div>
		</div>

		<div id="bbsCreated_footer">
		
	        <input type="button" class="btn2" value=" 답변작성 " 
	        onclick="javascript:location.href='${pageContext.servletContext.contextPath }/board/reply?boardNo=${vo.no}&pageNo=${pageNo }';"/>
	       <c:if test="${vo.deleteRight == true }">
	        <input type="button" value=" 수정하기 " class="btn2" 
	        onclick="javascript:location.href='${pageContext.servletContext.contextPath }/board/modify?boardNo=${vo.no}&pageNo=${pageNo }';"/>
	        <input type="button" class="btn2" value="삭제" onclick="sendIt('${vo.no}','${pageNo }');"/>
	       </c:if>
	        <input type="button" value=" 글목록 " class="btn2" 
	        	onclick="javascript:location.href='${pageContext.servletContext.contextPath }/board?pageNo=${pageNo }';"/>
		</div>
	
	    </form>
	</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>