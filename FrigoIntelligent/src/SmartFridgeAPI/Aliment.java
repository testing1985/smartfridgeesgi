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

	public String getM_sName() {
		return m_sName;
	}

	public void setM_sName(String name) {
		m_sName = name;
	}

	public int getM_iType() {
		return m_iType;
	}

	public void setM_iType(int type) {
		m_iType = type;
	}

	public int getM_iQuantity() {
		return m_iQuantity;
	}

	public void setM_iQuantity(int quantity) {
		m_iQuantity = quantity;
	}

	public float getM_iPrice() {
		return m_iPrice;
	}

	public void setM_iPrice(float price) {
		m_iPrice = price;
	}

	public long getM_iPeremption() {
		return m_iPeremption;
	}

	public void setM_iPeremption(long peremption) {
		m_iPeremption = peremption;
	}
	
}
