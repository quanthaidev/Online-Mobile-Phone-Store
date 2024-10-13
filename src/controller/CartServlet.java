package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import model.Product;

/**
 * CartServlet: Quản lý khi nhấp vào icon cart
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Tổng tiền
		float totalAmount = 0;
		
		// ArrayList chứa tất cả sản phẩm lấy từ HashMap trong AddToCartController.java
		ArrayList<Cart> productsArray = new ArrayList<>();


		// Tạo các Objects từ Map trong AddToCartController.java, sau đó cho vào
		// ArrayList
		// Lấy ra key và value trong hashmap và tổng số lượng sản phẩm có trong cart Map
		HttpSession session = request.getSession();
		HashMap<String, Integer> productsSelected = (HashMap<String, Integer>) session.getAttribute("cartMap");

		System.out.println("ArrayList co bi null khong?: " + productsSelected);

		if (productsSelected != null) {
			// Chạy vòng lặp để thêm sản phẩm vào ArrayList
			for (String i : productsSelected.keySet()) {
				System.out.print("ID sp la: " + i + ". ");
				System.out.print("So luong la: " + productsSelected.get(i));

				System.out.println();

				// Tạo object để thêm vào ArrayList
				Product e = new Product();

				try { // Truy vấn DB và thêm sản phẩm vào ArrayList
					Connection con = DBContext.getConnection();

					PreparedStatement ps = con.prepareStatement("select * from products where product_id = ?");
					ps.setString(1, i);
					ResultSet rs = ps.executeQuery();
					rs.next();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setDescription(rs.getString(3));
					e.setPrice(rs.getFloat(4));
					e.setSrc(rs.getString(5));
					e.setType(rs.getString(6));
					e.setBrand(rs.getString(7));

					con.close();

				} catch (Exception ex) {
					System.out.println(ex);
				}

				int num = productsSelected.get(i);
				float total = e.getPrice() * num;
				System.out.println("gia tri float total: " + total);
				productsArray.add(new Cart(e, num, total));

			}

			// Tính tổng giá tiền
			for (int i = 0; i < productsArray.size(); i++) {
				float temp = productsArray.get(i).getTotal();
				totalAmount = totalAmount + temp;
			}
			System.out.println("Tong tien la: " + totalAmount);

			System.out.println("--------");
//			//test chạy vòng lặp arraylist
			for (int i = 0; i < productsArray.size(); i++) {
				System.out
						.println(productsArray.get(i).getProduct().getName() + " : " + productsArray.get(i).getTotal());
				System.out.println(productsArray.get(i).getProduct().getPrice() * productsArray.get(i).getNumber());
			}

			// Chuyển đến cart.jsp để hiển thị sản phẩm
			request.setAttribute("cartProductsArray", productsArray);
			request.setAttribute("totalAmount", totalAmount);
			session.setAttribute("productsArray", productsArray);
		}

		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
