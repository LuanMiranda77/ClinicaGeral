package CONTROL;

import java.util.ArrayList;
import DAOSQL.CepSql;
import MODEL.CEP;


public class CTCEP {
	
	private static CEP cep= new CEP();
	private static CepSql cx= new CepSql();
	
	
	public static void insert(String cod, String rua, String bairro , String cidade, String uf) {
		cep.setCod(cod);
		cep.setRua(rua);
		cep.setBairro(bairro);
		cep.setCidade(cidade);;
		cep.setUF(uf);
		cx.insert(cep);
	}
	public static CEP getCod(String id) {
		return cx.getCod(id);
	}

	public static CEP getRua(String nome) {
		return cx.getNome(nome);
	}

	public static void update(String cod, String rua, String bairro , String cidade, String uf) {
		
		cep.setCod(cod);
		cep.setRua(rua);
		cep.setBairro(bairro);
		cep.setCidade(cidade);;
		cep.setUF(uf);
		cx.update(cep);
	}

	public static void remove(int id) {
		cx.remove(id);
	}

	public static ArrayList<CEP> getLista(){
		return cx.getLista();
	}
	
}
