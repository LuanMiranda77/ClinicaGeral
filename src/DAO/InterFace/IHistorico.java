package DAO.InterFace;


import java.sql.ResultSet;
import java.util.Date;

import MODEL.Historico;
public interface IHistorico {

	public void insert(Historico cdto);
	public void remove(int id);
	public Historico getHistorico(int id);
	public void update(Historico editado);
	public ResultSet getHistoricoCompleto(String cpf);
	public String getHistoricoIntervalo(int idpac);
	public Historico getHistoricoData(Date data);
	public int getTamanho();
	public int getId();

}
