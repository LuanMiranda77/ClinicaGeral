package DAO.InterFace;

import java.util.ArrayList;

import MODEL.Paciente;

public interface IPaciente {
	
	public void set(Paciente novo)throws Exception;
	
	public  Paciente get(int id);
	
	public  Paciente get(String nome);
	
	public void update(Paciente editado);
	
	public void remove(int id);
	
	public ArrayList<Paciente> getLista();
	
	public int getTamanho();
	
	public int getId();

}
