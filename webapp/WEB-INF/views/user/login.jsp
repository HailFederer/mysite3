<%@ page contentType="text/html;charset=UTF-8" %>
<% 
	String cp = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.servletContext.contextPath}/user/auth">
					<label class="block-label" for="email">이메일</label>
					<input placeholder="email" id="email" name="email" type="text" value="">
					<label class="block-label" >패스워드</label>
					<input placeholder="password" name="password" type="password" value="">
					<% 
						if("fail".equals(request.getParameter("result"))){
					%>
					<p>
						로그인이 실패 했습니다.
					</p>
					<%
						}
					%>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
	</div>
</body>
</html>