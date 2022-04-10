package Provisio;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; 
import jakarta.servlet.ServletContext;
import jakarta.servlet.RequestDispatcher;

/**
 * Basic login test
 */
@WebServlet(
	name = "HomeServlet",
	urlPatterns = "/home"
)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String home_host_context_redirect_url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(
		ServletConfig config
	) throws ServletException {
		super.init(config);

		// Get config values:
		ServletContext context = getServletContext();
		this.home_host_context_redirect_url = context.getInitParameter("UserHomeRedirectHostContextURL");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * Just because I don't like navigating to /jsp/login.jsp (:
	 */
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		response.sendRedirect(this.home_host_context_redirect_url);
	}

	/**
	 * Handle login form here
	 */
	public void doPost (
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		this.doGet(request, response);
	}
}