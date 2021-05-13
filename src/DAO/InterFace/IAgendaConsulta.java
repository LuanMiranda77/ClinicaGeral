package DAO.InterFace;

import java.util.ArrayList;

import MODEL.AgendaConsulta;

public interface IAgendaConsulta {

	public void set(AgendaConsulta novo);

	public AgendaConsulta get(int id);

	public AgendaConsulta get(String nome);

	public void update(AgendaConsulta id);

	public void remove(int id);

	public ArrayList<AgendaConsulta> getLista();

	public int getTamanho();
	
	public int getId();


}
