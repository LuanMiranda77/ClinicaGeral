package MODEL.Conexao;

import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Convenio;

public class CXConvenio {
	
	///metods do grud 
			public void set(Convenio novo) {
				TestConexao.retornar().fabricarConv().set(novo);
			}
			public Convenio get(int id) {
				return TestConexao.retornar().fabricarConv().get(id);
			}

			public Convenio get(String nome) {
				return TestConexao.retornar().fabricarConv().get(nome);
			}

			public void update(Convenio editado) {
				TestConexao.retornar().fabricarConv().update(editado);
			}

			public void remove(int id) {
				TestConexao.retornar().fabricarConv().remove(id);
			}

			public ArrayList<Convenio> getLista(){
				return TestConexao.retornar().fabricarConv().getLista();
			}

			public int getTamanho() {
				return TestConexao.retornar().fabricarConv().getTamanho();
			}
			public int getId() {
				return TestConexao.retornar().fabricarConv().getId();
			}
			
}
