package CONTROL;

import MODEL.Emitente;
import MODEL.Conexao.CXEmitente;

public class CTEmitente {

	private static CXEmitente cox = new CXEmitente();

	public static void insert(Emitente novo) {
		cox.set(novo);
	}

	public static void update(Emitente update) {
		/*
		 * cox.setCNPJ(cnpj); cox.setInscEst(insEst); cox.setInscMunic(insMunic);
		 * cox.setRazao(razao); cox.setNomeFatasia(nomeFantasia); cox.setCep(cep);
		 * cox.setRua(rua); cox.setNum(num); cox.setBairro(bairro);
		 * cox.setCidade(cidade); cox.setUF(UF); cox.setCodIbge(codIbge);
		 * cox.setFone(fone); cox.setFax(fax); cox.setEmail(email);
		 */
		cox.update(update);
	}

	public static Emitente getEmitente() {
		return cox.getEmitente();
	}

}
