package DAO.InterFace;

import java.util.ArrayList;

import MODEL.Especialidade;

public interface IEspecialidade {
	
public void set(Especialidade novo);
	
	public  Especialidade get(int id);
	
	public  Especialidade get(String nome);
	
	public void update(Especialidade id);
	
	public void remove(int id);
	
	public ArrayList<Especialidade> getLista();
	
	public int getTamanho();
	

}
