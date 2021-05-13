package CONTROL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import DAO.ListaOrdemXml;
import DAOSQL.AgendaConsultaSql;
import MODEL.AgendaConsulta;
import MODEL.ListaChegada;
import MODEL.Conexao.CXAgendaConsulta;

public class CTAgendaConsulta {
	
	private static CXAgendaConsulta con= new CXAgendaConsulta();
	private ListaOrdemXml cox = new ListaOrdemXml();
	private static SimpleDateFormat formato;
	private static AgendaConsultaSql bdconect;
	
	public  int getIdOrdem() {
		return cox.getIdOrdem();
	}
	
	
	public static void insert(AgendaConsulta novo) {
		con.set(novo);
	}
	public static AgendaConsulta get(int id) {
		return con.get(id);
	}

	public static AgendaConsulta get(String nome) {
		return con.get(nome);
	}

	public static void update(AgendaConsulta update) {
		con.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}

	public static ArrayList<AgendaConsulta> getLista(){
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	@SuppressWarnings("deprecation")
	public static String getDateAuto(Date data, int dia) {
		formato = new SimpleDateFormat("dd/MM/yyyy");
		String dt = null;
		data.setDate(dia);
		dt=formato.format(data);
		return dt;
	}
	public static String getDiaSemana(Date data){ //ex 07/03/2017
	    String dia = "---";
	    GregorianCalendar gc = new GregorianCalendar();
	    try {
	    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(formato.format(data)));
	        switch (gc.get(Calendar.DAY_OF_WEEK)) {
	            case Calendar.SUNDAY:
	                dia = "DOM";
	                break;
	            case Calendar.MONDAY:
	                dia = "SEG";
	                break;
	            case Calendar.TUESDAY:
	                dia = "TER";
	            break;
	            case Calendar.WEDNESDAY:
	                dia = "QUA";
	                break;
	            case Calendar.THURSDAY:
	                dia = "QUI";
	                break;
	            case Calendar.FRIDAY:
	                dia = "SEX";
	                break;
	            case Calendar.SATURDAY:
	                dia = "SAB";

	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return dia;
	}
	public static boolean testData(Date data, String hora, String NomeMedico) {
		boolean band=false;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		String d = formato.format(data);
		for(AgendaConsulta a:getLista()) {
			String h=formato.format(a.getData());
			if(d.equals(h) && a.getHora().equals(hora) && a.getMatMedico().equals(NomeMedico)) {
							return band=true;
			}
		}
		return band;
		
	}
	public static int getId() {
		return con.getId();
	}
	public  ArrayList<ListaChegada> getListaOrdem() {
		return cox.getLista();
	}
	public  void addListaOrdem(ListaChegada novo) {
		cox.insert(novo);
	}
	public  void removeListaOrdem(int row) {
		cox.removeLista(row);
	}
	public  void removeConsultFinalizada(int row) {
		cox.removeConsultaFinalizada(row);
	}
	public void testDataListaOrdem() {
		cox.testDataLista();
	}
	public  void  updateListaOrdem(int index) {
		cox.update(index);
	}
	public static ResultSet preencherListaPelaData(Date data,String matMedico) throws SQLException {
		bdconect = new AgendaConsultaSql();
		return bdconect.getListaDate(data, matMedico);
	}
	public static ResultSet historicoCompleto(String cpf) {
		bdconect = new AgendaConsultaSql();
		return bdconect.getHistoricoConsultas(cpf);
	}
	public static ResultSet testMensagemAutoZap() throws SQLException {
		bdconect = new AgendaConsultaSql();
		return bdconect.TestZap();
	}
	public static int tamanhoListaZap() {
		bdconect = new AgendaConsultaSql();
		return bdconect.tamanhoListaZap();
	}
	public  boolean verificarOrdem(int id) {
		return cox.verificarConsultaOrdem(id);
	}
	


}
