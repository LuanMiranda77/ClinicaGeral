package DAO;
import java.io.IOException;

import VIEW.TelaErroLog;
import VIEW.TelaLoginBkp;


/*
 * @ autor dessa luan marinate 
 */

public class Bacukp  {
	@SuppressWarnings("unused")
	public static  void realizaBackup() {
		try {
		//new TelaLoginBkp();
		ProcessBuilder pb;
		Process p;
		pb = new ProcessBuilder("C:\\postgre94\\bin\\" + "pg_dump.exe", "-i", "-h", "localhost", "-p", "5432", "-U", "postgres", "-F", "c", "-b", "-v", "-f", "C:\\System Clinica\\bkp\\" + "bkp" + ".backup");
		pb.environment().put("PGPASSWORD", "ads54321");
		pb.redirectErrorStream(true);
		p = pb.start();

		    } catch (IOException e) {
		       new TelaErroLog(e.getMessage(), "", "erro ao fazer bacukp");
		        e.printStackTrace();
		    }

		}
	public static void main(String[] args) {
		Bacukp.realizaBackup();
	}

}
