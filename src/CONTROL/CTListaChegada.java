package CONTROL;

import DAO.ListaOrdemXml;

public class CTListaChegada {
	
	private static ListaOrdemXml lista;
	
	public static int getSise() {
		lista= new ListaOrdemXml();
		return lista.getSize();
	}

}
