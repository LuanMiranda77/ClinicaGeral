package DAO.InterFace;

import java.sql.SQLException;
import java.util.ArrayList;
import MODEL.Profissional;

public interface IProfissional {
	
public void set(Profissional novo) throws SQLException;
	
	public  Profissional getMedico(String matricula);
	
	public  Profissional get(String nome);
	
	public void update(Profissional id);
	
	public void remove(int id);
	
	public ArrayList<Profissional> getLista();
	
	public int getTamanho();
	
	public ArrayList<String> getProced(String nome);
	
	public int getId();

}
