package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import DAO.InterFace.IConvenio;
import MODEL.Convenio;

public class ConvenioXml implements IConvenio {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Convenio> lista = new ArrayList<>();

	public ConvenioXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<Convenio> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File("xml/Convenio.xml");
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
	public ArrayList<Convenio> recuperarCentral() {
		File arquivo = new File("xml/Convenio.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Convenio>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Convenio>();
	}

	public void set(Convenio novo) {
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(Convenio editado) {
		int cont = 0;
		for (Convenio p : lista) {
			if (p == editado) {
				lista.set(cont, editado);
			}
			cont++;
		}
		salvarCentral(lista);
	}

	public void remove(int id) {
		lista.remove(id);
		salvarCentral(lista);
	}

	public Convenio get(String nome) {
		Convenio cliente = null;

		for (Convenio c : lista) {
			if (c.getNome().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public Convenio get(int id) {
		Convenio cliente = null;

		for (Convenio c : lista) {
			if (c.getId() == id)
				cliente = c;
		}

		return cliente;
	}

	@Override
	public ArrayList<Convenio> getLista() {
		ArrayList<Convenio> lista1 = new ArrayList<>();

		for (Convenio c : lista) {
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
