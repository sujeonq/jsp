package sub3;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

// �����̳�(WAS)�� ���۰� ���Ḧ �����ϴ� ������
public class MyContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ������ ���۵� ��
		System.out.println("contextInitialized...");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// ������ ����� ��
		System.out.println("contextDestroyed...");
	}
	
}