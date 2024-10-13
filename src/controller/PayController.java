package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.DBContext;
import model.Cart;

/**
 * servlet PayController: controller của chức năng lưu thông tin chi tiết đơn hàng và khách hàng vào datasource
 */
@WebServlet("/PayController")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //Lấy giá trị tiếng việt từ param url
		
		String username =  request.getParameter("username");
		String address = request.getParameter("address");
		
		
		String discount = request.getParameter("discount");
		
		System.out.println("Ä�á»‹a chá»‰ lÃ : " + address);
		

		
	
		
		// Regex to make sure email and password are valid
		String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		
		if (!username.matches(regexEmail)) { // Nếu email ko đúng regex
			request.setAttribute("check", "Email không đúng định dạng regex!");
			/*request.setAttribute("username", username);
			request.setAttribute("address", address);
			request.setAttribute("discount", discount);*/
			
			request.getRequestDispatcher("CartServlet").forward(request, response);
			
			

		}else { // Nếu đúng regex rồi thì lưu vào database và chuyển đến page thank you
			
			try {
				Connection con = DBContext.getConnection();

				//------------CHÈN VÀO BẢNG ORDERS ------------------//
				// ** Chèn giá trị một đơn hàng mới vào bảng orders và lấy ra ID auto generated đó
				String sql1 = "INSERT INTO orders (user_mail, order_status, order_discount_code, order_address)"
						+ "VALUES (?, ?, ?, ?)";

				PreparedStatement preparedStatement = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

				preparedStatement.setString(1, username);
				preparedStatement.setString(2, "1");
				preparedStatement.setString(3, discount);
				preparedStatement.setString(4, address);

				preparedStatement.executeUpdate();

				ResultSet resultSet = preparedStatement.getGeneratedKeys();

				// Lấy ra id vừa tạo trên database
				int insertedID = 0;
				if (resultSet.first()) {
					insertedID = resultSet.getInt(1);
					System.out.println("Id vua moi tao la: " + insertedID);
				}
				
				
				//------------CHÈN CÁC OBJECT TRONG ARRAYLIST VÀO BẢNG ORDERS_DETAIL  ------------------//
				HttpSession session = request.getSession();
				
				ArrayList<Cart> productsArray = (ArrayList) session.getAttribute("productsArray");
				HashMap<String, Integer> productsSelectedMap = (HashMap) session.getAttribute("cartMap");
				
				
				for (int i = 0; i < productsArray.size(); i++) {
					
					String sql2 = "INSERT INTO orders_detail (order_id, product_id, amount_product, price_product) "
							+ "VALUES (?, ?, ?, ?)";

					preparedStatement = con.prepareStatement(sql2);

					preparedStatement.setString(1, insertedID +"");
					preparedStatement.setString(2, productsArray.get(i).getProduct().getId() + "");
					preparedStatement.setString(3, productsArray.get(i).getNumber() + "");
					preparedStatement.setString(4, productsArray.get(i).getTotal() + "");

					preparedStatement.executeUpdate();
				      
				}
				//Sau khi chèn hết vào database thì clearAll tất cả items có trong đơn hàng
				productsArray.clear();
				productsSelectedMap.clear();
				
				session.setAttribute("totalItems", 0);
				session.setAttribute("cartMap", productsSelectedMap);
				

				con.close();

			} catch (Exception ex) {
				System.out.println(ex);
			}
			
			
			
			
			
			
			
			request.setAttribute("Email", username);
			request.getRequestDispatcher("thankyou.jsp").forward(request, response);
		}
		
		
	
	}

}
