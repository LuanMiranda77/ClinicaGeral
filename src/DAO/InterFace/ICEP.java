package DAO.InterFace;

import java.util.ArrayList;

import MODEL.CEP;

public interface ICEP {
	
public void insert(CEP novo);
	
	public  CEP getCod(String id);
	
	public  CEP getNome(String nome);
	
	public void update(CEP ag);
	
	public void remove(int id);
	
	public ArrayList<CEP> getLista();

}
