<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String cp = request.getContextPath();
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board/list.css" rel="stylesheet" type="text/css">
<link href="${pageContext.servletContext.contextPath }/assets/css/board/style.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript">

    function sendIt(no, pageNo) {
		
		var deleteYN = confirm("정말로 삭제하시겠습니까?");
		
		if(deleteYN == false){
			return;
		} else {
			document.location.href="${pageContext.servletContext.contextPath }/board/delete?boardNo="+no+"&pageNo="+pageNo;
		}
    }
    
	function searchData() {
		
		var f = document.searchForm;
		
		f.action = "<%=cp%>/board/list.action";
		f.submit();
	}
	
	$(document).ready(function(){
		
		$('[id^="fold-"]').click(function(){
			
			var src = ($(this).attr('src')==
				'<%=cp%>/assets/images/board/nonFolded-minus.png') ?'<%=cp%>/assets/images/board/folded-plus.png':'<%=cp%>/assets/images/board/nonFolded-minus.png';
		    $(this).attr('src',src);
			
			var obj = $('.'+ this.id);
			
			if(obj.css('display')=='none')
				obj.show();
			else
				obj.hide();
		});
	});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
	
	<div id="bbsList">
	<div id="bbsList_header">
		<div id="leftHeader">
		  <form name="searchForm" method="post" action="">
			<select name="searchKey" class="selectFiled">
				<option value="subject">제목</option>
				<option value="name">작성자</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="searchValue" class="textFiled"/>
			<input type="button" value=" 검 색 " class="btn2" 
				onclick="searchData();"/>
		  </form>
		</div>
		<div id="rightHeader">
			<input type="button" value=" 글올리기 " class="btn2" 
				onclick="javascript:location.href='${pageContext.servletContext.contextPath }/board/write?pageNo=${pageNo }';"/>
		</div>
	</div>
	<div id="bbsList_list">
		<div id="title">
			<dl>
				<dt class="num">번호</dt>
				<dt class="subject">제목</dt>
				<dt class="name">작성자</dt>
				<dt class="created">작성일</dt>
				<dt class="hitCount">조회수</dt>
				<dt class="deleteButton"></dt>
			</dl>
		</div>
		
		<form name="listForm" method="post" action="">
		<c:forEach var="dto" items="${list }">
			<%-- <c:set var="restDivOfJSP" value="${restDiv }"/>
			<c:forEach begin="1" end="${dto.depthGap }" step="1">
			</div>
			<c:set var="restDivOfJSP" value="${restDivOfJSP-1 }"/>
			</c:forEach> --%>
		<div class="fold-${dto.parent}" id="lists">
			<dl>
				<dd class="num">${dto.no}</dd>
				<dd class="subject">
					<%-- depthGap:${dto.depthGap } groupNo:${dto.groupNo } parent:${dto.parent } orderNo:${dto.orderNo } depth:${dto.depth } --%>
					<c:forEach items="${dto.verticalList }" var="verticalList">
							<c:if test="${verticalList != 0}"><img src="${pageContext.servletContext.contextPath }/assets/images/board/vertical.png"/></c:if>
							<c:if test="${verticalList == 0}"><img src="${pageContext.servletContext.contextPath }/assets/images/board/blank_nonTop.png"/></c:if>
					</c:forEach>
					
					<c:if test="${dto.depth == 0 && dto.replyNum == 0}">
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/blank_top.png"/>
					</c:if>
					<c:if test="${dto.depth == 0 && dto.replyNum != 0}">
						<img id="fold-${dto.no}" src="${pageContext.servletContext.contextPath }/assets/images/board/nonFolded-minus.png"/>
					</c:if>
						
					<c:if test="${dto.depth != 0 && dto.siblingNum != 0 && dto.replyNum == 0}">
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/middle.png"/>
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/horizontal.png"/>
					</c:if>
					<c:if test="${dto.depth != 0 && dto.siblingNum != 0 && dto.replyNum != 0}">
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/middle.png"/>
						<img id="fold-${dto.no}" src="${pageContext.servletContext.contextPath }/assets/images/board/nonFolded-minus.png"/>
					</c:if>
						
					<c:if test="${dto.depth != 0 && dto.siblingNum == 0 && dto.replyNum == 0}">
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/bottom.png"/>
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/horizontal.png"/>
					</c:if>
					<c:if test="${dto.depth != 0 && dto.siblingNum == 0 && dto.replyNum != 0}">
						<img src="${pageContext.servletContext.contextPath }/assets/images/board/bottom.png"/>
						<img id="fold-${dto.no}" src="${pageContext.servletContext.contextPath }/assets/images/board/nonFolded-minus.png"/>
					</c:if>
					
					<a href="${urlArticle}&boardNo=${dto.no}">
					${dto.title }</a>
				</dd>
					
				<dd class="name">${dto.name }</dd>
				<dd class="created">${dto.reg_date }</dd>
				<dd class="hitCount">${dto.hit }</dd>
				<dd class="deleteButton">
				<c:if test="${dto.deleteRight == true}">
				<input type="button" class="btn2" value="삭제" onclick="sendIt('${dto.no}','${pageNo }');"/>
				</c:if>
				</dd>
			</dl>
		</div>
		</c:forEach>
			<%-- <c:forEach begin="1" end="${restDivOfJSP }" step="1">
			</div>
			</c:forEach> --%>
		 </form>
		
		<div id="footer">
			<p>
				<c:if test="${totalDataCount != 0 }">
					${pageIndexList }
				</c:if>
				<c:if test="${totalDataCount == 0 }">
					등록된 게시물이 없습니다.
				</c:if>
			</p>
		</div>
	</div>
</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>