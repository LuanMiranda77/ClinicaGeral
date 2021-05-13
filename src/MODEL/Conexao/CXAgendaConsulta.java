package MODEL.Conexao;
import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.AgendaConsulta;

public class CXAgendaConsulta {
	
	///metods do grud 
	public void set(AgendaConsulta novo) {
		TestConexao.retornar().fabricarAgendaCon().set(novo);
	}
	public AgendaConsulta get(int id) {
		return TestConexao.retornar().fabricarAgendaCon().get(id);
	}

	public AgendaConsulta get(String nome) {
		return TestConexao.retornar().fabricarAgendaCon().get(nome);
	}

	public void update(AgendaConsulta editado) {
		TestConexao.retornar().fabricarAgendaCon().update(editado);
	}

	public void remove(int id) {
		TestConexao.retornar().fabricarAgendaCon().remove(id);
	}

	public ArrayList<AgendaConsulta> getLista(){
		return TestConexao.retornar().fabricarAgendaCon().getLista();
	}

	public int getTamanho() {
		return TestConexao.retornar().fabricarAgendaCon().getTamanho();
	}
	public int getId() {
		return TestConexao.retornar().fabricarAgendaCon().getId();
	}
}
