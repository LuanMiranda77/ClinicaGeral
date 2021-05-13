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
import DAO.InterFace.IFuncionario;
import MODEL.Funcionario;


public class FunXml implements IFuncionario {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Funcionario> lista = new ArrayList<>();

	public FunXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<Funcionario> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/func.xml");
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
	public ArrayList<Funcionario> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/func.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Funcionario>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Funcionario>();
	}

	public void set(Funcionario novo) {
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(Funcionario editado) {
		int cont = 0;
		for (Funcionario p : lista) {
			if (p.getId() == editado.getId()) {
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

	public Funcionario get(String nome) {
		Funcionario cliente = null;

		for (Funcionario c : lista) {
			if (c.getNome().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public Funcionario get(int id){
		Funcionario cliente = null;

		for (Funcionario c : lista) {
			if (c.getId() == id)
				cliente = c;
		}
			return cliente;
	}

	@Override
	public ArrayList<Funcionario> getLista() {
		ArrayList<Funcionario> lista1 = new ArrayList<>();

		for (Funcionario c : lista) {
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
