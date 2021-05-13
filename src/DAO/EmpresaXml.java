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
import MODEL.Emitente;

public class EmpresaXml  {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Emitente> lista = new ArrayList<>();

	public EmpresaXml() {
		lista = recuperarCentral();
	}

	private void salvarCentral(ArrayList<Emitente> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/Emitente.xml");
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
	private ArrayList<Emitente> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/Emitente.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Emitente>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Emitente>();
	}

	public void set(Emitente novo) {
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(Emitente editado) {
		lista.set(0,editado);
		salvarCentral(lista);
	}
	public Emitente get() {
		if(lista.size()>0) {
		return lista.get(0);
		}
		else {
			return null;
		}
	}



}
