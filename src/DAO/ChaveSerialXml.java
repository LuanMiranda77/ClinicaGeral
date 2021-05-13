package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import CONTROL.CTEmitente;
import MODEL.ChaveSerial;

public class ChaveSerialXml {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ChaveSerial lista = new ChaveSerial();
	private String caminho ="C:\\Users\\"+System.getProperty("user.name")+"\\Google Drive\\KeySerial\\"+CTEmitente.getEmitente().getRazao()+".xml";

	public ChaveSerialXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ChaveSerial lista) {

		try {
			String xml = xstream.toXML(lista);

			File arquivo = new File(caminho);
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
	public ChaveSerial recuperarCentral() {
		File arquivo = new File(caminho);

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ChaveSerial) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ChaveSerial();
	}

	public void insert(ChaveSerial novo) {
		salvarCentral(novo);
	}


	public ChaveSerial getChave() {
		return lista;
	}


}
