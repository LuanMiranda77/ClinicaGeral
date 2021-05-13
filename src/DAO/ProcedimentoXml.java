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
import DAO.InterFace.IProcedimento;
import MODEL.Procedimento;

public class ProcedimentoXml implements IProcedimento {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Procedimento> lista = new ArrayList<>();

	public ProcedimentoXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<Procedimento> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/Procedimento.xml");
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
	public ArrayList<Procedimento> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/Procedimento.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Procedimento>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Procedimento>();
	}

	public void set(Procedimento novo) {
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void up(Procedimento editado) {
		int cont=0;
		for(Procedimento p: lista) {
			if(p.getId()==editado.getId()) {
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

	public Procedimento get(String nome) {
		Procedimento cliente = null;

		for (Procedimento c : lista) {
			if (c.getDesc().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public Procedimento get(int id) {
		Procedimento cliente = null;

		for (Procedimento c : lista) {
			if (c.getId() == id)
				cliente = c;
		}

		return cliente;
	}

	@Override
	public ArrayList<Procedimento> getlista() {
		ArrayList<Procedimento> lista1 = new ArrayList<>();

		for (Procedimento c : lista) {
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

	@Override
	public void update(Procedimento novo) {
		// TODO Auto-generated method stub
		
	}

}
