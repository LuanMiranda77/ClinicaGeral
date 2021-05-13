package MODEL.Conexao;
import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.AgendaProf;

public class CXAgendaProf {
	
	///metods do grud 
		public void set(AgendaProf novo) {
			TestConexao.retornar().fabricarAgendaProf().set(novo);
		}
		public AgendaProf get(int id) {
			return TestConexao.retornar().fabricarAgendaProf().get(id);
		}

		public AgendaProf get(String nome) {
			return TestConexao.retornar().fabricarAgendaProf().get(nome);
		}

		public void update(AgendaProf editado) {
			TestConexao.retornar().fabricarAgendaProf().update(editado);
		}

		public void remove(int id) {
			TestConexao.retornar().fabricarAgendaProf().remove(id);
		}

		public ArrayList<AgendaProf> getLista(){
			return TestConexao.retornar().fabricarAgendaProf().getLista();
		}

		public int getTamanho() {
			return TestConexao.retornar().fabricarAgendaProf().getTamanho();
		}
		public int getId() {
			return TestConexao.retornar().fabricarAgendaProf().getId();
		}
		
		
}
