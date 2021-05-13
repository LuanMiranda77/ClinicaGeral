package MODEL;

public class Config {
	
	//config banco
	private String URL; // incica o caminho do banco de dados
	private String USER; // aqui vai o nome usuario que vc quer acessar
	private String PASS;
	
	//config bkp externo
	private String backupExterno;
	
	//config auto zap
	private String usar_Envio_automaico_zap;
	private String modelo_mesagem_zap;
	
	//config email
	private String usar_Envio_auto_email;
	private String email;
	private String senha_Email;
	private String porta_Email;
	private String servidor_Email;
	
	//config da verção
	private String versao;
	private String TipoInstalacao;

	
	
	
	
	
	
	public String getTipoInstalacao() {
		return TipoInstalacao;
	}
	public void setTipoInstalacao(String tipoInstalacao) {
		TipoInstalacao = tipoInstalacao;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getUSER() {
		return USER;
	}
	public void setUSER(String uSER) {
		USER = uSER;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	public String getBackupExterno() {
		return backupExterno;
	}
	public void setBackupExterno(String backupExterno) {
		this.backupExterno = backupExterno;
	}
	public String getUsar_Envio_automaico_zap() {
		return usar_Envio_automaico_zap;
	}
	public void setUsar_Envio_automaico_zap(String usar_Envio_automaico_zap) {
		this.usar_Envio_automaico_zap = usar_Envio_automaico_zap;
	}
	public String getUsar_Envio_auto_email() {
		return usar_Envio_auto_email;
	}
	public void setUsar_Envio_auto_email(String usar_Envio_auto_email) {
		this.usar_Envio_auto_email = usar_Envio_auto_email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha_Email() {
		return senha_Email;
	}
	public void setSenha_Email(String senha_Email) {
		this.senha_Email = senha_Email;
	}
	public String getPorta_Email() {
		return porta_Email;
	}
	public void setPorta_Email(String porta_Email) {
		this.porta_Email = porta_Email;
	}
	public String getServidor_Email() {
		return servidor_Email;
	}
	public void setServidor_Email(String servidor_Email) {
		this.servidor_Email = servidor_Email;
	}
	public String getModelo_mesagem_zap() {
		return modelo_mesagem_zap;
	}
	public void setModelo_mesagem_zap(String modelo_mesagem_zap) {
		this.modelo_mesagem_zap = modelo_mesagem_zap;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	
	
	
	
	
	
	

}
