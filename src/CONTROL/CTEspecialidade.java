package CONTROL;

import java.util.ArrayList;
import DAOSQL.EspecialidadeSql;
import MODEL.Especialidade;
import MODEL.Conexao.CXEspecialidade;

public class CTEspecialidade {
	
	private static CXEspecialidade con= new CXEspecialidade();
	private static EspecialidadeSql cox = new EspecialidadeSql();
	
	public static void insert(Especialidade novo) {
		con.set(novo);
	}
	
	
	public static String[] getListaNomes(){
		return cox.preencherListaNomes();
	}
	
	public static Especialidade get(int id) {
		return con.get(id);
	}

	public static Especialidade get(String nome) {
		return con.get(nome);
	}

	public static void update(Especialidade update) {
		con.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static ArrayList<Especialidade> getLista(){
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static void preencherLista() {
		cox.preencherLista();
	}
	public static int getId() {
		return cox.getId();
	}
	public static Especialidade getCbo(String cbo) {
		return cox.getCbo(cbo);
	}
	


}
