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
import DAO.InterFace.IAgendaConsulta;
import MODEL.AgendaConsulta;


public class AgendaConsultaXml implements IAgendaConsulta{
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<AgendaConsulta> lista = new ArrayList<>();

	public AgendaConsultaXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<AgendaConsulta> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/AgendaConsulta.xml");
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
	public ArrayList<AgendaConsulta> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/AgendaConsulta.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<AgendaConsulta>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<AgendaConsulta>();
	}

	public void set(AgendaConsulta novo) {
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(AgendaConsulta editado) {
		int cont=0;
		for(AgendaConsulta p: lista) {
			if(p.getId()==editado.getId()) {
				    lista.set(cont, editado);
			}
			cont++;
		}
		salvarCentral(lista);
	}

	public void remove(int id) {
		int row=0;
		for(AgendaConsulta ag : lista) {
			if(ag.getId()==id) {
				lista.remove(row);
				break;
			}
			row++;
		}
		
		salvarCentral(lista);
	}


	public AgendaConsulta get(int id) {
		AgendaConsulta cliente = null;

		for (AgendaConsulta c : lista) {
			if (c.getId() == id)
				cliente = c;
		}

		return cliente;
	}

	public ArrayList<AgendaConsulta> getLista() {
		ArrayList<AgendaConsulta> lista1 = new ArrayList<>();

		for (AgendaConsulta c : lista) {
				lista1.add(c);
		}

		return lista1;
	}

	public int getTamanho() {
		return lista.size();
	}

	@Override
	public AgendaConsulta get(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getId() {
		if(getTamanho()>0) {
			return lista.get(getTamanho()-1).getId()+1;
		}
		else
			return 1;
		}


}
