package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import com.jboard.dto.ArticleDto;
import com.jboard.dto.PageGroupDto;
import com.jboard.dto.UserDto;
import com.jboard.service.ArticleService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		
		// ���� ������ ��ȣ ���ϱ�
		int currentPage = service.getCurrentPage(pg);
		
		// ��ü �Խù� ���� ���ϱ�
		int total = service.selectCountTotal();
		
		// ������ ������ ��ȣ ���ϱ�
		int lastPageNum = service.getLastPageNum(total);
		
		// ���� ������ �׷� ���ϱ�
		PageGroupDto pageGroup = service.getCurrentPageGroup(currentPage, lastPageNum);
		
		// Limit�� ���� ��ȣ ���ϱ�
		int start = service.getStartNum(currentPage);
		
		// ������ ���� ��ȣ ���ϱ�(��Ͽ��� ������ȣ�� Ȱ��)
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// ������ ��ȸ
		List<ArticleDto> articles = service.selectArticles(start);
		
		// ���� ����
		req.setAttribute("articles", articles);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroup", pageGroup);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("currentPage", currentPage);
		
		// ������
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
		dispatcher.forward(req, resp);
	}
}







