package model;

//Cart lớp: chứa thông tin về 1 đơn hàng hiện tại. Thêm/xóa sửa số lượng sản phẩm trong đơn hàng
public class Cart {
	private Product product;
	private int number;
	private float total;
	
	
	public Cart() {
		super();
	}

	public Cart(Product product, int number, float total) {
		super();
		this.product = product;
		this.number = number;
		this.total = total;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	

	
	
}
