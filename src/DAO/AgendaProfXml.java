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
import DAO.InterFace.IAgendaProf;
import MODEL.AgendaProf;

public class AgendaProfXml implements IAgendaProf {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<AgendaProf> lista = new ArrayList<>();

	public AgendaProfXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<AgendaProf> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/agendaProf.xml");
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
	public ArrayList<AgendaProf> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/agendaProf.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<AgendaProf>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<AgendaProf>();
	}

	public void set(AgendaProf novo) {
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(AgendaProf editado) {
		int cont = 0;
		for (AgendaProf p : lista) {
			if (p == editado) {
				lista.set(cont, editado);
			}
			cont++;
		}
		salvarCentral(lista);
	}

	public void remove(int id) {
		lista.remove(id - 1);
		salvarCentral(lista);
	}

	public AgendaProf get(String nome) {
		AgendaProf cliente = null;

		for (AgendaProf c : lista) {
			if (c.getNome().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public AgendaProf get(int id) {
		AgendaProf cliente = null;

		for (AgendaProf c : lista) {
			if (c.getId() == id)
				cliente = c;
		}

		return cliente;
	}

	@Override
	public ArrayList<AgendaProf> getLista() {
		ArrayList<AgendaProf> lista1 = new ArrayList<>();

		for (AgendaProf c : lista) {
				lista1.add(c);
		}

		return lista1;
	}

	@Override
	public int getTamanho() {
		return lista.size();
	}
	public int getId() {
		if(getTamanho()>0) {
			return lista.get(getTamanho()-1).getId()+1;
		}
		else
			return 1;
		}

}
