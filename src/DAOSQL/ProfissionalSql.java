package DAOSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CONTROL.CTEspecialidade;
import DAO.InterFace.IProfissional;
import MODEL.Profissional;
import VIEW.TelaGerProf;

/**
 * 
 * @author luan
 *
 */
public class ProfissionalSql implements IProfissional {
	private Connection con = null;
	private PreparedStatement pst = null;
	private Profissional Profissional;
	private MedProcedSql cox;

	public ProfissionalSql() {
		Profissional = new Profissional();
	}

	@Override
	public void set(Profissional novo) throws SQLException{
		// codigo pra conexao de banco de daos pSostgre

		con = ConexaoSingleton.getInstance();
		Profissional = novo;
			pst = con.prepareStatement(
					"INSERT INTO Profissional (MATMEDICO,NOME,IDESPEC,CELULAR,TELFIXO,RECADO,EMAIL,FOTO,nomeagenda) VALUES (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, novo.getMatricula());
			pst.setString(2, novo.getNome());
			pst.setString(3, novo.getEspecialidade());
			pst.setString(4, novo.getCelular1());
			pst.setString(5, novo.getTelFixo());
			pst.setString(6, novo.getCelular2());
			pst.setString(7, novo.getEmil());
			pst.setString(8, novo.getFoto());
			pst.setString(9, novo.getAgenda());
			pst.execute();
			cox = new MedProcedSql();
			for (String proced : novo.getProcedimento()) {
				cox.insert(novo.getMatricula(),Integer.parseInt(proced) );
			}
	}

	@Override
	public void remove(int id) {
		// codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM Profissional  WHERE IDPROF=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {

		}
	}

	@Override
	public void update(Profissional novo) {
		String sql = "UPDATE Profissional  SET NOME=?,IDESPEC=?,CELULAR=?,TELFIXO=?,RECADO=?,EMAIL=?,FOTO=?, NOMEAGENDA=? WHERE MATMEDICO=?";
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, novo.getNome());
			pst.setString(2, novo.getEspecialidade());
			pst.setString(3, novo.getCelular1());
			pst.setString(4, novo.getTelFixo());
			pst.setString(5, novo.getCelular2());
			pst.setString(6, novo.getEmil());
			pst.setString(7, novo.getFoto());
			pst.setString(8, novo.getAgenda());
			//o ultimo paramentro sempre é a comparcação de dados para a atualização
			pst.setString(9, novo.getMatricula());
			pst.execute();
			cox = new MedProcedSql();
			for (String proced : novo.getProcedimento()) {
				cox.insert(novo.getMatricula(),Integer.parseInt(proced) );
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Profissional get(int cod) {

		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Profissional  where idProfissional =?");
			pst.setInt(1, cod);
			ResultSet rs = pst.executeQuery();
			cox = new MedProcedSql();
			while (rs.next()) {
				Profissional = new Profissional();
				Profissional.setId(rs.getInt("IDPROF"));
				Profissional.setMatricula(rs.getString("Matmedico"));
				Profissional.setNome(rs.getString("nome"));
				Profissional.setEspecialidade(rs.getString("IDESPEC"));
				Profissional.setCelular1(rs.getString("CELULAR"));
				Profissional.setTelFixo(rs.getString("TELFIXO"));
				Profissional.setCelular2(rs.getString("recado"));
				Profissional.setEmil(rs.getString("email"));
				Profissional.setFoto(rs.getString("foto"));
				Profissional.setProcedimento(cox.getLista(rs.getString("Matmedico")));
				Profissional.setAgenda(rs.getString("NOMEAGENDA"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Profissional;

	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM Profissional");
			ResultSet rs = pst.executeQuery();
			rs.next();
			tamanho = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tamanho;
	}

	@Override
	public Profissional get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Profissional  where nome=?");
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			cox = new MedProcedSql();
			
			while (rs.next()) {
				Profissional = new Profissional();
				Profissional.setId(rs.getInt("IDPROF"));
				Profissional.setMatricula(rs.getString("Matmedico"));
				Profissional.setNome(rs.getString("nome"));
				Profissional.setEspecialidade(rs.getString("IDESPEC"));
				Profissional.setCelular1(rs.getString("CELULAR"));
				Profissional.setTelFixo(rs.getString("TELFIXO"));
				Profissional.setCelular2(rs.getString("recado"));
				Profissional.setEmil(rs.getString("email"));
				Profissional.setFoto(rs.getString("foto"));
				Profissional.setProcedimento(cox.getLista(rs.getString("Matmedico")));
				Profissional.setAgenda(rs.getString("NOMEAGENDA"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Profissional;

	}

	@Override
	public ArrayList<Profissional> getLista() {
		return null;
	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(IDPROF) from Profissional ");
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
			pst = con.prepareStatement("SELECT *FROM Profissional ");
			ResultSet rs = pst.executeQuery();
			
			
			while (rs.next()) {
				Profissional = new Profissional();
				Profissional.setId(rs.getInt("IDPROF"));
				Profissional.setMatricula(rs.getString("Matmedico"));
				Profissional.setNome(rs.getString("nome"));
				Profissional.setEspecialidade(CTEspecialidade.getCbo(rs.getString("IDESPEC")).getNome());
				Profissional.setCelular1(rs.getString("CELULAR"));
				Profissional.setTelFixo(rs.getString("TELFIXO"));
				Profissional.setCelular2(rs.getString("recado"));
				Profissional.setEmil(rs.getString("email"));
				Profissional.setFoto(rs.getString("foto"));
				TelaGerProf.addLinha(Profissional);
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
			pst = con.prepareStatement("SELECT nome FROM Profissional  order by nome");
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
	public Profissional getMedico(String matricula) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Profissional  where Matmedico=?");
			pst.setString(1, matricula);
			ResultSet rs = pst.executeQuery();
			cox = new MedProcedSql();
			while (rs.next()) {
				Profissional = new Profissional();
				Profissional.setId(rs.getInt("IDPROF"));
				Profissional.setMatricula(rs.getString("Matmedico"));
				Profissional.setNome(rs.getString("nome"));
				Profissional.setEspecialidade(rs.getString("IDESPEC"));
				Profissional.setCelular1(rs.getString("CELULAR"));
				Profissional.setTelFixo(rs.getString("TELFIXO"));
				Profissional.setCelular2(rs.getString("recado"));
				Profissional.setEmil(rs.getString("email"));
				Profissional.setFoto(rs.getString("foto"));
				Profissional.setAgenda(rs.getString("NOMEAGENDA"));
				Profissional.setProcedimento(cox.getLista(Profissional.getMatricula()));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Profissional;
	}

	@Override
	public ArrayList<String> getProced(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
