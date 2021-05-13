package DAOSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.InterFace.IAgendaConsulta;
import MODEL.AgendaConsulta;


/**
 * 
 * @author luan
 *
 */
public class AgendaConsultaSql implements IAgendaConsulta {
	private Connection con = null;
	PreparedStatement pst = null;
	private AgendaConsulta  AgendaConsulta ;
	
	
	public AgendaConsultaSql() {
		AgendaConsulta  = new AgendaConsulta ();
	}

	@Override
	public void set(AgendaConsulta  novo) {
		//codigo pra conexao de banco de daos pSostgre
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO AgendaConsulta (hora,dataconsult,idpaciente,idproced,marMedico,status,descont,valor,obs) VALUES (?,?,?,?,?,?,?,?,?)");
				pst.setString(1, novo.getHora());
				Date d1 = null;
				if(novo.getData()!=null) {
				 d1= new Date (novo.getData().getTime());
				}
				pst.setDate(2, d1);
				pst.setInt(3, novo.getIdPaciente());
				pst.setInt(4, Integer.parseInt(novo.getIdProce()));
				pst.setString(5, novo.getMatMedico());
				pst.setString(6, novo.getStatus());
				pst.setFloat(7, novo.getDesc());
				pst.setFloat(8, novo.getValor());
				pst.setString(9,novo.getObs());
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
			pst = con.prepareStatement("DELETE FROM AgendaConsulta WHERE ID=?");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(AgendaConsulta  novo) {
			String sql = "UPDATE AgendaConsulta  SET hora=?, dataconsult=?, idpaciente=?, idproced=?, marmedico=?, status=?, descont=?, valor=?, obs=?  WHERE ID=?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getHora());
				Date d1 = null;
				if(novo.getData()!=null) {
				 d1= new Date (novo.getData().getTime());
				}
				pst.setDate(2, d1);
				pst.setInt(3, novo.getIdPaciente());
				pst.setInt(4, Integer.parseInt(novo.getIdProce()));
				pst.setString(5, novo.getMatMedico());
				pst.setString(6, novo.getStatus());
				pst.setFloat(7, novo.getDesc());
				pst.setFloat(8, novo.getValor());
				pst.setString(9,novo.getObs());
				
				pst.setInt(10, novo.getId());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();

			}
			
	
	}

	@Override
	public AgendaConsulta  get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM AgendaConsulta  where id=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				AgendaConsulta  = new AgendaConsulta ();
				AgendaConsulta .setId(rs.getInt("ID"));
				AgendaConsulta .setHora(rs.getString("hora"));
				AgendaConsulta .setData(rs.getDate("dataConsult"));
				AgendaConsulta .setIdPaciente(rs.getInt("idPaciente"));
				AgendaConsulta .setIdProce(rs.getString("idProced"));
				AgendaConsulta .setMatMedico(rs.getString("marMedico"));
				AgendaConsulta .setStatus(rs.getString("status"));
				AgendaConsulta .setDesc(rs.getFloat("descont"));
				AgendaConsulta .setValor(rs.getFloat("valor"));
				AgendaConsulta .setObs(rs.getString("obs"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return AgendaConsulta ;
		
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM AgendaConsulta");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

	@Override
	public AgendaConsulta  get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM AgendaConsulta  where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				AgendaConsulta  = new AgendaConsulta ();
				AgendaConsulta .setId(rs.getInt("ID"));
				AgendaConsulta .setHora(rs.getString("hora"));
				AgendaConsulta .setData(rs.getDate("dataConsult"));
				AgendaConsulta .setIdPaciente(rs.getInt("idPaciente"));
				AgendaConsulta .setIdProce(rs.getString("idProced"));
				AgendaConsulta .setMatMedico(rs.getString("marMedico"));
				AgendaConsulta .setStatus(rs.getString("status"));
				AgendaConsulta .setDesc(rs.getFloat("descont"));
				AgendaConsulta .setValor(rs.getFloat("valor"));
				AgendaConsulta .setObs(rs.getString("obs"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return AgendaConsulta ;
		
	}


	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(ID) from AgendaConsulta ");
			ResultSet rs= pst.executeQuery();
			rs.next();
			id=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 if (id==0) {
	        	id=1;
	        }
			return id;
	}
	
	@Override
	public ArrayList<AgendaConsulta> getLista() {
		ArrayList<AgendaConsulta> lista = new ArrayList<>();
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM AgendaConsulta");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				AgendaConsulta  = new AgendaConsulta ();
				AgendaConsulta .setId(rs.getInt("ID"));
				AgendaConsulta .setHora(rs.getString("hora"));
				AgendaConsulta .setData(rs.getDate("dataConsult"));
				AgendaConsulta .setIdPaciente(rs.getInt("idPaciente"));
				AgendaConsulta .setIdProce(rs.getString("idProced"));
				AgendaConsulta .setMatMedico(rs.getString("marMedico"));
				AgendaConsulta .setStatus(rs.getString("status"));
				AgendaConsulta .setDesc(rs.getFloat("descont"));
				AgendaConsulta .setValor(rs.getFloat("valor"));
				AgendaConsulta .setObs(rs.getString("obs"));
				lista.add(AgendaConsulta);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public ResultSet getListaDate(java.util.Date data , String matMedico) throws SQLException {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM AgendaConsulta  where dataConsult=? and marMedico=? order by ID");
			Date d = new Date(data.getTime());
			pst.setDate(1, d);
			pst.setString(2, matMedico);
			ResultSet rs=pst.executeQuery();
		return rs;	
	}
	public ResultSet  getHistoricoConsultas(String nome) {
		ResultSet rs=null;
		System.out.println(nome);
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("select c.id,c.dataconsult,c.hora,m.nome,proc.nome,c.status from agendaconsulta c join paciente p on c.idpaciente=p.idpac" + 
					" join profissional m on c.marmedico=m.matmedico" + 
					" join procedimento proc on c.idproced=proc.idproced where p.nome=?");
			pst.setString(1, nome);
			rs=pst.executeQuery();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs ;
		
	}
	
	public ResultSet TestZap() throws SQLException {
		con = ConexaoSingleton.getInstance();
		ResultSet rs=null;
		pst = con.prepareStatement("select c.dataconsult,c.hora,p.nome,p.celular,m.nome,proc.nome"
			   + " from agendaconsulta c join paciente p" + 
				" on c.idpaciente=p.idpac" + 
				" join profissional m on c.marmedico=m.matmedico" + 
				" join procedimento proc on c.idproced=proc.idproced" + 
				" where DATACONSULT=CURRENT_DATE and p.celular<>''");
		rs=pst.executeQuery();
	return rs;	
	
}
	
	public int tamanhoListaZap(){
		con = ConexaoSingleton.getInstance();
		ResultSet rs=null;
		int tam=0;
		try {
			pst = con.prepareStatement("select c.dataconsult,c.hora,p.nome,p.celular,m.nome,proc.nome"
					+ " from agendaconsulta c join paciente p" + 
					" on c.idpaciente=p.idpac" + 
					" join profissional m on c.marmedico=m.matmedico" + 
					" join procedimento proc on c.idproced=proc.idproced" + 
					" where DATACONSULT=CURRENT_DATE and p.celular<>''");
			
			rs=pst.executeQuery();
	
			while(rs.next()) {
				
				tam++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return tam;	
}

	


}
