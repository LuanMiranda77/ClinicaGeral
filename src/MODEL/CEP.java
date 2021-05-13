package MODEL;

import java.util.ArrayList;

import DAOSQL.TestConexao;

public class CEP {
	
	String cod;
	String rua;
	String bairro;
	String cidade;
	String UF;
	
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uf) {
		UF = uf;
	}
	
	//metods
			public void set(CEP novo){
				TestConexao.retornar().fabricarCEP().insert(novo);
			}
			public CEP getCod(String id) {
				return TestConexao.retornar().fabricarCEP().getCod(id);
			}

			public CEP getRua(String nome) {
				return TestConexao.retornar().fabricarCEP().getNome(nome);
			}

			public void update(CEP editado) {
				TestConexao.retornar().fabricarCEP().update(editado);
			}

			public void remove(int id) {
				TestConexao.retornar().fabricarCEP().remove(id);
			}

			public ArrayList<CEP> getLista(){
				return TestConexao.retornar().fabricarCEP().getLista();
			}

}
