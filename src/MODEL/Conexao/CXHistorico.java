package MODEL.Conexao;
import java.sql.ResultSet;
import java.util.Date;
import DAOSQL.TestConexao;
import MODEL.Historico;

public class CXHistorico {
	
	///metods do grud 
				public void insert(Historico novo) {
					TestConexao.retornar().fabricarHistorico().insert(novo);
				}
				public Historico getHistoricoId(int id) {
					return TestConexao.retornar().fabricarHistorico().getHistorico(id);
				}

				public ResultSet getHistoricoCompleto(String cpf) {
					return TestConexao.retornar().fabricarHistorico().getHistoricoCompleto(cpf);
				}
				
				public Historico getHistoricoDate(Date data) {
					return TestConexao.retornar().fabricarHistorico().getHistoricoData(data);
				}

				public void update(Historico editado) {
					TestConexao.retornar().fabricarHistorico().update(editado);
				}

				public void remove(int id) {
					TestConexao.retornar().fabricarHistorico().remove(id);
				}


				public int getTamanho() {
					return TestConexao.retornar().fabricarHistorico().getTamanho();
				}
				public int getId() {
					return TestConexao.retornar().fabricarHistorico().getId();
				}
				public String getHistorico(int idpac ) {
					return TestConexao.retornar().fabricarHistorico().getHistoricoIntervalo(idpac);
				}
				
}
