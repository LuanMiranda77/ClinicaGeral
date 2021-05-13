package DAOSQL;
import java.sql.Connection;

import DAO.BDarquivo;
import DAO.InterFace.FabFactory;


public class TestConexao {
private static FabFactory criar;
	
	public static  FabFactory retornar() {
	
	Connection con = null;
		con = ConexaoSingleton.getInstance();
    
    if(con==null) {
    	criar = new BDarquivo();
    }
    else if(criar==null) {
    	
    	criar = new BDrelacional();
    	
	}
	return criar;
	}
	
	//test de finalizar conexão
	public static void main(String[] args) {
	for(int cont=0;cont<20;cont++) {	
		ConexaoSingleton.sairConexao();;
	}
	}
}
