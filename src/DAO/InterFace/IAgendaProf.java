package DAO.InterFace;

import java.util.ArrayList;

import MODEL.AgendaProf;

public interface IAgendaProf {
	
public void set(AgendaProf novo);
	
	public  AgendaProf get(int id);
	
	public  AgendaProf get(String nome);
	
	public void update(AgendaProf ag);
	
	public void remove(int id);
	
	public ArrayList<AgendaProf> getLista();
	
	public int getTamanho();
	
	public int getId();

}
