package DAO.InterFace;
import java.util.ArrayList;

import MODEL.Medicamento;
public interface IMedicamento {

	public void set(Medicamento cdto);
	public void remove(int id);
	public ArrayList<Medicamento> getlista();
	public Medicamento get(int id);
	public Medicamento get(String nome);
	public void up(Medicamento editado);
	public int getTamanho();
	public int getId();
}
