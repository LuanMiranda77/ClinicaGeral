package MODEL;

import java.util.Date;

public class Historico {
	private int id;
	private String paciente_CPF;
	private String matMedico;
	private String historio;
	private Date dateHist;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaciente_CPF() {
		return paciente_CPF;
	}
	public void setPaciente_CPF(String paciente_CPF) {
		this.paciente_CPF = paciente_CPF;
	}
	public String getMatMedico() {
		return matMedico;
	}
	public void setMatMedico(String matMedico) {
		this.matMedico = matMedico;
	}
	public String getHistorio() {
		return historio;
	}
	public void setHistorio(String historio) {
		this.historio = historio;
	}
	public Date getDateHist() {
		return dateHist;
	}
	public void setDateHist(Date dateHist) {
		this.dateHist = dateHist;
	}
	
	
	

}
