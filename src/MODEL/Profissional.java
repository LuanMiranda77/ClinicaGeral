package MODEL;
import java.util.ArrayList;
public class Profissional  extends PessoaFisica{
	
	private int id;
	private String matricula;
	private double Salario;
	private String agenda;
	private String especialidade;
	private ArrayList<String> procedimento;
	private String foto;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getSalario() {
		return Salario;
	}
	public void setSalario(double salario) {
		Salario = salario;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public ArrayList<String> getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(ArrayList<String> procedimento) {
		this.procedimento = procedimento;
	}
	
}
