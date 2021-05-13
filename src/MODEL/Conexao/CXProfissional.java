package MODEL.Conexao;

import java.sql.SQLException;
import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Profissional;

public class CXProfissional {
	
	///metods do grud 
			public void set(Profissional novo) throws SQLException {
				TestConexao.retornar().fabricarProfissional().set(novo);
			}
			public Profissional getMatMedico(String matricula) {
				return TestConexao.retornar().fabricarProfissional().getMedico(matricula);
			}

			public Profissional get(String nome) {
				return TestConexao.retornar().fabricarProfissional().get(nome);
			}

			public void update(Profissional editado) {
				TestConexao.retornar().fabricarProfissional().update(editado);
			}

			public void remove(int id) {
				TestConexao.retornar().fabricarProfissional().remove(id);
			}

			public ArrayList<Profissional> getLista(){
				return TestConexao.retornar().fabricarProfissional().getLista();
			}

			public int getTamanho() {
				return TestConexao.retornar().fabricarProfissional().getTamanho();
			}
			public ArrayList<String> getListaProced(String nome) {
				return TestConexao.retornar().fabricarProfissional().getProced(nome);
			}
			public int getId() {
				return TestConexao.retornar().fabricarProfissional().getId();
			}
			
}
