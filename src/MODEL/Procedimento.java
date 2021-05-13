package MODEL;

public class Procedimento {
	private int id;
	private String desc;
	private double valor;
	
	
	public Procedimento() {
		
	}
	public Procedimento(String desc,double valor) {
		this.desc=desc;
		this.valor=valor;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//metods
		
		

}
