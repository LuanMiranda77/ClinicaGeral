package DAOSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.IMedicamento ;
import MODEL.Medicamento ;
import VIEW.TelaCadMedicamento;


/**
 * 
 * @author luan
 *
 */
public class MedicamentoSql implements IMedicamento {
	private Connection con = null;
	PreparedStatement pst = null;
	private Medicamento  Medicamento ;
	
	
	public MedicamentoSql() {
		Medicamento  = new Medicamento ();
	}

	@Override
	public void set(Medicamento  novo) {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO Medicamento (nome,mg) VALUES (?,?)");
				pst.setString(1, novo.getDesc());
				pst.setDouble(2, novo.getmg());
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
			pst = con.prepareStatement("DELETE FROM Medicamento WHERE IDMEDC=?");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void up(Medicamento  novo) {
			String sql = "UPDATE Medicamento  SET nome=?,MG=? WHERE IDMEDC  = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getDesc());
				pst.setDouble(2, novo.getmg());
				//parametro de test de idmedc
				pst.setInt(3,novo.getId());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	
	}

	@Override
	public Medicamento  get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Medicamento  where IDMEDC =?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Medicamento  = new Medicamento ();
				Medicamento .setId(rs.getInt("idmedic"));
				Medicamento .setDesc(rs.getString("nome"));
				Medicamento .setmg(rs.getDouble("MG"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Medicamento ;
		
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM Medicamento");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

	@Override
	public Medicamento  get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Medicamento  where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Medicamento  = new Medicamento ();
				Medicamento .setId(rs.getInt("IDMEDC"));
				Medicamento .setDesc(rs.getString("nome"));
				Medicamento .setmg(rs.getDouble("MG"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Medicamento ;
		
	}


	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(IDMEDC) from Medicamento ");
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
			pst = con.prepareStatement("SELECT *FROM MEDICAMENTO");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Medicamento  = new Medicamento ();
				Medicamento .setId(rs.getInt("idmedc"));
				Medicamento .setDesc(rs.getString("nome"));
				Medicamento .setmg(rs.getDouble("mg"));;
				TelaCadMedicamento.addLinha(Medicamento);
				System.out.print(Medicamento.getDesc());
				
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
			pst = con.prepareStatement("SELECT nome FROM Medicamento  order by nome");
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

	@Override
	public ArrayList<MODEL.Medicamento> getlista() {
		// TODO Auto-generated method stub
		return null;
	}


}
