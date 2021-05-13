package DAO;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTConfig;
import CONTROL.CTPaciente;
import CONTROL.ControlCentral;
import MODEL.Paciente;
import VIEW.TelaErroLog;
import VIEW.TelaLoanding;

public class ContatosTxt {

	/**
	 * O metodo salvar Central recebe um objeto do tipo "Central" e uma String como
	 * parametros. O try catch converte a Central em Xml, abre um novo arquivo e
	 * escreve os dados da central nele
	 */
	private String lista = new String();
	private SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	private MensagensTxt ms = new MensagensTxt();

	public ContatosTxt() {
		lista = readArquivo();
	}

	private void writeArquivo(String c) {

		try {

			FileWriter arquivo = new FileWriter(ControlCentral.getLocal() + "/contatos.txt");
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
	private String readArquivo() {
		String lista = new String();
		try {
			FileReader arq = new FileReader(ControlCentral.getLocal() + "/contatos.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto
			while (linha != null) {
				lista += linha + "\n";
				linha = lerArq.readLine(); // lê da segunda até a última linha
			}
			arq.close();
			return lista;
		} catch (IOException e) {
			new TelaErroLog(e.getMessage(), "Erro de leitura do arquivo", "Precistencia txt");
			lista = "erro";
		}
		return new String();
		
	}

	public void gravarArquivo(String ms) {
		lista = ms;
	}

	public void limparArquivo() {
		String c = "";
		writeArquivo(c);
	}

	public int getSize() {
		return lista.length();
	}

	

	public void TestAgendaMarcDia() {
		//este teste para verificar se o envio esta ativo
		if(CTConfig.getConfig().getUsar_Envio_automaico_zap().equals("S")) {
			
		
		MensagensTxt ms = new MensagensTxt();
		String mens = CTConfig.getConfig().getModelo_mesagem_zap();
		String[] m = mens.split(";");
		String cel = "";
		mens = "";
		String ent = "\n";
		ResultSet res = null;
		try {
			res = CTAgendaConsulta.testMensagemAutoZap();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int tam = CTAgendaConsulta.tamanhoListaZap();
			if (tam>0) {
				
				int op = JOptionPane.showConfirmDialog(null,
						"Existem Consulta Agendadas para hoje,\n" + "Quer enviar mensagem automaticas aos Pacientes ?",
						"Tela de confirmação", JOptionPane.YES_NO_OPTION);
				if (op == 0) {
					int op1 = JOptionPane.showConfirmDialog(null,
							"Verifique se o Whatsapp esta aberto e conectado ao seu Celular?", "Tela de confirmação",
							JOptionPane.YES_NO_OPTION);

					if (op1 == 0) {

						int cont = 0;
						new TelaLoanding();
						while (res.next()) {
							if (cont == tam) {
								ent = "";
							}
							// pegando o celular do cliente
							cel +=res.getString("celular") + ent;

							// montando a mensagem via zap

							mens += "*" + m[0] + ": " + m[1] + res.getString(3) + "*" + " " + m[2] + ": "
									+ "*" + formato.format(res.getDate(1)) + "*" + " " + m[3] + ": " + "*"+ res.getString(2)+"*"
									+ " com o Dr." + res.getString(5) + " para realizar " + res.getString(6) + " " + m[4] + ", " + m[5]
									+ " " + ent;
							cont++;
							
						
							}
						writeArquivo(cel);
						ms.writeArquivo(mens);
							/*
							 * Desktop d = Desktop.getDesktop(); try { d.browse(new
							 * URI("https://web.whatsapp.com/")); } catch (URISyntaxException e) { new
							 * TelaErroLog(e.getMessage(), "erro zap",""); }
							 */
					    Runtime.getRuntime().exec("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\WhatsApp\\WhatsApp.exe");
						Runtime.getRuntime().exec("C:\\System Clinica\\execZap.bat");
					

					}
				}
			}
			
			
		} catch (SQLException | IOException e) {
			new TelaErroLog(e.getMessage(), "Metodo", "enviarMensagemZap");
		}
		}
		

	}

	public void enviarMensagemZap(String cliente, String titulo, String mensagem) {
		try {
			Paciente paciente = CTPaciente.get(cliente);
			limparArquivo();
			ms.limparArquivo();
			writeArquivo(paciente.getCelular1());
			ms.writeArquivo("*" + titulo + "*" + " Sr(a)." + cliente + " " + mensagem);
			Runtime.getRuntime().exec("C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\WhatsApp\\WhatsApp.exe");
			Runtime.getRuntime().exec("C:\\System Clinica\\execZap.bat");
		} catch (Exception e) {
			new TelaErroLog(e.getMessage(), "Metodo", "enviarMensagemZap");
		}
	}

	public static void main(String[] args) {
		ContatosTxt c = new ContatosTxt();
		c.TestAgendaMarcDia();
	}
}
