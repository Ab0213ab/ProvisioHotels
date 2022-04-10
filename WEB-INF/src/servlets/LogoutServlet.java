/**
 * Log the user out
 */
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

@WebServlet(
	name = "LogoutServlet",
	urlPatterns = "/logout"
)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String session_user_email;
	private String session_user_id;
	private String login_host_context_redirect_url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
		this.session_user_email = context.getInitParameter("SessionUserEmail");
		this.session_user_id = context.getInitParameter("SessionUserId");
		this.login_host_context_redirect_url = context.getInitParameter("LoginRedirectHostContextURL");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * Do same thing as post
	 */
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * Handle login form here
	 */
	public void doPost (
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		// Destroy the session:
		this.destroySession(request.getSession());

		// Redirect to login:
		response.sendRedirect(this.login_host_context_redirect_url);
	}

	/**
	 * Destroy the session
	 */
	private void destroySession(
		HttpSession session
	){
		session.removeAttribute(this.session_user_email);
		session.removeAttribute(this.session_user_id);
	}
}