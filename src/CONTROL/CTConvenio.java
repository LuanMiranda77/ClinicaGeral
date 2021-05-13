package CONTROL;

import java.util.ArrayList;
import DAOSQL.ConvenioSql;
import MODEL.Convenio;
import MODEL.Conexao.CXConvenio;

public class CTConvenio {
	
	private static CXConvenio con= new CXConvenio();
	private static ConvenioSql cox = new ConvenioSql();
	
	public static String[] getListaNomes(){
		return cox.preencherListaNomes();
	}
	
	public static void insert(Convenio novo) {
		con.set(novo);
	}
	public static Convenio getConvId(int matricula) {
		return con.get(matricula);
	}

	public static Convenio get(String nome) {
		return con.get(nome);
	}

	public static void update(Convenio update) {
		con.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static ArrayList<Convenio> getLista(){
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
