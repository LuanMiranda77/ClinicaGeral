package MODEL.Conexao;

import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Procedimento;

public class CXProcedimento {
	
	///metods do grud 
				public void set(Procedimento novo) {
					TestConexao.retornar().fabricarProcedimento().set(novo);
				}
				public Procedimento get(int id) {
					return TestConexao.retornar().fabricarProcedimento().get(id);
				}

				public Procedimento get(String convenio) {
					return TestConexao.retornar().fabricarProcedimento().get(convenio);
				}

				public void update(Procedimento editado) {
					TestConexao.retornar().fabricarProcedimento().up(editado);
				}

				public void remove(int id) {
					TestConexao.retornar().fabricarProcedimento().remove(id);
				}

				public ArrayList<Procedimento> getLista(){
					return TestConexao.retornar().fabricarProcedimento().getlista();
				}

				public int getTamanho() {
					return TestConexao.retornar().fabricarProcedimento().getTamanho();
				}
				public int getId() {
					return TestConexao.retornar().fabricarProcedimento().getId();
				}
				
}
