package pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBContext;
import model.Product;

public class ProductPaginationDao {

	// Method này lấy ra danh sách các dòng từ dòng pageid 9 trở đi và tổng total
	// hiện là 8
	public List<Product> getRecords(int start, int total) {
		List<Product> list = new ArrayList<Product>();
		try {
			Connection con = DBContext.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from products limit " + (start - 1) + "," + total);
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
				
				list.add(e);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	

	// Đếm số hàng có trong table
	public int getRows() {
		int rows = 0;
		try {
			Connection con = DBContext.getConnection();

			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM products");
			ResultSet rs = ps.executeQuery();

			rs.next();
			rows = rs.getInt(1);

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return rows;
	}
	
	
	
	
	
	
	
	

}
