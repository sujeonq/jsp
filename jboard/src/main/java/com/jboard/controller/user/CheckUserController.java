package com.jboard.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.jboard.service.user.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/checkUser.do")
public class CheckUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// ������ ����
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		logger.debug("type : " + type);
		logger.debug("value : " + value);
		
		// ��ȸ�ϱ�
		int result = service.selectCountUser(type, value);
		
		// JSON ����
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON ���
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}




