package sub1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 4234852702204424689L;

	@Override
	public void init() throws ServletException {
		// ������ ���ʷ� ����� ��
		System.out.println("HelloServlet init...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Ŭ���̾�Ʈ�� ��û�� GET�� ���
		System.out.println("HelloServlet doGet...");
		
		// HTML ���
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'/>");
		writer.println("<title>HelloServlet</title");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h3>HelloServlet</h3>");
		writer.println("<a href='/ch09/1.ServletTest.jsp'>Servlet ����</a>");
		writer.println("<a href='/ch09/hello.do'>hello</a>");
		writer.println("<a href='/ch09/welcome.do'>welcome</a>");
		writer.println("<a href='/ch09/greeting.do'>greeting</a>");
		writer.println("</body>");
		writer.println("</html>");
		writer.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Ŭ���̾�Ʈ�� ��û�� POST�� ���
		System.out.println("HelloServlet doPost...");
	}
	
	@Override
	public void destroy() {
		// ������ ���� �� ��
		System.out.println("HelloServlet destroy...");
	}
	
	
	
}