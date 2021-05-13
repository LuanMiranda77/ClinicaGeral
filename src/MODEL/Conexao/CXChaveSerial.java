package MODEL.Conexao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import CONTROL.CTEmitente;
import DAO.ChaveSerialXml;
import MODEL.ChaveSerial;

public class CXChaveSerial {
	
	private static ChaveSerialXml cox = new ChaveSerialXml();
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public boolean testChave() {
		if(cox.getChave().getDataChave().before(new Date())) {
			return true;
		}
		else {
			return false;
		}

	}

	public void criarChave() {
		Calendar calendar = Calendar.getInstance();
		// diminnuindo dois anos
		calendar.add(Calendar.MONTH, 2);
		Date fim = new Date(calendar.getTime().getTime());
		ChaveSerial novo = new ChaveSerial();
		novo.setChave(CTEmitente.getEmitente().getCNPJ()+"-"+formato.format(fim));
		novo.setDataChave(fim);
		cox.insert(novo);
	}
	public static String getSerial() {
		return cox.getChave().getChave();
	}

}
