package CONTROL;

import java.util.ArrayList;
import DAOSQL.AgendaProfSql;
import MODEL.AgendaProf;
import MODEL.Dia;
import MODEL.Conexao.CXAgendaProf;


public class CTAgendaProf {
	private static AgendaProf agenda = new AgendaProf();
	private static AgendaProfSql con = new AgendaProfSql();
	private static CXAgendaProf cox = new CXAgendaProf();
	
	public static String[] getListaNomes(){
		return con.preencherListaNomes();
	}
	
	public static AgendaProf getAgenda(String nome) {
		return agenda=con.get(nome);
	}
	public static ArrayList<Dia> getHorario(String nome){
		ArrayList<Dia> horario = new ArrayList<>();  
		agenda=con.get(nome);
		horario.add(agenda.getDomingo());
		horario.add(agenda.getSegunda());
		horario.add(agenda.getTerca());
		horario.add(agenda.getQuarta());
		horario.add(agenda.getQuinta());
		horario.add(agenda.getSexta());
		horario.add(agenda.getSabado());
		
		return horario;
	}
	
	public static void insert(AgendaProf novo) {
		cox.set(agenda);
	}
	
	public static void update(AgendaProf update) {
		cox.update(agenda);
	}

	public static void remove(int id) {
		cox.remove(id);
	}

	public static ArrayList<AgendaProf> getLista(){
		return cox.getLista();
	}

	public static int getTamanho() {
		return cox.getTamanho();
	}
	public static int getId() {
		return con.getId();
	}


}
