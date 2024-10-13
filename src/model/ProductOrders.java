package model;

//ProductOrders lớp: chứa thông tin của một sản phẩm trong đơn hàng
public class ProductOrders {
	
	private int idO;
	private int idP ;
	private String nameP;
	private float price;
	private int amountP ;
	private float totalP ;
	
	public ProductOrders() {
		super();
	}

	

	public int getIdO() {
		return idO;
	}

	public void setIdO(int idO) {
		this.idO = idO;
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public String getNameP() {
		return nameP;
	}

	public void setNameP(String nameP) {
		this.nameP = nameP;
	}

	
	
	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public int getAmountP() {
		return amountP;
	}

	public void setAmountP(int amountP) {
		this.amountP = amountP;
	}

	public float getTotalP() {
		return totalP;
	}

	public void setTotalP(float totalP) {
		this.totalP = totalP;
	}
	
	

}
