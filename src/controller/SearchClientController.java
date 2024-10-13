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
import model.Product;

/**
 * servlet SearchClientController: controller của chức năng tìm kiếm dành cho
 * người dùng
 */
@WebServlet("/SearchClientController")
public class SearchClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchClientController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ** ArrayList chứa tập hợp các sản phẩm có chứa từ khóa
	public ArrayList<Product> productsSearchList = new ArrayList<>();
	
	// ** ArrayList chứa tập hợp các sản phẩm theo pagination
	public ArrayList<Product> subArray = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productsSearchList.clear();
		System.out.println("productsSearchList: " + productsSearchList);
		
		subArray.clear();
		System.out.println("subArray: " + subArray);

		String searchKey = request.getParameter("searchKey");
	

		try {
			Connection con = DBContext.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM shoppingdb.products WHERE product_name LIKE '%" + searchKey + "%'");
			ResultSet rs = ps.executeQuery();

			// Chạy vòng lặp để cho các object Product vào List được lấy ra từ code SQL trên
			while (rs.next()) {
				Product e = new Product();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setDescription(rs.getString(3));
				e.setPrice(rs.getFloat(4));
				e.setSrc(rs.getString(5));
				e.setType(rs.getString(6));
				e.setBrand(rs.getString(7));

				productsSearchList.add(e);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		//  Vòng lặp check xem arraylist có work với từ khóa chưa
		System.out.println("GiÃ¡ trá»‹ trong productsSearchList");
		for (int i = 0; i < productsSearchList.size(); i++) {
			System.out.println(productsSearchList.get(i).getName() + " | " + productsSearchList.get(i).getId());
			System.out.println();
		}
		System.out.println("Size cua productsSearchList luc nay la: " + productsSearchList.size());
		System.out.println("--------------------------------------");

		
		
		
		// ** Array sub để lấy các 8 objects từ productsSearchList gốc

		
		String pageid = request.getParameter("page");
		if(pageid == null) {
			pageid = "1";
		}
		int pageidOfficial = Integer.parseInt(pageid);
		System.out.println("pageidOfficial: " + 1);
		System.out.println("pageidOfficial - 1: " + (pageidOfficial - 1));
		
		
		//System.out.println("Gia tri cua pageid: " + pageid);
		
		int start = pageidOfficial -1 ; // Điểm bắt đầu lấy ra object ( trừ đi còn 0 tương ứng với pag 1, trừ đi còn 1 tương ứng với pag 2)
		//System.out.println("start: " + start);
		
		int total = 8; // Lấy ra 8 object
		//System.out.println("total: " + total);
		
		int max = (start + 1) * total;
		//System.out.println("max: " + max);
		
		int sizeOriginList = productsSearchList.size(); // Dùng biến này để tránh việc bị tràn quá index trong Arraylist gốc
		//System.out.println("sizeOriginList: " + sizeOriginList);			
		
		

		
		if (total < sizeOriginList) {
			if (start == 0) {
				for (int i = start; i < total; i++) {
					subArray.add(productsSearchList.get(i));
				}
				System.out.println("vo if 1");
			} else if (max <= sizeOriginList) {
				for (int i = start * total; i < max; i++) {
					subArray.add(productsSearchList.get(i));
				}
				System.out.println("vo if 2");

			} else {
//				System.out.println("start * total = " + start * total);
//				System.out.println("sizeOriginList luc nay la " + sizeOriginList);
//				System.out.println(productsSearchList.size() + " vo if 3");
				
//				for (int i = 0; i < subArray.size(); i++) {
//				      System.out.println("subArray: "+ subArray.get(i).getId());
//				  }
				
				for (int i = start * total; i < sizeOriginList; i++) {
					
					subArray.add( productsSearchList.get(i) );
					//System.out.println("productsSearchList: " + productsSearchList.get(i).getId());
				}
				
//				for (int i = 0; i < subArray.size(); i++) {
//				      System.out.println("subArray new: "+ subArray.get(i).getId());
//				  }
				
				System.out.println("vo if 3");
			}
		} else {
			for (int i = 0; i < sizeOriginList; i++) {
			      subArray.add(productsSearchList.get(i));
			}
			System.out.println("vo if 4");
		}

		//Vòng lặp check xem subArray có lấy được object từ productsSearchList chưa
		System.out.println("GiÃ¡ trá»‹ trong subArray");
		for (int i = 0; i < subArray.size(); i++) {
			System.out.println(subArray.get(i).getName() + " | " + subArray.get(i).getId());
			System.out.println();

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		// Sau khi Ä‘Ã£ cÃ³ subArray rá»“i, thÃ¬ tiáº¿n hÃ nh pass qua search.jsp
		request.setAttribute("total", productsSearchList.size());
		
		request.setAttribute("subArray", subArray);
		request.setAttribute("searchKey", searchKey);

		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

	

}
