package DAOSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import DAO.InterFace.IHistorico;
import MODEL.Historico;

/**
 * 
 * @author luan
 *
 */
public class HistoricoPacienteSql implements IHistorico{
	private Connection con = null;
	PreparedStatement pst = null;

	@Override
	public void insert(Historico novo) {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO historicoPac (idPac,MATMEDICO,historico,date_hist) VALUES (?,?,?,?)");
				int id = Integer.parseInt(novo.getPaciente_CPF());
				pst.setInt(1, id);
				pst.setString(2,novo.getMatMedico());
				pst.setString(3, novo.getHistorio());
				Date data = new Date (novo.getDateHist().getTime());
				pst.setDate(4,data);
				pst.execute();
			}catch (SQLException e) {
				e.printStackTrace();
	
				
			}
			
			
	}
	@Override
	public void remove(int id){
		//codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM historicoPac  WHERE idhistorico=?");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}
	@Override
	public void update(Historico novo) {
			String sql = "UPDATE historicoPac  SET idPac=? MATMEDICO=? historico=? date_hist=? WHERE idhistorico=?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				int id = Integer.parseInt(novo.getPaciente_CPF());
				pst.setInt(1,id);
				pst.setString(2,novo.getMatMedico());
				pst.setString(3, novo.getHistorio());
				Date data = new Date (novo.getDateHist().getTime());
				pst.setDate(4,data);
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
	
	}
	@Override
	public Historico getHistorico(int cod) {
		Historico histo = null;
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM historicoPac  where idhistorico=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			
				histo = new Historico();
				histo.setId(rs.getInt("idhistorico"));
				histo.setPaciente_CPF(""+rs.getInt("IDPAC"));
				histo.setMatMedico(rs.getString("MATMEDICO"));
				histo.setHistorio(rs.getString("HISTORICO"));
				histo.setDateHist(rs.getDate("DATE_HIST"));
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return histo;
		
	}

@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM historicoPac");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

@Override
public ResultSet getHistoricoCompleto(String cpf) {
	ResultSet rs = null;
	try {
		con = ConexaoSingleton.getInstance();
		int id = Integer.parseInt(cpf);
		pst = con.prepareStatement("SELECT * FROM historicoPac where h.idpac=?");
		pst.setInt(1, id);
		rs=pst.executeQuery();
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return rs;
}

@Override
public Historico getHistoricoData(java.util.Date data) {
	Historico historico = null;
	try {
		con = ConexaoSingleton.getInstance();
		pst = con.prepareStatement("SELECT h.idhistorico,h.idpac,h.matmedico,h.historico,h.historico,h.date_hist FROM historicoPac " + 
				"join paciente p on h.idpac=p.cpf where date_hist=?");
		ResultSet rs=pst.executeQuery();
		
		while(rs.next()) {
			historico = new Historico();
			historico.setId(rs.getInt("idhistorico"));
			historico.setPaciente_CPF(rs.getString("IDPAC"));
			historico.setMatMedico(rs.getString("MATMEDICO"));
			historico.setHistorio(rs.getString("HISTORICO"));
			historico.setDateHist(rs.getDate("DATE_HIST"));
		}
	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return historico;
}
@Override
public int getId() {
	int id = 0;
	con = ConexaoSingleton.getInstance();
	try {
		pst = con.prepareStatement("SELECT max(idhistorico) from historicoPac");
		ResultSet rs= pst.executeQuery();
		rs.next();
		id=rs.getInt(1);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	if(id==0) {
		id=1;
	}

	return id;
}
@Override
public String getHistoricoIntervalo(int idpac) {
	
	String m = "";
	try {
		con = ConexaoSingleton.getInstance();
		//pegando a data de hoje
		Calendar calendar = Calendar.getInstance();
		
		Date fim = new Date(calendar.getTime().getTime());
        //diminnuindo dois anos
		calendar.add(Calendar.YEAR, -2);
		Date inicio = new Date(calendar.getTime().getTime());// tirando 2 anos
		

		//consulta em sql
		pst = con.prepareStatement("SELECT historico FROM historicoPac where IDPAC=? AND DATE_HIST BETWEEN ? AND ?" );
		pst.setInt(1, idpac);
		pst.setDate(2, inicio);
		pst.setDate(3, fim);
		ResultSet rs=pst.executeQuery();
		while (rs.next()) {
		    m+=rs.getString("historico")+"\n_____________________________________________________"+"\n\n";
			
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return m;
}

	
}
