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
import DAO.InterFace.IProfissional;
import MODEL.Profissional;

public class ProfissionalXml implements IProfissional {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Profissional> lista = new ArrayList<>();

	public ProfissionalXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<Profissional> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/Profissional.xml");
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
	public ArrayList<Profissional> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/Profissional.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Profissional>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Profissional>();
	}

	public void set(Profissional novo) {
		int id = getId();
		novo.setId(id);
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(Profissional editado) {
		int cont=0;
		for(Profissional p: lista) {
			if(p.getMatricula().equals(editado.getMatricula())) {
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

	public Profissional get(String nome) {
		Profissional cliente = null;

		for (Profissional c : lista) {
			if (c.getNome().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public Profissional getMedico(String matricula) {
		Profissional cliente = null;

		for (Profissional c : lista) {
			if (c.getMatricula().equals(matricula))
				cliente = c;
		}

		return cliente;
	}

	@Override
	public ArrayList<Profissional> getLista() {
		ArrayList<Profissional> lista1 = new ArrayList<>();

		for (Profissional c : lista) {
				lista1.add(c);
		}

		return lista1;
	}

	@Override
	public int getTamanho() {
		return lista.size();
	}
	public ArrayList<String> getProced(String nome){
		ArrayList<String>  procedlist = new ArrayList<>();
		
		for(Profissional p:lista) {
			if(p.getNome().equals(nome)) {
				procedlist=p.getProcedimento();
			}
			
		}
		return procedlist;
	}
	
	public int getId() {
		if(getTamanho()>0) {
			return lista.get(getTamanho()-1).getId()+1;
		}
		else
			return 1;
		}


}
