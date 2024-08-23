package com.jboard.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jboard.dao.UserDao;
import com.jboard.dto.UserDto;

public enum UserService {

	INSTANCE;	
	private UserDao dao = UserDao.getInstance();
	
	public String sendEmailCode(String email) {
		// �����ڵ� ����
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		// �̸��� �⺻����
		String title = "jboard ������ȣ �Դϴ�.";
		String content = "<h1>�����ڵ�� " + code + "�Դϴ�.</h1>";
		String sender = "sujeonq@gmail.com";
		String appPass = "jpeh ipid qtxm qzzp"; // Google �� ��й�ȣ
		
		// gmail SMTP ����
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// gmail session ����
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(sender, appPass);
			}
		});
		
		// ���� �߼�
		Message message = new MimeMessage(gmailSession);
		
		try{
			message.setFrom(new InternetAddress(sender, "�����»��", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ""+code;
	}
	
	
	public int selectCountUser(String type, String value) {
		return dao.selectCountUser(type, value);
	}
	
	public void insertUser(UserDto dto) {
		dao.insertUser(dto);
	}
	public UserDto selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	public List<UserDto> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDto dto) {
		dao.updateUser(dto);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
}