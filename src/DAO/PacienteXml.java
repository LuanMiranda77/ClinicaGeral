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
import DAO.InterFace.IPaciente;
import MODEL.Paciente;

public class PacienteXml implements IPaciente {
	/**
	 * Primeiro a classe cria um arquivo em Xml
	 */

	private XStream xstream = new XStream(new DomDriver("ISO-8859-1"));

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private ArrayList<Paciente> lista = new ArrayList<>();

	public PacienteXml() {
		lista = recuperarCentral();
	}

	public void salvarCentral(ArrayList<Paciente> c) {

		try {
			String xml = xstream.toXML(c);

			File arquivo = new File(ControlCentral.getLocal()+"/Paciente.xml");
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
	public ArrayList<Paciente> recuperarCentral() {
		File arquivo = new File(ControlCentral.getLocal()+"/Paciente.xml");

		try {
			if (arquivo.exists()) {
				FileInputStream arq = new FileInputStream(arquivo);
				return ((ArrayList<Paciente>) xstream.fromXML(arq));
			}
		} catch (FileNotFoundException a) {
			a.printStackTrace();
		}
		return new ArrayList<Paciente>();
	}
//metodos de salvar cliente
	public void set(Paciente novo) throws Exception {
		for(Paciente p:lista) {
			if(p.getCPF().equals(novo.getCPF())) {
				throw new  Exception("Paciente já esta cadastrado");
			}
		}
		novo.setId(getId());
		lista.add(novo);
		salvarCentral(lista);
	}

	public void update(Paciente editado) {
		int cont=0;
		for(Paciente p: lista) {
			if(p.getId()==editado.getId()) {
				    lista.set(cont, editado);
			}
			cont++;
		}
		salvarCentral(lista);
	}
	@Override
	public void remove(int id){
		lista.remove(id);
		salvarCentral(lista);
	}

	public Paciente get(String nome) {
		Paciente cliente = null;

		for (Paciente c : lista) {
			if (c.getNome().equals(nome))
				cliente = c;
		}
		return cliente;
	}

	public Paciente get(int id) {
		Paciente cliente = null;

		for (Paciente c : lista) {
			if (c.getId() == id)
				cliente = c;
		}

		return cliente;
	}

	@Override
	public ArrayList<Paciente> getLista() {
		ArrayList<Paciente> lista1 = new ArrayList<>();

		for (Paciente c : lista) {
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
