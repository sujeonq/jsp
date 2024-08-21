package com.jboard.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jboard.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		logger.debug("type : " + type + ", value : " + value);
		
		// ��ȸ�ϱ�
		int result = service.selectCountUser(type, value);
		
		if(type.equals("email") && result == 0) {
			// �̸��� ������ȣ �߼��ϱ�
			String code = service.sendEmailCode(value);
			
			// ���� ����
			HttpSession session = req.getSession();
			session.setAttribute("authCode", code);
		}
		
		// JSON ����
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		// JSON ���
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Javascript fetch�Լ� POST JSON ���ڿ� ��Ʈ�� ó��
		BufferedReader reader = req.getReader();
		StringBuilder requestBody = new StringBuilder();
		
		String line;
		while((line = reader.readLine()) != null){
			requestBody.append(line);
		}
		reader.close();

		// JSON �Ľ�
		Gson gson = new Gson();
		Properties prop = gson.fromJson(requestBody.toString(), Properties.class);
		String code = prop.getProperty("code");
		logger.debug("code : " + code);
		
		// �����ڵ� ��ġ ���� Ȯ��
		HttpSession session = req.getSession();
		String authCode = (String) session.getAttribute("authCode");
		logger.debug("authCode : " + authCode);
		
		// JSON ���� �� ���
		JsonObject json = new JsonObject();
		
		if(authCode.equals(code)) {			
			json.addProperty("result", 1);
		}else {
			json.addProperty("result", 0);
		}
		
		PrintWriter writer = resp.getWriter();
		writer.print(json);
	}
}




