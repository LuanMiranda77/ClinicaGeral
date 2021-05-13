package MODEL.Conexao;
import java.util.ArrayList;

import DAOSQL.PacienteSql;
import DAOSQL.TestConexao;
import MODEL.Paciente;

public class CXPaciente {
	private PacienteSql con = new PacienteSql();
	
	///metods do grud 
	public void set(Paciente novo) throws Exception {
		TestConexao.retornar().fabricarPaciente().set(novo);
	}
	public Paciente get(int id) {
		return TestConexao.retornar().fabricarPaciente().get(id);
	}

	public Paciente get(String nome) {
		return TestConexao.retornar().fabricarPaciente().get(nome);
	}

	public void update(Paciente editado) {
		TestConexao.retornar().fabricarPaciente().update(editado);
	}

	public void remove(int id) {
		TestConexao.retornar().fabricarPaciente().remove(id);
	}

	public ArrayList<Paciente> getLista(){
		return TestConexao.retornar().fabricarPaciente().getLista();
	}

	public int getTamanho() {
		return TestConexao.retornar().fabricarPaciente().getTamanho();
	}
	public int getId() {
		return TestConexao.retornar().fabricarPaciente().getId();
	}
	public void preencherLista() {
		con.preencherLista();
	}
}
