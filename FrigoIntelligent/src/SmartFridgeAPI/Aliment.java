package SmartFridgeAPI;

public class Aliment {
	private String m_sName;
		
	// Quantity 
	private int m_iQuantity;
	
	private float m_iPrice;
	
	private String m_sUnite;
	
	// Peremption date
	long m_iPeremption;
	
	public Aliment( String name )
	{
		m_sName = name;
		m_iQuantity = 1;
		m_iPrice = (float) 1.0;
		m_iPeremption = 0 ;	
	}
	
	public Aliment(String name, int quantity, float price, long peremption , String sUnite) {
		m_sName = name;
		m_iQuantity = quantity;
		m_iPrice = price;
		m_iPeremption = peremption;
		m_sUnite = sUnite;
	}

	public String getName() {
		return m_sName;
	}

	public void setName(String name) {
		m_sName = name;
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
	
	public void print()
	{
		System.out.println( m_iQuantity + " " + m_sName + " for " + m_iPrice + "€, best before " + m_iPeremption ) ;
	}
	
}
