package DAOSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.InterFace.IFuncionario ;
import MODEL.Funcionario ;
import VIEW.TelaGerFunc;


/**
 * 
 * @author agemiro
 *
 */
public class FunSql implements IFuncionario {
	private Connection con = null;
	PreparedStatement pst = null;
	private Funcionario  Funcionario ;
	
	
	public FunSql() {
		Funcionario  = new Funcionario ();
	}

	@Override
	public void set(Funcionario  novo) {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO usuario (nome,cargo,nomeprof,senha) VALUES (?,?,?,?)");
				pst.setString(1, novo.getNome());
				pst.setString(2, novo.getCargo());
				pst.setString(3, novo.getNomeProf());
				pst.setString(4, novo.getSenha());
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
			pst = con.prepareStatement("DELETE FROM USUARIO  WHERE IDUSER=?");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void update(Funcionario  novo) {
			String sql = "UPDATE usuario  SET nome=?,cargo=?,nomeprof=?,senha=? WHERE iduser  = ?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getNome());
				pst.setString(2, novo.getCargo());
				pst.setString(3, novo.getNomeProf());
				pst.setString(4, novo.getSenha());
				pst.setInt(5, novo.getId());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("editado com sucesso");
			
	
	}

	@Override
	public Funcionario  get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM USUARIO where iduser =?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Funcionario  = new Funcionario ();
				Funcionario .setId(rs.getInt("iduser"));
				Funcionario .setNome(rs.getString("nome"));
				Funcionario .setCargo(rs.getString("cargo"));
				Funcionario .setNomeProf(rs.getString("nomeprof"));
				Funcionario .setSenha(rs.getString("senha"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Funcionario ;
		
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM usuario");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return tamanho;
	}

	@Override
	public Funcionario  get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM USUARIO where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Funcionario  = new Funcionario ();
				Funcionario .setId(rs.getInt("iduser"));
				Funcionario .setNome(rs.getString("nome"));
				Funcionario .setCargo(rs.getString("cargo"));
				Funcionario .setNomeProf(rs.getString("nomeprof"));
				Funcionario .setSenha(rs.getString("senha"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return Funcionario ;
		
	}

	
	@Override
	public ArrayList<Funcionario > getLista() {
		return null;
	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(iduser) from usuario ");
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
			pst = con.prepareStatement("SELECT *FROM USUARIO order by IDUSER");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Funcionario  = new Funcionario ();
				Funcionario .setId(rs.getInt("iduser"));
				Funcionario .setNome(rs.getString("nome"));
				Funcionario .setCargo(rs.getString("cargo"));
				Funcionario .setNomeProf(rs.getString("nomeprof"));
				Funcionario .setSenha(rs.getString("senha"));
                TelaGerFunc.addLinha(Funcionario); 

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
			pst = con.prepareStatement("SELECT nome FROM usuario  order by nome");
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
	
	

}
