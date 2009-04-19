package SmartFridgeAPI;




public class Aliment {
	private String m_sName;
	
	// type of aliment ( vegetable, fruit, meat, ... )
	private int m_iType;
	
	// Quantity 
	private int m_iQuantity;
	
	private float m_iPrice;
	
	// Peremption date
	long m_iPeremption;
	
	public Aliment( String name )
	{
		m_sName = name;
		m_iType = 0;
		m_iQuantity = 1;
		m_iPrice = (float) 1.0;
		m_iPeremption = 0 ;	
	}
	
	public Aliment(String name, int type, int quantity, float price, long peremption) {
		m_sName = name;
		m_iType = type;
		m_iQuantity = quantity;
		m_iPrice = price;
		m_iPeremption = peremption;
	}

	public String getName() {
		return m_sName;
	}

	public void setName(String name) {
		m_sName = name;
	}

	public int getType() {
		return m_iType;
	}

	public void setType(int type) {
		m_iType = type;
	}

	public int getQuantity() {
		return m_iQuantity;
	}

	public void setQuantity(int quantity) {
		m_iQuantity = quantity;
	}

	public float getPrice() {
		return m_iPrice;
	}

	public void setPrice(float price) {
		m_iPrice = price;
	}

	public long getPeremption() {
		return m_iPeremption;
	}

	public void setPeremption(long peremption) {
		m_iPeremption = peremption;
	}
	
}
