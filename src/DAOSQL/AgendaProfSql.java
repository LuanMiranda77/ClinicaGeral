package DAOSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.IAgendaProf;
import MODEL.AgendaProf;
import MODEL.Dia;

/**
 * 
 * @author luan
 *
 */
public class AgendaProfSql implements IAgendaProf {
	private Connection con = null;
	PreparedStatement pst = null;
	private AgendaProf AgendaProf;

	public AgendaProfSql() {
		AgendaProf = new AgendaProf();
	}

	@Override
	public void set(AgendaProf novo) {
		// codigo pra conexao de banco de daos pSostgre
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("INSERT INTO AgendaProf (nome) VALUES (?)");
			pst.setString(1, novo.getNome());
			pst.execute();
			insertHorarios(novo);
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void insertHorarios(AgendaProf novo) {
		con = ConexaoSingleton.getInstance();
		int id = getId();
		try {
			for (String hora : novo.getDomingo().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (1,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getSegunda().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (2,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getTerca().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (3,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getQuarta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (4,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getQuinta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (5,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getSexta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (6,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getSabado().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (7,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public void updateHorarios(AgendaProf novo) {
		con = ConexaoSingleton.getInstance();
		int id = getId();
		try {
			for (String hora : novo.getDomingo().getHora()) {
				pst = con.prepareStatement(
						"update HORARIOAGENDA  hora=? where idagenda=" + novo.getId() + "and" + "iddia=1");
				pst.setString(2, hora);
				pst.execute();
			}
			for (String hora : novo.getSegunda().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (2,?,?)");
				pst.setString(2, hora);
				pst.execute();
			}
			for (String hora : novo.getTerca().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (3,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getQuarta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (4,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getQuinta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (5,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getSexta().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (6,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
			for (String hora : novo.getSabado().getHora()) {
				pst = con.prepareStatement("INSERT INTO HORARIOAGENDA (iddia,hora,idagenda) VALUES (7,?,?)");
				pst.setString(2, hora);
				pst.setInt(3, id);
				pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void remove(int id) {
		// codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM AgendaProf WHERE IDAGPROF=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void update(AgendaProf novo) {
		String sql = "UPDATE AgendaProf  SET nome=? WHERE IDAGPROF=?";
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, novo.getNome());
			updateHorarios(novo);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public AgendaProf get(int cod) {

		return null;

	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM AgendaProf");
			ResultSet rs = pst.executeQuery();
			rs.next();
			tamanho = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tamanho;
	}

	@Override
	public AgendaProf get(String nome) {
		try {
			Dia op = new Dia();
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM AgendaProf  where nome=?");
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				AgendaProf = new AgendaProf();
				AgendaProf.setId(rs.getInt("IDAGPROF"));
				int id = AgendaProf.getId();
				AgendaProf.setNome(rs.getString("nome"));
				op = preencherDia(1, id);
				AgendaProf.setDomingo(op);
				op = preencherDia(2, id);
				AgendaProf.setSegunda(op);
				op = preencherDia(3, id);
				AgendaProf.setTerca(op);
				op = preencherDia(4, id);
				AgendaProf.setQuarta(op);
				op = preencherDia(5, id);
				AgendaProf.setQuinta(op);
				op = preencherDia(6, id);
				AgendaProf.setSexta(op);
				op = preencherDia(7, id);
				AgendaProf.setSabado(op);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return AgendaProf;

	}

	public Dia preencherDia(int iddia, int idAgenda) {
		Dia dia = new Dia();
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement(
					"select hora from horarioagenda h join dia d on d.iddia=h.iddia join agendaprof a on a.idagprof=h.idagenda where h.idagenda="
							+ idAgenda + "and d.iddia=" + iddia);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				dia.addHora(rs.getString("hora"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return dia;

	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(IDAGPROF) from AgendaProf ");
			ResultSet rs = pst.executeQuery();
			rs.next();
			id = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public String[] preencherListaNomes() {
		String[] nomes = new String[getTamanho() + 1];
		nomes[0] = "-";
		int cont = 1;
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT nome FROM AgendaProf  order by nome");
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
	public ArrayList<AgendaProf> getLista() {
		ArrayList<AgendaProf> lista = new ArrayList<>();
		Dia op = new Dia();
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM AgendaProf");

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				AgendaProf = new AgendaProf();
				AgendaProf.setId(rs.getInt("IDAGPROF"));
				int id = AgendaProf.getId();
				AgendaProf.setNome(rs.getString("nome"));
				op = preencherDia(1, id);
				AgendaProf.setDomingo(op);
				op = preencherDia(2, id);
				AgendaProf.setSegunda(op);
				op = preencherDia(3, id);
				AgendaProf.setTerca(op);
				op = preencherDia(4, id);
				AgendaProf.setQuarta(op);
				op = preencherDia(5, id);
				AgendaProf.setQuinta(op);
				op = preencherDia(6, id);
				AgendaProf.setSexta(op);
				op = preencherDia(7, id);
				AgendaProf.setSabado(op);
				lista.add(AgendaProf);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return lista;
	}

}
