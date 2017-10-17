<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>

$(function() {
	setInterval(function() {
		$('#img-graph').attr('src', 'http://localhost:8000/graph?ex=2&rnd='+Math.random())
	}, 1000);
});

</script>
</head>
<body>
	<h1>Python Matplotlib Graph Test</h1>
	<img id="img-graph" src="http://localhost:8000/graph?ex=2">
</body>
</html>