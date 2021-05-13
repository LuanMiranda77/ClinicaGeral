package MODEL;  
import java.util.Date;

public class Paciente extends PessoaFisica{
	
	private int id;
	private String foto;
	private String convenio;
	private String numCartao;
	private Date validadeCrad;
	private float altura;
	private float peso;
	private String tipoSangue;
	private String celular3;
	private String obs;
	private int historico;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getCelular3() {
		return celular3;
	}
	public void setCelular3(String celular3) {
		this.celular3 = celular3;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public Date getValidadeCrad() {
		return validadeCrad;
	}
	public void setValidadeCrad(Date validadeCrad) {
		this.validadeCrad = validadeCrad;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getTipoSangue() {
		return tipoSangue;
	}
	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getHistorico() {
		return historico;
	}
	public void setHistorico(int historico) {
		this.historico = historico;
	}
	
	
	//metods
		

}
