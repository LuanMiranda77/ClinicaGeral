package MODEL;

public class Medicamento {
	private int id;
	private String desc;
	private double mg;
	private String quant;
	private String modoUso;
	private String via;
	
	
	public Medicamento() {
		
	}
	public Medicamento(String desc,double mg) {
		this.desc=desc;
		this.mg=mg;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getmg() {
		return mg;
	}
	public void setmg(double mg) {
		this.mg = mg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuant() {
		return quant;
	}
	public void setQuant(String quant) {
		this.quant = quant;
	}
	public String getModoUso() {
		return modoUso;
	}
	public void setModoUso(String modoUso) {
		this.modoUso = modoUso;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	
	
	//metods
		
		

}
