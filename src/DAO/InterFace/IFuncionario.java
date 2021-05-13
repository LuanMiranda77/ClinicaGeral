package DAO.InterFace;

import java.util.ArrayList;

import MODEL.Funcionario;

public interface IFuncionario {
	
public void set(Funcionario novo);
	
	public  Funcionario get(int id);
	
	public  Funcionario get(String nome);
	
	public void update(Funcionario editado);
	
	public void remove(int id);
	
	public ArrayList<Funcionario> getLista();
	
	public int getTamanho();
	
	public int getId();

}
