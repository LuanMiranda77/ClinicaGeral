package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import CONTROL.ControlCentral;
import MODEL.ListaChegada;

public class ListaOrdemXml {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<ListaChegada> lista;

	public ListaOrdemXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<ListaChegada> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal() + "/ListaChegada.xml");
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ela retorna um objeto do tipo central recebe uma String com o nome do arquivo
	 * Se o arquivo já esxistir ele tranforma o xml em objeto E se não existir ele
	 * cria uma nova central
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ListaChegada> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal() + "/ListaChegada.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<ListaChegada>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<ListaChegada>();
	}

	public void insert(ListaChegada novo) {
		lista.add(novo);
		salvarCentral(lista);
	}

	public void removeLista(int row) {
		lista.remove(row);
		salvarCentral(lista);
	}
	
	public void removeConsultaFinalizada(int idconsulta) {
		int cont=0;
		lista=recuperarCentral();
		for(ListaChegada l : lista) {
		
			if(l.getIdConsulta()==idconsulta) {
				break;
			}
			cont++;
		}
		
		lista.remove(cont);
		salvarCentral(lista);
		
	}
	public int getIdOrdem() {
		int id= lista.size();
		if(id>0) {
			id = Integer.parseInt(lista.get(id-1).getNum());
		}
		return id;
	}

	public ArrayList<ListaChegada> getLista() {
		
		return lista;
	}
	
	public int getSize() {
		return lista.size();
	}
	
	public void update(int index) {
		int cont=0;
		for(ListaChegada l : lista) {
			if(l.getIdConsulta()==index) {
			lista.get(cont).setStatus("FINALIZADA");
			salvarCentral(lista);
			break;
			}
			cont++;
		}
		
		
		
		
		
	}
	
	public boolean verificarConsultaOrdem(int id) {
		for(ListaChegada l : lista) {
			if(l.getIdConsulta()==id) {
			  return true;
			}
		}
		return false;
	}

	public void testDataLista() {
		ArrayList<ListaChegada> l;
		if(lista.size()>0) {
		 l=new ArrayList<>();	
		 lista=l;
		 salvarCentral(lista);
		}

	}

}
