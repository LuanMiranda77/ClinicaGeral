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
import DAO.InterFace.ICEP;
import MODEL.CEP;


public class CEPXml implements ICEP {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<CEP> lista = new ArrayList<>();

	public CEPXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<CEP> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/CEP.xml");
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
	public ArrayList<CEP> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/CEP.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<CEP>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<CEP>();
	}

	public void insert(CEP novo) {
		boolean band=false;
		for(CEP c : lista) {
			if(c.getCod().equals(novo.getCod())) {
				band=true;
			}
		}
		if(band==false) {
		lista.add(novo);
		salvarCentral(lista);
		}
	}

	public void update(CEP editado) {
		int cont = 0;
		for (CEP p : lista) {
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

	public CEP getNome(String nome) {
		CEP cliente = null;

		for (CEP c : lista) {
			if (c.getRua().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public CEP getCod(String id){
		CEP cliente = null;

		for (CEP c : lista) {
			if (c.getCod().equals(id))
				cliente = c;
		}
			return cliente;
	}

	@Override
	public ArrayList<CEP> getLista() {
		ArrayList<CEP> lista1 = new ArrayList<>();

		for (CEP c : lista) {
				lista1.add(c);
		}

		return lista1;
	}

}
