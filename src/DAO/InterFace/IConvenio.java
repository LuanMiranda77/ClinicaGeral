package DAO.InterFace;

import java.util.ArrayList;

import MODEL.Convenio;

public interface IConvenio {
	
public void set(Convenio novo);
	
	public  Convenio get(int id);
	
	public  Convenio get(String nome);
	
	public void update(Convenio id);
	
	public void remove(int id);
	
	public ArrayList<Convenio> getLista();
	
	public int getTamanho();
	
	public int getId();

}
