package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBContext;
import model.Orders;
import model.ProductOrders;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idOrder = request.getParameter("idOrder");
		System.out.println(idOrder);

		if (idOrder == null) { // Nếu id đơn hàng là null, thì tức là đang trang chủ của admin
			ArrayList<Orders> arrayOrders = new ArrayList<>(); //  Tạo mảng chứa danh sách orders
			try {
				Connection con = DBContext.getConnection();

				// Câu lệnh sql này lấy các đơn hàng theo thứ tự mới nhất (giảm dần ID )DESC
				PreparedStatement ps = con.prepareStatement("select * from orders order by order_id desc");
				ResultSet rs = ps.executeQuery();

				// Chạy vòng lặp để cho các object Emp vào List được lấy ra từ code SQL trên
				while (rs.next()) {
					Orders e = new Orders();

					e.setEmail(rs.getString(1));
					e.setId(rs.getInt(2));
					e.setStatus(rs.getInt(3));
					e.setDate(rs.getString(4));
					e.setDiscountCode(rs.getString(5));
					e.setAddress(rs.getString(6));

					// rs.getInt(2) là lấy ra ID của đơn hàng đó để tìm trong orders_detail
					PreparedStatement ps1 = con.prepareStatement(
							"SELECT ROUND(SUM(price_product), 2) as SUM from orders_detail where order_id = "
									+ rs.getInt(2));
					ResultSet rs1 = ps1.executeQuery();
					rs1.next();
					e.setTotalPrice(rs1.getFloat(1));

					arrayOrders.add(e);
				}
				con.close();

				// Sau đó forward qua admin.jsp

				request.setAttribute("idOrder", "portal");
				request.setAttribute("arrayOrders", arrayOrders);
				request.getRequestDispatcher("admin.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println(e);
			}

		} else { // Nếu idOrder không null, tức là có giá trị param khi click vào thì nó sẽ hiển
					// thị chi tiết sản phẩm trong đơn hàng
			
			ArrayList<ProductOrders> arrayProductOrders = new ArrayList<>(); // Táº¡o máº£ng chá»©a danh sÃ¡ch orders
			
			try {
				Connection con = DBContext.getConnection();

				// câu lệnh sql này join 2 bảng orders_detail và products để lấy thông tin
				PreparedStatement ps = con.prepareStatement(
						"select orders_detail.order_id, orders_detail.product_id, products.product_name, \r\n"
								+ "products.product_price, orders_detail.amount_product, orders_detail.price_product\r\n"
								+ "from orders_detail join products \r\n"
								+ "on orders_detail.product_id = products.product_id and orders_detail.order_id = "
								+ idOrder);
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {
					ProductOrders e = new ProductOrders();
					e.setIdO(rs.getInt(1));
					e.setIdP(rs.getInt(2));
					e.setNameP(rs.getString(3));
					e.setPrice(rs.getFloat(4));
					e.setAmountP(rs.getInt(5));
					e.setTotalP(rs.getFloat(6));
					
					arrayProductOrders.add(e);
					
				}

				

				con.close();

				
				request.setAttribute("arrayProductOrders", arrayProductOrders);
				
				request.getRequestDispatcher("admin.jsp").forward(request, response);

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
