package CONTROL;
import DAOSQL.ProcedimentoSql;
import MODEL.Procedimento;
import MODEL.Conexao.CXProcedimento;

public class CTProcedimento {
	private static CXProcedimento con = new CXProcedimento();
	private static ProcedimentoSql bd = new ProcedimentoSql();

	public static void insert(Procedimento novo) {
		con.set(novo);
	}

	public static void update(Procedimento update) {
		bd.update(update);
	}

	public static void remove(int id) {
		con.remove(id);
	}
	
	public static String[] getListaNomes() {
		return bd.preencherListaNomes();
	}

	public static Procedimento getProcedimento(String nome) {
		return con.get(nome);
	}

	public static Procedimento getProcedimento(int num) {
		return con.get(num);
	}


	public static void getLista() {
		bd.preencherLista();
	}

	public static int getTamanho() {
		return con.getTamanho();
	}
	public static int getId() {
		return con.getId();
	}
}
