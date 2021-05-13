package MODEL.Conexao;
import DAO.ConfigXml;
import MODEL.Config ;

public class CXConfig {
	
	private ConfigXml  cox = new ConfigXml();
	///metods do grud 
	public void insert(Config  novo) {
		cox.insert(novo);
	}
	
	public Config  getConfig () {
		return cox.getConfig();
	}

	public void update(Config  editado) {
		cox.insert(editado);
	}

}
