/**
 * Handles registering the user
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
import jakarta.servlet.RequestDispatcher;

@WebServlet(
	name = "RegisterServlet",
	urlPatterns = "/register"
)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String session_user_email;
	private String session_user_id;
	private String user_lobby_redirect_url;
	private String user_registration_host_context_redirect_url;
	private String user_registration_project_context_redirect_url;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		this.user_lobby_redirect_url = context.getInitParameter("UserLobbyRedirectHostContextURL");
		this.user_registration_host_context_redirect_url = context.getInitParameter("UserRegistrationRedirectHostContextURL");
		this.user_registration_project_context_redirect_url = context.getInitParameter("UserRegistrationRedirectProjectContextURL");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {}

	/**
	 * Redirect to register page
	 */
	public void doGet(
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		response.sendRedirect(this.user_registration_host_context_redirect_url);
	}

	/**
	 * Handle register form here
	 */
	public void doPost (
		HttpServletRequest request,
		HttpServletResponse response
	) throws ServletException, IOException {
		String first_name = request.getParameter("fname");
		String last_name = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		RequestDispatcher request_dispatcher = request.getRequestDispatcher(this.user_registration_project_context_redirect_url);

		// Verify the password:
		if (!this.isValidPassword(password)){
			request.setAttribute("error_message", "Invalid password. Must to at least 8 characters, one uppercase letter, and one lowercase letter.");
			request_dispatcher.forward(request, response);
			return;	
		}

		// Check if user exists:
		if (DBUserHandler.userExists(email)){
			request.setAttribute("error_message", "User already exists. Please login.");
			request_dispatcher.forward(request, response);
			return;
		}

		// Try to register them in and retrieve user id:
		Integer user_id = DBUserHandler.registerUser(
			first_name,
			last_name,
			email,
			password
		);

		if (user_id == -1){
			request.setAttribute("error_message", "Uh Oh! Something went wrong! Please try again.");
			request_dispatcher.forward(request, response);
			return;
		}

		// Success! Add them to the session:
		HttpSession session = request.getSession();
		session.setAttribute(this.session_user_email, email);
		session.setAttribute(this.session_user_id, user_id);

		// Set it to expire after 60 minutes:
		session.setMaxInactiveInterval(3600);

		System.out.println("trying to redirect to " + this.user_lobby_redirect_url);
		response.sendRedirect(this.user_lobby_redirect_url);
	}

	/**
	 * Make sure password meets our requirements
	 */
	private Boolean isValidPassword(
		String password
	){
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{8,}$");
	}
}