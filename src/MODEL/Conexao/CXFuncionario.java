package MODEL.Conexao;

import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Funcionario;

public class CXFuncionario {
	
	///metods do grud 
	//metods
			public void set(Funcionario novo) {
				TestConexao.retornar().fabricarFun().set(novo);
			}
			public Funcionario get(int id) {
				return TestConexao.retornar().fabricarFun().get(id);
			}

			public Funcionario get(String nome) {
				return TestConexao.retornar().fabricarFun().get(nome);
			}

			public void update(Funcionario editado) {
				TestConexao.retornar().fabricarFun().update(editado);
			}

			public void remove(int id) {
				TestConexao.retornar().fabricarFun().remove(id);
			}

			public ArrayList<Funcionario> getLista(){
				return TestConexao.retornar().fabricarFun().getLista();
			}

			public int getTamanho() {
				return TestConexao.retornar().fabricarFun().getTamanho();
			}
			public int getId() {
				return TestConexao.retornar().fabricarFun().getId();
			}
			
			
}
