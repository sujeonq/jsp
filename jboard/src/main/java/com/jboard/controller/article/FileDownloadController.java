package com.jboard.controller.article;

import java.io.IOException;

import com.jboard.dto.FileDto;
import com.jboard.service.FileService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/article/fileDownload.do")
public class FileDownloadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private FileService service = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fno = req.getParameter("fno");
		
		// ���� ��ȸ
		FileDto fileDto = service.selectFile(fno);
		
		// ���� ����(service -> fileDownload �޼��忡�� getAttribute�� ������)
		req.setAttribute("fileDto", fileDto);
		
		// ���� �ٿ�ε� ī��Ʈ ������Ʈ
		service.updateFileDownloadCount(fno);
		
		// ���� �ٿ�ε�
		service.fileDownload(req, resp);
	}
}







