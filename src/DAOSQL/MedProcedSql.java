package DAOSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedProcedSql {
	
	private Connection con = null;
	PreparedStatement pst = null;
	
	public void insert(String matMedico, int idProced) {
		//codigo pra conexao de banco de daos pSostgre
		
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO MEDPROCED (MATMEDICO,IDPROCED) VALUES (?,?)");
				pst.setString(1, matMedico);
				pst.setInt(2, idProced);
				pst.execute();
			}catch (SQLException e) {
				e.printStackTrace();
	
				
			}
			
			
	}
	
	public void remove(String matMedico, int idProced){
		//codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM MEDPROCED  WHERE MATMEDICO=? and IDPROCED=?");
			pst.setString(1,matMedico);
			pst.setInt(2,idProced);
			pst.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String matMedico, int idProced) {
			String sql = "UPDATE MEDPROCED   SET MATMEDICO=?,IDPROCED=? WHERE MATMEDICO=? and IDPROCED=?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1,matMedico);
				pst.setInt(2,idProced);
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

	public ArrayList<String> getLista(String matMedico) {
		ArrayList<String> lista = new ArrayList<>();
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("select  p.nome from MEDPROCED m  join procedimento p on m.idproced=p.idproced" + 
					" join profissional prof on prof.matmedico=m.matmedico where m.matmedico=? order by p.nome");
			pst.setString(1, matMedico);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				lista.add(rs.getString("nome"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}


}
