package CONTROL;

import java.io.File;
import java.io.IOException;
import DAO.Bacukp;
import DAO.CentralXml;
import MODEL.Central;
import MODEL.Funcionario;

public class ControlCentral {
	
	private static CentralXml cox = new CentralXml();
	
	public static void setLogado(Funcionario novo) {
		Central.setLogado(novo);
	}
	public static Funcionario getLogado() {
		return Central.getLogado();
		
	}
	public static void setLocal(String local) {
		Central.setLocal(local);
	}
	public static String getLocal() {
		return Central.getLocal();
	}
	public static void geraBackup() throws IOException {
		Bacukp.realizaBackup();
		
	}
	public static void mesagensa() throws IOException {
		Runtime.getRuntime().exec("C:\\System Clinica\\execZap.bat");
	}

	
	
	
	
	public static void moverArquivo(String arq) {
		 
        // Arquivo a ser movido
        File arquivo = new File(arq);
 
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado");
        } else {
 
            // Diretorio de destino
            File diretorioDestino = new File(cox.getBkpExterno());
 
            // Move o arquivo para o novo diretorio
            boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
            if (sucesso) {
                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
            } else {
                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
                        + diretorioDestino.getAbsolutePath() + "'");
            }
        }
    }

}