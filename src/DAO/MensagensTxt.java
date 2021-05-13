package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import CONTROL.ControlCentral;
import VIEW.TelaErroLog;

public class MensagensTxt {
	
	@SuppressWarnings("unused")
	private String lista = new String();
	public MensagensTxt() {
		lista = readArquivo();
	}

	public void writeArquivo(String c) {

		try {

			FileWriter arquivo = new FileWriter(ControlCentral.getLocal()+"/mensagem.txt");
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.println(c);
			gravar.close();
		} catch (IOException e) {
			new TelaErroLog(e.getMessage(), "Erro de gravar o arquivo", "Precistencia txt");
		}
	}

	/**
	 * Ela retorna um objeto do tipo central recebe uma String com o nome do arquivo
	 * Se o arquivo já esxistir ele tranforma o xml em objeto E se não existir ele
	 * cria uma nova central
	 */
	public String readArquivo() {
		String lista = new String();
		 try {
		      FileReader arq = new FileReader(ControlCentral.getLocal()+"/mensagem.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		      while (linha != null) {
		        lista+=linha+"\n";
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		      }
		      arq.close();
		      return lista;
		    } catch (IOException e) {
		    	new TelaErroLog(e.getMessage(), "Erro de leitura do arquivo", "Precistencia txt");
		       lista="erro";
		    }
		return new String();
	}
	
	public void limparArquivo() {
		String c = "";
		writeArquivo(c);
	}

}
