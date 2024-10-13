package model;

//Orders lớp: chứa thông tin về một đơn hàng, bao gồm danh sách sản phẩm của đơn hàng, thông tin người mua hàng
public class Orders {
	private String email;
	private int id;
	private int status;
	private String date;
	private String discountCode;
	private String address;
	
	private float totalPrice;
	
	
	public Orders() {
		super();
	}


	public Orders(String email, int id, int status, String date, String discountCode, String address, float totalPrice) {
		super();
		this.email = email;
		this.id = id;
		this.status = status;
		this.date = date;
		this.discountCode = discountCode;
		this.address = address;
		this.totalPrice = totalPrice;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDiscountCode() {
		return discountCode;
	}


	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	

}
