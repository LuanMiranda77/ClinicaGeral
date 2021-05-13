package CONTROL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAOSQL.PacienteSql;
import MODEL.Paciente;
import MODEL.Conexao.CXPaciente;

public class CTPaciente {
	private static CXPaciente con = new CXPaciente();
	private static PacienteSql bd = new PacienteSql();
	
	public static String[] getListaNomes(){
		return bd.preencherListaNomes();
	}
	public static String[] getListaNomesEmail(){
		return bd.ListaNomesEmail();
	}
	public static String getConv(String nome){
		Paciente p= get(nome);
		return p.getConvenio();
	}
	

	public static void insert(Paciente novo) throws Exception {
		con.set(novo);
	}

	public static void update(Paciente update) {
		con.update(update);
	}

	public static Paciente get(int id) {
		return con.get(id);
	}

	public static Paciente get(String nome) {
		return con.get(nome);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static ArrayList<Paciente> getLista() {
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static int getId() {
		return con.getId();
	}
	public static void preencherLista() {
		con.preencherLista();
	}
	public static Paciente getPacienteCPF(String cpf) {
		return bd.getPacienteCPF(cpf);
	}
	public static Paciente getPacienteNomeLike(String nome) {
		return bd.getPacienteNomeLike(nome);
	}
	
	public static String[] getListaMensagemZap() {
		return bd.ListaNomesZap();
	}
	public static ResultSet getListaPesquisaCli() throws SQLException {
		return bd.getListaPesquisaCliente();
	}

}
