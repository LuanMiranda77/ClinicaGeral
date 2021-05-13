package CONTROL;

import MODEL.Config;
import MODEL.Conexao.CXConfig;

public class CTConfig {
	
	private static CXConfig  cox = new CXConfig();
	
	///metods do grud 
	public static void insert(Config  novo) {
		cox.insert(novo);
	}
	
	public static Config  getConfig () {
		return cox.getConfig();
	}

	public static void update(Config  editado) {
		cox.insert(editado);
	}


}
