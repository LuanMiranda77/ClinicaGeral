package DAOSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.IProcedimento;
import MODEL.Procedimento;
import VIEW.TelaCadProcedimento;

/**
 * 
 * @author luan
 *
 */
public class ProcedimentoSql implements IProcedimento {
	private Connection con = null;
	PreparedStatement pst = null;
	private Procedimento Procedimento;

	public ProcedimentoSql() {
		Procedimento = new Procedimento();
	}

	@Override
	public void set(Procedimento novo) {
		// codigo pra conexao de banco de daos pSostgre

		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("INSERT INTO procedimento (IDPROCED,nome,valor) VALUES (?,?,?)");
			pst.setInt(1, novo.getId());
			pst.setString(2, novo.getDesc());
			pst.setDouble(3, novo.getValor());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void remove(int id) {
		// codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM PROCEDIMENTO WHERE IDPROCED=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {

		}
	}

	@Override
	public void update(Procedimento novo) {
		String sql = "UPDATE procedimento  SET nome=?,valor=? WHERE IDPROCED=?";
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, novo.getDesc());
			pst.setDouble(2, novo.getValor());

			// paramentro de test
			pst.setInt(3, novo.getId());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Procedimento get(int cod) {

		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Procedimento  where IDPROCED  =?");
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Procedimento = new Procedimento();
				Procedimento.setId(rs.getInt("IDPROCED"));
				Procedimento.setDesc(rs.getString("nome"));
				Procedimento.setValor(rs.getDouble("valor"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Procedimento;

	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM procedimento");
			ResultSet rs = pst.executeQuery();
			rs.next();
			tamanho = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tamanho;
	}

	@Override
	public Procedimento get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Procedimento  where nome=?");
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Procedimento = new Procedimento();
				Procedimento.setId(rs.getInt("IDPROCED"));
				Procedimento.setDesc(rs.getString("nome"));
				Procedimento.setValor(rs.getDouble("valor"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Procedimento;

	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(idproced) from Procedimento ");
			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void preencherLista() {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Procedimento ");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Procedimento = new Procedimento();
				Procedimento.setId(rs.getInt("IDPROCED"));
				Procedimento.setDesc(rs.getString("nome"));
				Procedimento.setValor(rs.getDouble("valor"));
				;
				TelaCadProcedimento.preenchertabela(Procedimento);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public String[] preencherListaNomes() {
		String[] nomes = new String[getTamanho() + 1];
		nomes[0] = "-";
		int cont = 1;
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT nome FROM procedimento  order by nome");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				nomes[cont] = rs.getString("nome");
				cont++;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return nomes;

	}
	
	@Override
	public ArrayList<MODEL.Procedimento> getlista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void up(MODEL.Procedimento editado) {
		// TODO Auto-generated method stub

	}

}
