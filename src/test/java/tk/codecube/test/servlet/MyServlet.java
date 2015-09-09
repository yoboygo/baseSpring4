package tk.codecube.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义的Servlet
 * @author Aimy
 * 下午2:37:07
 */
public class MyServlet extends HttpServlet {
	
	 static {
		System.out.println("MyServlet:加载");
	}
	
	/**
	 * 实例化
	 */
	public MyServlet() {
		System.out.println("MyServlet:实例化");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8446031499227216559L;

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("MyServlet:public service");
		super.service(arg0, arg1);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("MyServlet:初始化");
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("MyServlet:protect service()");
		super.service(arg0, arg1);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		System.out.println("MyServlet:doGet()");
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type","text/html;charset=UTF-8");
		
		String userName = req.getParameter("userName");
		String welcomeInfo = "Welcome you ,"+userName;
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>");
		out.println("Welcome Page");
		out.println("</title></head>");
		out.println("<body>");
		out.println(welcomeInfo);
		out.println("</body></html>");
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyServlet:doPost()");
//		super.doPost(req, resp);
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		System.out.println("MyServlet:destroy()");
		super.destroy();
	}
}
