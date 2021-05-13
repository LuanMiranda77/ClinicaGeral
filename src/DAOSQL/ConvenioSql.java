package DAOSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.InterFace.IConvenio;
import MODEL.Convenio;
import VIEW.TelaCadConvenio;


/**
 * 
 * @author agemiro
 *
 */
public class ConvenioSql implements IConvenio{
	private Connection con = null;
	PreparedStatement pst = null;
	private Convenio Convenio;
	
	
	public ConvenioSql() {
		Convenio = new Convenio();
	}

	@Override
	public void set(Convenio novo) {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO Convenio (nome,tabela,celular,telfixo,email) VALUES (?,?,?,?,?)");
				pst.setString(1, novo.getNome());
				pst.setString(2, novo.getTabela());
				pst.setString(3, novo.getCelular());
				pst.setString(4, novo.getFixo());
				pst.setString(5, novo.getEmail());
				pst.execute();
			}catch (SQLException e) {
				e.printStackTrace();
	
				
			}
			
			
	}
	public void remover(int id){
		//codigo pra conexao de banco de daos postgre
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("DELETE FROM Convenio WHERE IDCONV = ?");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void update(Convenio novo) {
			String sql = "UPDATE Convenio SET nome=?,tabela=?,celular=?,telfixo=?,email=? WHERE IDCONV = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getNome());
				pst.setString(2, novo.getTabela());
				pst.setString(3, novo.getCelular());
				pst.setString(4, novo.getFixo());
				pst.setString(5, novo.getEmail());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("editado com sucesso");
			
	
	}

	@Override
	public Convenio get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Convenio where IDCONV=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Convenio = new Convenio();
				Convenio.setId(rs.getInt("idconv"));
				Convenio.setNome(rs.getString("nome"));
				Convenio.setTabela(rs.getString("tabela"));
				Convenio.setCelular(rs.getString("celular"));
				Convenio.setFixo(rs.getString("telfixo"));
				Convenio.setEmail(rs.getString("email"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Convenio;
		
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM Convenio");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

	@Override
	public Convenio get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM Convenio where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Convenio = new Convenio();
				Convenio.setId(rs.getInt("idconv"));
				Convenio.setNome(rs.getString("nome"));
				Convenio.setTabela(rs.getString("tabela"));
				Convenio.setCelular(rs.getString("celular"));
				Convenio.setFixo(rs.getString("telfixo"));
				Convenio.setEmail(rs.getString("email"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Convenio;
		
	}

	@Override
	public void remove(int id) {
		//codigo pra conexao de banco de daos postgre
				try {
					con = ConexaoSingleton.getInstance();
					pst = con.prepareStatement("DELETE FROM Convenio WHERE idconv = ?");
					pst.setInt(1, id);
					pst.execute();
				}catch(SQLException e) {
					
				}
	}

	@Override
	public ArrayList<Convenio> getLista() {
		return null;
	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(idconv) from convenio");
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
			pst = con.prepareStatement("SELECT *FROM Convenio");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Convenio = new Convenio();
				Convenio.setId(rs.getInt("idconv"));
				Convenio.setNome(rs.getString("nome"));
				Convenio.setTabela(rs.getString("tabela"));
				Convenio.setCelular(rs.getString("celular"));
				Convenio.setFixo(rs.getString("telfixo"));
				Convenio.setEmail(rs.getString("email"));
				TelaCadConvenio.addLinha(Convenio);


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
			pst = con.prepareStatement("SELECT nome FROM Convenio order by nome");
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
	public static void main(String[] args) {
		ConvenioSql v = new ConvenioSql();
		System.out.println(v.getId());
	}
	

}
