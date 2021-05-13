package MODEL;
import java.util.Date;


public class AgendaConsulta {
	
	private int id;
	private String hora;
	private Date data;
	private int idPaciente;
	private String idProce;
	private String matMedico;
	private String status;
	private float valor;
	private float desc;
	private String obs;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getIdProce() {
		return idProce;
	}
	public void setIdProce(String idProce) {
		this.idProce = idProce;
	}
	public String getMatMedico() {
		return matMedico;
	}
	public void setMatMedico(String matMedico) {
		this.matMedico = matMedico;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public float getDesc() {
		return desc;
	}
	public void setDesc(float desc) {
		this.desc = desc;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
	
	

}
