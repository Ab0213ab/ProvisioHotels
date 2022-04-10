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
	name = "LoginServlet",
	urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String session_user_email;
	private String session_user_id;
	private String login_host_context_redirect_url;
	private String login_project_context_redirect_url;
	private String user_lobby_redirect_url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		this.login_project_context_redirect_url = context.getInitParameter("LoginRedirectProjectContextURL");
		this.login_host_context_redirect_url = context.getInitParameter("LoginRedirectHostContextURL");
		this.user_lobby_redirect_url = context.getInitParameter("UserLobbyRedirectHostContextURL");
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
		response.sendRedirect(this.login_host_context_redirect_url);
	}

	/**
	 * Handle login form here
	 */
	public void doPost (
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		// Get login credentials:
		String username = request.getParameter("username");
		String user_password = request.getParameter("password");

		RequestDispatcher request_dispatcher = request.getRequestDispatcher(this.login_project_context_redirect_url);

		// Try to log them in and retrieve user id:
		Integer user_id = DBUserHandler.loginUser(
			username,
			user_password
		);

		if (user_id == -1){
			request.setAttribute("error_message", "Invalid login.");
			request_dispatcher.forward(request, response);
			return;
		}

		// Success! Add them to the session:
		HttpSession session = request.getSession();
		session.setAttribute(this.session_user_email, username);
		session.setAttribute(this.session_user_id, user_id);

		// Set it to expire after 60 minutes:
		session.setMaxInactiveInterval(3600);

		System.out.println("trying to redirect to " + this.user_lobby_redirect_url);
		response.sendRedirect(this.user_lobby_redirect_url);
	}
}