package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBContext;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Code này kiểm tra đăng nhập thành công và tên user đã lưu trong session chưa?
		// Nếu rồi thì khi click lại lần nữa vào login thì đi thẳng vào AdminServlet
		// (admin.jsp) không
		// đi qua login.jsp nữa
		// Nếu session chứa user bằng null
		if (request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			response.sendRedirect("AdminServlet");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vietnamese
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {

			// Regex to make sure email and password are valid
			String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

			// Code này dùng để check password phải gồm chữ hoa chữ thường, ký tự đặc biệt,
			// phải đủ 8 ký tự
			// String regexPassword =
			// "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

			// Collect data from login form
			// xử lý cho email này về lowercase nếu như người nhập chữ hoa chữ thường bên
			// trong
			String userID = request.getParameter("username").trim().toLowerCase();
			String password = request.getParameter("password");
			String remember = request.getParameter("rememberMe");

			// Kiểm tra email có đúng cú pháp so với regex bên trên chưa rồi sau đó mới
			// connect để check trong database
			if (!userID.matches(regexEmail)) {
				request.setAttribute("check", "Email không khớp regex !");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else { // Nếu xác thực email đúng format, thì tiến hành so sánh với database
				// Code này lấy ra email và password trong web.xml
				// String uid = getServletContext().getInitParameter("username").trim();
				// String pwd = getServletContext().getInitParameter("password");

				// Biến đếm này xác định xem có hàng nào tồn tại cả email password trong
				// database không
				int count = 0;

				// Connect tới database
				try {

					String sql = "select count(*) as Count from account where user_mail=? and password=?";

					Connection con = DBContext.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, userID);
					stmt.setString(2, password);

					ResultSet rs = stmt.executeQuery();

					// Biến count này để xác nhận là điều kiện cả email và password có cùng tồn tại
					// trong database hay không
					// Input nhập vào nếu viết chữ hoa, chữ thường cũng không sao. Vì database không
					// phân biệt hoa thường.
					if (rs.next()) {
						count = rs.getInt("Count");
					}
					// Biến count trả về số lượng các row match với điều kiện
					// Nếu tìm được thì count lúc này có thể lớn hơn 1 (ví dụ như 2,3 nếu có 2,3
					// hàng match với điều kiện)
					// Tuy nhiên trong trường hợp này thì count sẽ luôn bằng 1, vì cột user_mail
					// trong database là khóa chính,
					// cho nên không có trường hợp có 2 email trùng nhau.
					System.out.println("count: " + count);

					rs.close();

					con.close();

				} catch (Exception ex) {
					System.out.println(ex);
				}

				// Nếu count > 0 ( tức là lúc này count = 1 , tìm đc 1 hàng match)
				// THì bắt đầu cho login vào
				if (count > 0) {
					//Tạo session pass giá trị tên vào file admin.jsp
					HttpSession session = request.getSession();
					session.setAttribute("user", userID);

					if (remember != null) { // Nếu remember là không là null thì tạo cookies chứa giá trị để ghi nhớ
											// đăng nhập
						Cookie cUserName = new Cookie("cookuser", userID.trim());
						Cookie cPassword = new Cookie("cookpass", password.trim());
						Cookie cRemember = new Cookie("cookrem", remember.trim());

						cUserName.setMaxAge(60);// 1ph
						cPassword.setMaxAge(60);
						cRemember.setMaxAge(60);
						response.addCookie(cUserName);
						response.addCookie(cPassword);
						response.addCookie(cRemember);

					} else { // Còn nếu null ( ko check box) thì tạo các cookies null để lần login tới sẽ
							// không hiện ra nữa
						Cookie cUserName = new Cookie("cookuser", null);
						Cookie cPassword = new Cookie("cookpass", null);
						Cookie cRemember = new Cookie("cookrem", null);
						cUserName.setMaxAge(0);
						cPassword.setMaxAge(0);
						cRemember.setMaxAge(0);
						response.addCookie(cUserName);
						response.addCookie(cPassword);
						response.addCookie(cRemember);
					}

					// Đăng nhập thành công thì chuyển đến AdminServlet (admin.jsp)
					response.sendRedirect("AdminServlet");
				} else {
					request.setAttribute("check",
							"Username hoặc Password không khớp với dữ liệu trong database!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}

	}

}
