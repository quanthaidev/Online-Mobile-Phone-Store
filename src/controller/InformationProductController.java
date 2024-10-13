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
import model.Product;

/**
 * servlet InformationProductController: controller của chức năng xem thông tin
 * chi tiết của một sản phẩm
 */
@WebServlet("/InformationProductController")
public class InformationProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InformationProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		
		Product e = new Product();

		try {
			Connection con = DBContext.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from products where product_id = ?");
			ps.setString(1, idString);
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
		

		request.setAttribute("productSelected", e);
		request.getRequestDispatcher("infoProduct.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
