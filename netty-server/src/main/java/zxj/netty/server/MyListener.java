package zxj.netty.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener  implements ServletContextListener{


	public void contextInitialized(ServletContextEvent sce) {
		new MyNettyServer("127.0.0.1",8080).run();
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
