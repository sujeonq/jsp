package controller.user1;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.User1Service;

@WebServlet("/user1/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private User1Service service = User1Service.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ������ ����
		String uid = req.getParameter("uid");
		
		// ������ ����
		service.deleteUser1(uid);
		
		// �����̷�Ʈ
		resp.sendRedirect("/ch10/user1/list.do");
	}
}