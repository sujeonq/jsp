package com.jboard.filter;

import java.io.IOException;

import com.jboard.dto.UserDto;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/article/list.do", "/article/write.do", "/article/view.do", "/article/modify.do"})
public class LoginCheckForArticleFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		// �α��� ���� Ȯ��
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession();
		
		UserDto sessUser = (UserDto) session.getAttribute("sessUser");
		
		if(sessUser == null) {
			// �α����� ���� ������ �α��� �������� �̵�
			HttpServletResponse resp = (HttpServletResponse) arg1;
			resp.sendRedirect("/jboard/user/login.do?success=102");
		}else {
			// �α��� �����̸� ���� ���� �̵�(Controller ��û)
			arg2.doFilter(arg0, arg1);
		}
	}
}