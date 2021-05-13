package CONTROL;

import java.sql.SQLException;
import java.util.ArrayList;
import DAOSQL.MedProcedSql;
import DAOSQL.ProfissionalSql;
import MODEL.Profissional;
import MODEL.Conexao.CXProfissional;

public class CTProfissional {
	
	private static CXProfissional con= new CXProfissional();
	private static ProfissionalSql cox = new ProfissionalSql();
	private static MedProcedSql conMedProcedSql = new MedProcedSql();
	
	public static String[] getListaNomes(){
		return cox.preencherListaNomes();
	}
	
	
	

	public static void insert(Profissional novo) throws SQLException {
		con.set(novo);
	}
	public static  Profissional getMedico(String matricula) {
		return con.getMatMedico(matricula);
	}

	public static  Profissional get(String nome) {
		return con.get(nome);
	}

	public static void update(Profissional editado) {
		con.update(editado);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static  ArrayList<Profissional> getLista(){
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static String [] getProced(String matMedico){
		ArrayList<String> l =conMedProcedSql.getLista(matMedico);
		String [] lista = new String[l.size()+1];
	    lista[0]="-"; 
		for(int i =0; i<l.size();i++) {
			lista[i+1]=l.get(i);
		}

		return lista;
		
	}
	public static int getId() {
		return con.getId();
	}
	
	public static void preencherLista() {
		cox.preencherLista();
	}
	public static void addProcedimento(String matMedico,int idProced) {
		conMedProcedSql.insert(matMedico, idProced);
		
	}
	public static void removerProced(String matMedico,int idProced) {
		conMedProcedSql.remove(matMedico, idProced);
	}
}
