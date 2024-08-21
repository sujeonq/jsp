package com.jboard.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboard.dao.FileDao;
import com.jboard.dto.FileDto;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public enum FileService {

	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private FileDao dao = FileDao.getInstance();
	
	public void insertFile(FileDto dto) {
		dao.insertFile(dto);
	}
	
	public FileDto selectFile(int fno) {
		return dao.selectFile(fno);
	}
	
	public List<FileDto> selectFiles() {
		return dao.selectFiles();
	}
	
	public void updateFile(FileDto dto) {
		dao.updateFile(dto);
	}
	
	public void deleteFile(int fno) {
		dao.deleteFile(fno);
	}
	
	public List<FileDto> fileUpload(HttpServletRequest req) {
		
		List<FileDto> files = new ArrayList<>();
		
		ServletContext ctx = req.getServletContext();
		String uploadPath = ctx.getRealPath("/uploads");
		logger.debug("uploadPath : " + uploadPath);
		
		try {
			// ÷�� ���� ������ü ��������
			Collection<Part> parts = req.getParts();
			
			for(Part part : parts) {
				// ���ϸ� ����
				String ofileName = part.getSubmittedFileName();
				
				// ������ ÷��������
				if(ofileName != null && !ofileName.isEmpty()) {
					logger.debug("ofileName : " + ofileName);
					
					// ���� ���ϸ� ����
					int idx = ofileName.lastIndexOf(".");
					String ext = ofileName.substring(idx);
					
					String sfileName = UUID.randomUUID().toString() + ext;
					logger.debug("sfileName : " + sfileName);
					
					// ���� ����
					part.write(uploadPath + File.separator + sfileName);
					
					// fileDTO ���� �� ����Ʈ ����
					FileDto fileDto = new FileDto();
					fileDto.setoName(ofileName);
					fileDto.setsName(sfileName);
					files.add(fileDto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return files;
	}
	
	public void fileDownload() {
		
	}
	
}