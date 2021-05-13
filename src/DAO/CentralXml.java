package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import MODEL.Funcionario;

public class CentralXml {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));
    
	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como parametros.
	 * O try catch converte a Central em Xml, abre um novo arquivo e escreve os dados da central nele 
	 */
	 private ArrayList<String> lista=new ArrayList<>();
	 
		public CentralXml() {
			lista = recuperarCentral();
		}


		public void salvarCentral(ArrayList<String> c) {
			

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File("xml/central.xml");
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
	@SuppressWarnings("unchecked")
	public ArrayList<String> recuperarCentral() {
		File arquivo = new File("xml/central.xml");

			try {
				if (arquivo.exists()) {
					FileInputStream arq = new FileInputStream(arquivo);
					return ((ArrayList<String>) xstream.fromXML(arq));
				}
			} catch (FileNotFoundException a) {
				a.printStackTrace();
			}
		return new ArrayList<String>();
	}

		public void setLogado(Funcionario cdto) {
		     lista.set(0, ""+cdto.getId()); 
			 lista.set(1,cdto.getNome());
		     lista.set(2,cdto.getCargo());
		      salvarCentral(lista);
		}


		public Funcionario getLogado() {
			Funcionario novo = new Funcionario();
			novo.setId(Integer.parseInt(lista.get(0)));
			novo.setNome(lista.get(1));
			novo.setCargo(lista.get(2));
			return novo;
		}
		public String getLocal() {
			return lista.get(3);
		}
		public void setLocal(String local) {
			lista.set(3,local);
			 salvarCentral(lista);
		}
		public String getCorFundo() {
			return lista.get(3);
		}
		public void setCorfundo(String local) {
			lista.set(3,local);
			 salvarCentral(lista);
		}
		public String getBkpExterno() {
			return lista.get(4);
		}
		public void  setBkpExterno(String local) {
			lista.set(4,local);
			 salvarCentral(lista);
		}
		


}
