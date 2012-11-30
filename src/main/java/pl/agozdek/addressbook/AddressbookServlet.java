package pl.agozdek.addressbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


import pl.agozdek.addressbook.persistence.DBService;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

@WebServlet(urlPatterns = "/*", initParams={@WebInitParam(name="application", value="pl.agozdek.addressbook.AddressbookApplication")})
public class AddressbookServlet extends AbstractApplicationServlet {
	
		private static final long serialVersionUID = 1L;
		
		DBService ps = new DBService();
		Application myApp = new AddressbookApplication(ps);
		
		@Override
		protected Application getNewApplication(HttpServletRequest request)
				throws ServletException {
			
			return myApp;
		}
		@Override
		protected Class<? extends Application> getApplicationClass()
				throws ClassNotFoundException {
			// TODO Auto-generated method stub
			return AddressbookApplication.class;
		}
}
