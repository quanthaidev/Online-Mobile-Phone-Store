package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet AddToCartController: Controller của chức năng thêm một sản phẩm vào
 * giỏ hàng
 */
@WebServlet("/AddToCartController")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int total = 0;


	public AddToCartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Map chứa tất cả id sản phẩm đã thêm vào đơn hàng.
		HashMap<String, Integer> productsSelectedMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("cartMap")== null) {
			System.out.println("không có sản phẩm trong map");
		}else {//Nếu có sản phẩm trong map, thì nạp vào
			productsSelectedMap = (HashMap) session.getAttribute("cartMap");
		}
		
		

		// Lấy id từ request param
		String idSelected = request.getParameter("idProduct");

		// Lấy ra số lượng hiện tại của sản phẩm vừa được thêm
		int numberOfProductSelected;
		System.out.println("gia tri action: " + request.getParameter("action"));

		System.out.println("gia tri cart: " + request.getParameter("cart"));

		// đang ở page infoProduct.jsp
		if (request.getParameter("action").equals("info")) {
			// Nếu tồn tại Key id đó trong map, thì lúc này mới lấy số lượng đó và ++
			if (productsSelectedMap.containsKey(idSelected)) {
				// Lấy ra số lượng hiện tại của sản phẩm đó trong Map
				numberOfProductSelected = productsSelectedMap.get(idSelected);
				numberOfProductSelected++;
				productsSelectedMap.put(idSelected, numberOfProductSelected);

			} else { // Còn nếu không tồn tại thì thêm mới và mặc định numberOfProductSelected = 1;
				numberOfProductSelected = 1;
				productsSelectedMap.put(idSelected, numberOfProductSelected);

			}

			// Lấy ra key và value trong hashmap và tổng số lượng sản phẩm có trong cart Map
			total = 0;
			int totalNumInMap2 = 0;
			for (String i : productsSelectedMap.keySet()) {
				System.out.print("ID sp la: " + i + ". ");
				System.out.print("So luong la: " + productsSelectedMap.get(i));

				totalNumInMap2 = productsSelectedMap.get(i);
				total = total + totalNumInMap2;
				System.out.println();
			}
			System.out.println("Tong san pham la: " + total);
			System.out.println("--------");

			// session scope
			session.setAttribute("totalItems", total);
			session.setAttribute("cartMap", productsSelectedMap);

			// Quay trở lại trang vừa nãy
			String id = idSelected;
			request.getRequestDispatcher("InformationProductController?id=" + id).forward(request, response);

		}

		// Đang ở page cart.jsp
		if (request.getParameter("action").equals("add") && request.getParameter("cart").equals("true")) {
			System.out.println("gia tri cart add: " + request.getParameter("cart"));
			// Cộng thêm

			// Lấy ra số lượng hiện tại của sản phẩm đó trong Map
			numberOfProductSelected = productsSelectedMap.get(idSelected);
			numberOfProductSelected++;
			productsSelectedMap.put(idSelected, numberOfProductSelected);

			// Lấy ra key và value trong hashmap và tổng số lượng sản phẩm có trong cart Map
			total = 0;
			int totalNumInMap2 = 0;
			for (String i : productsSelectedMap.keySet()) {
				System.out.print("ID sp la: " + i + ". ");
				System.out.print("So luong la: " + productsSelectedMap.get(i));

				totalNumInMap2 = productsSelectedMap.get(i);
				total = total + totalNumInMap2;
				System.out.println();
			}
			System.out.println("Tong san pham la: " + total);
			System.out.println("--------");

			// session scope
			session.setAttribute("totalItems", total);
			session.setAttribute("cartMap", productsSelectedMap);

			//  Quay trở lại trang CartServlet rồi chuyển đến cart.jsp
			
			//request.getRequestDispatcher("CartServlet").forward(request, response);
			response.sendRedirect("CartServlet");
			
		} else if (request.getParameter("action").equals("subtract") && request.getParameter("cart").equals("true")) {
			System.out.println("gia tri cart substract: " + request.getParameter("cart"));

			// Trừ đi
			// Lấy ra số lượng hiện tại của sản phẩm đó trong Map
			numberOfProductSelected = productsSelectedMap.get(idSelected);
			numberOfProductSelected--;
			if (numberOfProductSelected == 0) { // Nếu trừ đi hoài mà còn bằng 0, thì lúc này remove object đó ra khỏi
												// HashMap
				productsSelectedMap.remove(idSelected);
			} else {
				productsSelectedMap.put(idSelected, numberOfProductSelected);
			}

			// Lấy ra key và value trong hashmap và tổng số lượng sản phẩm có trong cart Map
			total = 0;
			int totalNumInMap2 = 0;
			for (String i : productsSelectedMap.keySet()) {
				System.out.print("ID sp la: " + i + ". ");
				System.out.print("So luong la: " + productsSelectedMap.get(i));

				totalNumInMap2 = productsSelectedMap.get(i);
				total = total + totalNumInMap2;
				System.out.println();
			}
			System.out.println("Tong san pham la: " + total);
			System.out.println("--------");

			// session scope
			session.setAttribute("totalItems", total);
			session.setAttribute("cartMap", productsSelectedMap);

			// Quay trở lại trang CartServlet rồi chuyển đến cart.jsp
			
			
			//request.getRequestDispatcher("CartServlet").forward(request, response);
			response.sendRedirect("CartServlet");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
