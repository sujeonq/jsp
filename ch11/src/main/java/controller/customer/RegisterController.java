package controller.customer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CustomerDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CustomerService;

@WebServlet("/customer/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// �ΰŻ���
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// ����Ÿ�� �̱��� ��ü
	private CustomerService service = CustomerService.INSTANCE;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/customer/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ������ ����
		String custId = req.getParameter("custId");
		String name   = req.getParameter("name");
		String hp     = req.getParameter("hp");
		String addr   = req.getParameter("addr");
		String rdate  = req.getParameter("rdate");		
		
		//System.out.println("custId : " + custId);
		logger.debug("custId : " + custId);
		logger.debug("name : " + name);
		logger.debug("rdate : " + rdate);
	
		// DTO ����
		CustomerDTO dto = new CustomerDTO();
		dto.setCustId(custId);
		dto.setName(name);
		dto.setHp(hp);
		dto.setAddr(addr);
		dto.setRdate(rdate);
		
		//System.out.println(dto);
		logger.info(dto.toString());
		
		// ���� ����
		service.insertCustomer(dto);
		
		// �����̷�Ʈ
		resp.sendRedirect("/ch11/customer/list.do");
	}
}








