package DAOSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.IEspecialidade;
import MODEL.Especialidade;
import VIEW.TelaCadEspecialidade;


/**
 * 
 * @author luan
 *
 */
public class EspecialidadeSql implements IEspecialidade{
	private Connection con = null;
	PreparedStatement pst = null;
	private Especialidade Especialidade;
	
	
	public EspecialidadeSql() {
		Especialidade = new Especialidade();
	}

	@Override
	public void set(Especialidade novo) {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO especialidade (cbo,nome) VALUES (?,?)");
				pst.setInt(1, Integer.parseInt(novo.getCbo()));
				pst.setString(2, novo.getNome());
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
			pst = con.prepareStatement("DELETE FROM especialidade  WHERE IDESPEC=?");
			pst.setInt(1,id);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void update(Especialidade novo) {
			String sql = "UPDATE especialidade  SET CBO=?,nome=? WHERE CBO  = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, Integer.parseInt(novo.getCbo()));
				pst.setString(2, novo.getNome());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

	@Override
	public Especialidade get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Especialidade where IDESPEC=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Especialidade = new Especialidade();
				Especialidade.setId(rs.getInt("IDESPEC"));
				Especialidade.setCbo(""+rs.getInt("cbo"));
				Especialidade.setNome(rs.getString("nome"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Especialidade;
		
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM especialidade");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

	@Override
	public Especialidade get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Especialidade where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Especialidade = new Especialidade();
				Especialidade.setId(rs.getInt("IDESPEC"));
				Especialidade.setCbo(rs.getString("cbo"));
				Especialidade.setNome(rs.getString("nome"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Especialidade;
		
	}

	
	@Override
	public ArrayList<Especialidade> getLista() {
		return null;
	}
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(IDESPEC) from Especialidade");
			ResultSet rs= pst.executeQuery();
			rs.next();
			id=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return id;
	}
	
	public void  preencherLista() {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Especialidade");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Especialidade = new Especialidade();
				Especialidade.setId(rs.getInt("IDESPEC"));
				Especialidade.setCbo(rs.getString("cbo"));
				Especialidade.setNome(rs.getString("nome"));
				TelaCadEspecialidade.preencherLista(Especialidade);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public String[] preencherListaNomes() {
		String [] nomes=new String[getTamanho()+1];
		nomes[0]="-";
		int cont=1;
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT nome FROM especialidade  order by nome");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			nomes[cont]=rs.getString("nome");
			cont++;
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return nomes;
		
	}
	public Especialidade getCbo(String cbo) {
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Especialidade where cbo=?");
			pst.setString(1, cbo);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Especialidade = new Especialidade();
				Especialidade.setId(rs.getInt("IDESPEC"));
				Especialidade.setCbo(""+rs.getInt("cbo"));
				Especialidade.setNome(rs.getString("nome"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Especialidade;
		
	}

}
