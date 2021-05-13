package MODEL.Conexao;

import java.util.ArrayList;

import DAOSQL.TestConexao;
import MODEL.Medicamento;

public class CXMedicamento {
	
	///metods do grud 
				public void set(Medicamento novo) {
					TestConexao.retornar().fabricarMedicamento().set(novo);
				}
				public Medicamento get(int id) {
					return TestConexao.retornar().fabricarMedicamento().get(id);
				}

				public Medicamento get(String convenio) {
					return TestConexao.retornar().fabricarMedicamento().get(convenio);
				}

				public void update(Medicamento editado) {
					TestConexao.retornar().fabricarMedicamento().up(editado);
				}

				public void remove(int id) {
					TestConexao.retornar().fabricarMedicamento().remove(id);
				}

				public ArrayList<Medicamento> getLista(){
					return TestConexao.retornar().fabricarMedicamento().getlista();
				}

				public int getTamanho() {
					return TestConexao.retornar().fabricarMedicamento().getTamanho();
				}
				public int getId() {
					return TestConexao.retornar().fabricarMedicamento().getId();
				}
				
}
