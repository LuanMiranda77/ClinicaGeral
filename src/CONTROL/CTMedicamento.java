package CONTROL;

import java.util.ArrayList;
import DAOSQL.MedicamentoSql;
import MODEL.Medicamento;
import MODEL.Conexao.CXMedicamento;

public class CTMedicamento {
	private static CXMedicamento con = new CXMedicamento();
	private static MedicamentoSql cox = new MedicamentoSql();

	public static void insert(Medicamento novo) {
		con.set(novo);
	}
	
	public static void preencherLista() {
		cox.preencherLista();
	}

	public static void update(Medicamento update) {
		con.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}
	
	public static String[] getListaNomes() {
	return cox.preencherListaNomes();
	}

	public static Medicamento getMedicamento(String nome) {
		return con.get(nome);
	}

	public static Medicamento getMedicamento(int num) {
		return con.get(num);
	}


	public static ArrayList<Medicamento> getLista() {
		return con.getLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static int getId() {
		return con.getId();
	}
}
