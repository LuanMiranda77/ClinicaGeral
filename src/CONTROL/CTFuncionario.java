package CONTROL;

import java.util.ArrayList;
import java.util.Arrays;

import DAOSQL.FunSql;
import MODEL.Funcionario;
import MODEL.Conexao.CXFuncionario;

public class CTFuncionario {
	
	private static CXFuncionario con= new CXFuncionario();
	private static FunSql cox = new FunSql();
	
	public static String[] getListaNomes(){
		String [] nomes=new String[con.getTamanho()+1];
		nomes[0]="-";
		int cont=1;
		for(Funcionario i:con.getLista()) {
			if(i.getCargo().equals("MEDICO")) {
			nomes[cont]=i.getNome();
			}
			cont++;
			
		}
		Arrays.sort(nomes);
		return nomes;
	}
	
	public static void insert(Funcionario novo) {
		con.set(novo);
	}
	public static Funcionario get(int id) {
		return con.get(id);
	}

	public static Funcionario get(String nome) {
		return con.get(nome);
	}

	public static void update(Funcionario update) {
		con.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static ArrayList<Funcionario> getLista(){
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static int getId() {
		return con.getId();
	}
	public static void preencherLista() {
		cox.preencherLista();
	}


}
