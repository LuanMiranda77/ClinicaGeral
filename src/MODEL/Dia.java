package MODEL;

import java.util.ArrayList;
import java.util.Date;

public class Dia {
	private String nome;
	private ArrayList<String> hora = new ArrayList<>();
	private Date data;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public void addHora(String hora) {
		this.hora.add(hora);
	}
	public void removerHora(int index) {
		this.hora.remove(index);
	}
	public ArrayList<String> getHora() {
		return hora;
	}
	public void setHora(ArrayList<String> hora) {
		this.hora = hora;
	}
	
	
	
	
	

}
