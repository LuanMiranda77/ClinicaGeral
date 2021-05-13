package MODEL.Conexao;
import DAO.EmpresaXml;
import MODEL.Emitente;

public class CXEmitente {
	private EmpresaXml emitente = new EmpresaXml();
	///metods do grud 
	///metods do grud 
	public void set(Emitente novo) {
		emitente.set(novo);
	}
	
	public Emitente getEmitente() {
		return emitente.get();
	}

	public void update(Emitente editado) {
		emitente.update(editado);
	}

}
