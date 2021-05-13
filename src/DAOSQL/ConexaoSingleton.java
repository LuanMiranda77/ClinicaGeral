package DAOSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import CONTROL.CTConfig;
import VIEW.TelaErroLog;


public class ConexaoSingleton {
    
	private final static String URL =  CTConfig.getConfig().getURL();//localhost:5433/bdClinica; // incica o caminho do banco de dados
	private final static String USER = CTConfig.getConfig().getUSER();//"postgres"; // aqui vai o nome usuario que vc quer acessar
	private final static String PASS = CTConfig.getConfig().getPASS();//"ads54321"; // aqui a senha do seu banco
	private static Connection conexao;
	private volatile static Connection instance;
	
	private ConexaoSingleton() {
	
	}
	
	public  static Connection getInstance() {
		if( instance == null ){
			synchronized (ConexaoSingleton.class){
		if( instance == null ) {
		   instance = new ConexaoSingleton().criarConcexao();
			}
		}
	}
		return instance;
		}
	
	
	private Connection criarConcexao() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("conectado");
			return conexao= DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e) {
			new TelaErroLog(e.getMessage(), "Erro Singiton", "Concexao");
			throw new RuntimeException(e.getMessage());
			
		}
	}

	
	public static void sairConexao(){
	
		try {
			   conexao.close(); 
			   conexao=null;
		   System.out.println("Finalizado...");
		} catch (SQLException e) {
			new TelaErroLog(e.getMessage(), "Erro Singiton", "Finalizado...");
		} 
	}
	public static void main(String[] args) {
		for(int cont=0;cont<10;cont++) {
			ConexaoSingleton.sairConexao();
		}
	}
	

}
