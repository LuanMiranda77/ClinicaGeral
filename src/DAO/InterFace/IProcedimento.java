package DAO.InterFace;
import java.util.ArrayList;

import MODEL.Procedimento;

public interface IProcedimento {

	public void set(Procedimento cdto);
	public void remove(int id);
	public ArrayList<Procedimento> getlista();
	public Procedimento get(int id);
	public Procedimento get(String nome);
	public void up(Procedimento editado);
	public int getTamanho();
	public int getId();
	void update(Procedimento novo);
}
