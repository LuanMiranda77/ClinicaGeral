package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import MODEL.Config;

public class ConfigXml {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private Config lista=new Config();
	 
		public ConfigXml() {
			lista = recuperarCentral();
		}


		public void salvarCentral(Config c) {
			

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File("config/config.xml");
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		/**
		 * Ela retorna um objeto do tipo central
		 * recebe uma String com o nome do arquivo
		 * Se o arquivo já esxistir ele tranforma o xml em objeto
		 * E se não existir ele cria uma nova central
		 */
	public Config recuperarCentral() {
		File arquivo = new File("config/config.xml");

			try {
				if (arquivo.exists()) {
					FileInputStream arq = new FileInputStream(arquivo);
					return (Config) xstream.fromXML(arq);
				}
			} catch (FileNotFoundException a) {
				a.printStackTrace();
			}
		return new Config();
	}

		public void insert(Config  novo) {
		      lista=novo;
		      salvarCentral(lista);
		}

		public Config  getConfig() {
			return recuperarCentral();
		}
		
}
