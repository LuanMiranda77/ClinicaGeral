package CONTROL;

import java.sql.ResultSet;
import java.util.Date;
import MODEL.Historico;
import MODEL.Conexao.CXHistorico;



public class CTHistorico {
	
	private static CXHistorico cox = new CXHistorico();
	
	public static void insert(Historico novo) {
		cox.insert(novo);
	}
	public static Historico getHistoricoId(int id) {
		return cox.getHistoricoId(id);
	}

	public static ResultSet getHistoricoCompleto(String cpf) {
		return cox.getHistoricoCompleto(cpf);
	}
	
	public static Historico getHistoricoDate(Date data) {
		return cox.getHistoricoDate(data);
	}

	public static void update(Historico editado) {
		cox.update(editado);
	}

	public static void remove(int id) {
		cox.remove(id);
	}


	public static int getTamanho() {
		return cox.getTamanho();
	}
	public static int getId() {
		return cox.getId();
	}
	public static String getHistoricoIdpac(int idpac) {
		return cox.getHistorico(idpac);
	}

}
