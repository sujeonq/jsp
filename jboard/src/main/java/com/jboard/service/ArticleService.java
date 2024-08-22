package com.jboard.service;

import java.util.List;

import com.jboard.dao.ArticleDao;
import com.jboard.dto.ArticleDto;
import com.jboard.dto.PageGroupDto;

public enum ArticleService {

	INSTANCE;
	private ArticleDao dao = ArticleDao.getInstance();
	
	// ��ü �Խù� �������� ������ ������ ��ȣ ���ϱ�
	public int getLastPageNum(int total) {		
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	// ������ ���۹�ȣ(limit��)
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// ���� ��������ȣ ���ϱ�
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		return currentPage;
	}
	
	// ���� ������ �׷� ���ϱ� 
	public PageGroupDto getCurrentPageGroup(int currentPage, int lastPageNum) {
		
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		return new PageGroupDto(pageGroupStart, pageGroupEnd);
	}
	
	// ������ ���۹�ȣ
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	public int insertArticle(ArticleDto dto) {
		return dao.insertArticle(dto);
	}
	
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	
	public ArticleDto selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDto> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	public void updateArticle(ArticleDto dto) {
		dao.updateArticle(dto);
	}	
	
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}	
}