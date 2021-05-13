package MODEL.Conexao;

import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Especialidade;

public class CXEspecialidade {
	
	///metods do grud 
	public void set(Especialidade novo) {
		TestConexao.retornar().fabricarEspecialidade().set(novo);
	}
	public Especialidade get(int id) {
		return TestConexao.retornar().fabricarEspecialidade().get(id);
	}

	public Especialidade get(String nome) {
		return TestConexao.retornar().fabricarEspecialidade().get(nome);
	}

	public void update(Especialidade editado) {
		TestConexao.retornar().fabricarEspecialidade().update(editado);
	}

	public void remove(int id) {
		TestConexao.retornar().fabricarEspecialidade().remove(id);
	}

	public ArrayList<Especialidade> getLista(){
		return TestConexao.retornar().fabricarEspecialidade().getLista();
	}

	public int getTamanho() {
		return TestConexao.retornar().fabricarEspecialidade().getTamanho();
	}
	
}
