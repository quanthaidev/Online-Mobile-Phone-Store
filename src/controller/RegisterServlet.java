package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBContext;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Vietnamese
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		try {

			// Regex to make sure email are valid
			String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

			// Code này dùng để check password phải gồm chữ hoa chữ thường, ký tự đặc biệt,
			// phải đủ 8 ký tự
			// String regexPassword =
			// "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

			// Collect data from register form
			// xử lý cho email này về lowercase nếu như người nhập chữ hoa chữ thường bên
			// trong
			String fullname = request.getParameter("fullname").trim();
			String address = request.getParameter("address").trim(); //
			String phone = request.getParameter("phone").trim(); //
			String email = request.getParameter("email").trim().toLowerCase();
			String password = request.getParameter("password").trim();
			String rePassword = request.getParameter("rePassword").trim();

			// Kiểm tra email có đúng cú pháp so với regex bên trên chưa rồi sau đó mới
			// connect để check trong database
			if (!email.matches(regexEmail)) {
				request.setAttribute("check", "Email không khớp regex !");
				request.setAttribute("fullname", fullname);
				request.setAttribute("address", address);
				request.setAttribute("phone", phone);
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("rePassword", rePassword);

				request.getRequestDispatcher("register.jsp").forward(request, response);

			} else if (!password.equals(rePassword)) { // Nếu mật khẩu không khớp thì báo không khớp
				request.setAttribute("check", "Mật khẩu không khớp !");
				request.setAttribute("fullname", fullname);
				request.setAttribute("address", address);
				request.setAttribute("phone", phone);
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				request.setAttribute("rePassword", rePassword);

				request.getRequestDispatcher("register.jsp").forward(request, response);

			} else { // Nếu xác thực email đúng format và mật khẩu khớp, thì tiến hành kiểm tra trong database có email nào tồn tại không
				
				// Biến đếm này xác định xem có có trùng email trong database không
				// database không
				int count = 0;

				// Connect tới database
				try {

					String sql = "select count(*) as Count from account where user_mail=?";

					Connection con = DBContext.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, email);

					ResultSet rs = stmt.executeQuery();

					// Biến count này để xác nhận là điều kiện email có  tồn tại
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

				// Nếu count > 0 ( tức là lúc này count = 1 , 
				//thì trở về register.jsp để thông báo rằng email này đã tồn tại)
				if (count > 0) {

					request.setAttribute("check", "Email này đã tồn tại. Vui lòng nhập email khác.");
					request.setAttribute("fullname", fullname);
					request.setAttribute("address", address);
					request.setAttribute("phone", phone);
					request.setAttribute("email", email);
					request.setAttribute("password", password);
					request.setAttribute("rePassword", rePassword);
					request.getRequestDispatcher("register.jsp").forward(request, response);

				} else {
					// Hàm else này xác định là không có tồn tại email đó, nên cho đăng ký được. (count = 0)
					// Lúc này thêm tất cả dữ liệu vào database

					try {

						String sql = "INSERT INTO account (user_mail, password, account_role, user_name, user_address, user_phone) VALUES (?, ?, ?, ?, ?, ?)";

						Connection con = DBContext.getConnection();
						PreparedStatement stmt = con.prepareStatement(sql);

						stmt.setString(1, email);
						stmt.setString(2, password);
						stmt.setString(3, "1");
						stmt.setString(4, fullname);
						stmt.setString(5, address);
						stmt.setString(6, phone);

						stmt.executeUpdate();

						con.close();

					} catch (Exception ex) {
						System.out.println(ex);
					}

					request.getRequestDispatcher("createAccountSuccess.jsp").forward(request, response);

				}

			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}

	}

}
