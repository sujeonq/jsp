package controller.user1;

import java.io.IOException;
import java.util.List;

import dto.User1DTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.User1Service;

@WebServlet("/user1/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private User1Service service = User1Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ������ ��ȸ�ϱ�
		List<User1DTO> users = service.selectUser1s();
		
		// ������ ����(View���� �����͸� ����ϱ� ���� ���� request scope ���)
		req.setAttribute("users", users);
		
		// �� forward
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user1/list.jsp");
		dispatcher.forward(req, resp);
	}
}









