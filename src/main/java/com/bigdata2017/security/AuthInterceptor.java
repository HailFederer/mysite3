package com.bigdata2017.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bigdata2017.mysite.vo.UserVO;
import com.bigdata2017.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Method에서 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 3-1. Method에 @Auth가 없다면, Class(Type)에 붙어있는 @Auth 받아오기
		if(auth == null)
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		
		// 4. @Auth가 안 붙어있는 경우
		if(auth == null) {
			return true;
		}
		
		// 5.@Auth가 붙어있을 때, 인증여부 체크
		HttpSession session = request.getSession();
		
		if(session == null) {	// 인증이 안 되어있는 상태
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {	// 인증이 안 되어있는 상태
			response.sendRedirect(request.getContextPath()+"/user/login");			
			return false;
		}
		
		String role = String.valueOf(auth.role());
		String userRole = authUser.getRole();
		
		if(!userRole.equals(String.valueOf(Role.ADMIN)) && role.equals(String.valueOf(Role.ADMIN))) {
			return false;
		}		
		
		return true;
	}
}













