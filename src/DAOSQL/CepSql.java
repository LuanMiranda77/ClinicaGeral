package DAOSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.ICEP;
import MODEL.CEP;
/**
 * 
 * @author agemiro
 *
 */
public class CepSql implements ICEP{
	private Connection con = null;
	PreparedStatement pst = null;
	private CEP cep;
	
	
	public CepSql() {
		cep = new CEP();
	}

	@Override
	public void insert(CEP novo) {
		//codigo pra conexao de banco de daos pSostgre
		
           if(testCep(novo.getCod())==false) {
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO cep (CEP,RUA,BAIRRO,CIDADE,UF) VALUES (?,?,?,?,?)");
				pst.setString(1, novo.getCod());
				pst.setString(2, novo.getRua());
				pst.setString(3, novo.getBairro());
				pst.setString(4, novo.getCidade());
				pst.setString(5, novo.getUF());
				pst.execute();
			}catch (SQLException e) {
				e.printStackTrace();
	
				
			}
           }
			
			
	}
	public void remover(String cep){
		//codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM cep WHERE cep = ?");
			pst.setString(1, cep);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void update(CEP novo) {
			String sql = "UPDATE cep SET cep=?,rua=?,bairro=?,cidade=?,uf=? WHERE cep = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getCod());
				pst.setString(2, novo.getRua());
				pst.setString(3, novo.getBairro());
				pst.setString(4, novo.getCidade());
				pst.setString(5, novo.getUF());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("editado com sucesso");
			
	
	}
	@Override
	public CEP getCod(String cep1) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cep where cep=?");
			pst.setString(1, cep1);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				cep = new CEP();
				cep.setCod(rs.getString("cep"));
				cep.setRua(rs.getString("rua"));
				cep.setBairro(rs.getString("bairro"));
				cep.setCidade(rs.getString("cidade"));
				cep.setUF(rs.getString("uf"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cep;
		
	}
	public boolean testCep(String cp) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cep where cep=?");
			pst.setString(1, cp);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				return true;
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
		
	}

   @Override
	public CEP getNome(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM cep where nome=?");
			pst.setString(2, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				cep = new CEP();
				cep.setCod(rs.getString("cep"));
				cep.setRua(rs.getString("rua"));
				cep.setBairro(rs.getString("bairro"));
				cep.setCidade(rs.getString("cidade"));
				cep.setUF(rs.getString("uf"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return cep;
		
	}

	@Override
	public void remove(int id) {
		//codigo pra conexao de banco de daos postgre
				try {
					con = ConexaoSingleton.getInstance();
					pst = con.prepareStatement("DELETE FROM cep WHERE idconv = ?");
					pst.setInt(1, id);
					pst.execute();
				}catch(SQLException e) {
					
				}
	}

	@Override
	public ArrayList<CEP> getLista() {
		return null;
	}
	

}
