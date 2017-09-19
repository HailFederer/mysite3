<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<div id="navigation">
		<ul>
		<c:if test="${authUser != null }">
			<li><a href="${pageContext.servletContext.contextPath }">${authUser.name }</a></li>
		</c:if>
			<li><a href="${pageContext.servletContext.contextPath }/guestBook">방명록</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/guestBook/ajax">방명록(ajax)</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/board">게시판</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/gallery">갤러리</a></li>
		</ul>
	</div>