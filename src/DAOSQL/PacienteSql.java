package DAOSQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import CONTROL.CTConvenio;
import DAO.InterFace.IPaciente;
import MODEL.Paciente;
import VIEW.TelaErroLog;
import VIEW.tabela.TabelaCliente;

/**
 * 
 * @author agemiro
 *
 */
public class PacienteSql implements IPaciente{
	private Connection con = null;
	PreparedStatement pst = null;
	private Paciente paciente;
	private ConvenioSql convsql = new ConvenioSql();
	
	
	public PacienteSql() {
		paciente = new Paciente();
	}

	@Override
	public void set(Paciente novo) throws Exception {
		//codigo pra conexao de banco de daos pSostgre
		

			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement("INSERT INTO PACIENTE (cpf,nome,dtnasc,rg,sexo,altura,peso,idade,"
						+ "										  estcivil,tiposangue,prof,cor,idconv,numcartao,"
						+ "                 validadecard,cep,rua,num,comp,bairro,cidade,uf,celular,telfixo,recado,"
						+ "                 email,obs,foto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pst.setString(1, novo.getCPF());
				pst.setString(2, novo.getNome());
				Date d1 = null;
				if(novo.getDataNasc()!=null) {
				 d1= new Date (novo.getDataNasc().getTime());
				}
				pst.setDate(3, d1);
				pst.setString(4, novo.getRg());
				pst.setString(5, novo.getSexo());
				pst.setFloat(6, novo.getAltura());
				pst.setFloat(7, novo.getPeso());
				pst.setInt(8, novo.getIdade());
				pst.setString(9, novo.getEstadoCivil());
				pst.setString(10, novo.getTipoSangue());
				pst.setString(11, novo.getProfissao());
				pst.setString(12, novo.getCor());
				int idConv = convsql.get(novo.getConvenio()).getId();
				pst.setInt(13, idConv);
				pst.setString(14, novo.getNumCartao());
				Date d2 = null;
				if(novo.getValidadeCrad()!=null) {
				  d2= new Date (novo.getValidadeCrad().getTime());
				}
				pst.setDate(15,d2);
			
				pst.setString(16, novo.getCep());
				pst.setString(17, novo.getRua());
				pst.setString(18, novo.getNum());
				pst.setString(19, novo.getComplemento());
				pst.setString(20, novo.getBairro());
				pst.setString(21, novo.getCidade());
				pst.setString(22, novo.getUf());
				pst.setString(23, novo.getCelular1());
				pst.setString(24, novo.getTelFixo());
				pst.setString(25, novo.getCelular2());
				pst.setString(26, novo.getEmil());
				pst.setString(27, novo.getObs());
				pst.setString(28, novo.getFoto());
				pst.execute();
			}catch (SQLException e) {
				new TelaErroLog(e.getMessage(), "erro no insert", "");
	
				
			}
			
			
	}
	public void remover(String idpac){
		//codigo pra conexao de banco de daos postgre
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("delete from paciente cascade where idpac= ?");
			pst.setString(1, idpac);
			pst.execute();
		}catch(SQLException e) {
			
		}
	}

	@Override
	public void update(Paciente novo) {
			String sql = "UPDATE paciente SET cpf=?,nome=?,dtNasc=?,rg=?,sexo=?,altura=?,peso=?,IDADE=?,ESTCIVIL=?,tiposangue=?,"
					+ "prof=?,cor=?,idconv=?,numcartao=?,validadecard=?,CEP=?,rua=?,num=?,comp=?,bairro=?,cidade=?,uf=?,celular=?,"+
					"telfixo=?,recado=?,EMAIL=?,obs=?,FOTO=? where idPAC=?";
			con = ConexaoSingleton.getInstance();
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, novo.getCPF());
				pst.setString(2, novo.getNome());
				Date d1 = null;
				if(novo.getDataNasc()!=null) {
				 d1= new Date (novo.getDataNasc().getTime());
				}
				pst.setDate(3, d1);
				pst.setString(4, novo.getRg());
				pst.setString(5, novo.getSexo());
				pst.setFloat(6, novo.getAltura());
				pst.setFloat(7, novo.getPeso());
				pst.setInt(8, novo.getIdade());
				pst.setString(9, novo.getEstadoCivil());
				pst.setString(10, novo.getTipoSangue());
				pst.setString(11, novo.getProfissao());
				pst.setString(12, novo.getCor());
				int idConv = convsql.get(novo.getConvenio()).getId();
				pst.setInt(13, idConv);
				pst.setString(14, novo.getNumCartao());
				Date d2 = null;
				if(novo.getValidadeCrad()!=null) {
				  d2= new Date (novo.getValidadeCrad().getTime());
				}
				pst.setDate(15,d2);
			
				pst.setString(16, novo.getCep());
				pst.setString(17, novo.getRua());
				pst.setString(18, novo.getNum());
				pst.setString(19, novo.getComplemento());
				pst.setString(20, novo.getBairro());
				pst.setString(21, novo.getCidade());
				pst.setString(22, novo.getUf());
				pst.setString(23, novo.getCelular1());
				pst.setString(24, novo.getTelFixo());
				pst.setString(25, novo.getCelular2());
				pst.setString(26, novo.getEmil());
				pst.setString(27, novo.getObs());
				pst.setString(28, novo.getFoto());
				
				pst.setInt(29, novo.getId());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				}
			
	
	}

	@Override
	public Paciente get(int cod) {
	
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM paciente where idpac=?");
			pst.setInt(1, cod);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				paciente.setConvenio(rs.getString("idconv"));
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));

			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return paciente;
		
	}

	public Paciente lista() {
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * FROM paciente");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				paciente.setConvenio(rs.getString("idconv"));
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));
				
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return paciente;
	}

	@Override
	public int getTamanho() {
		int tamanho = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT COUNT(*) FROM PACIENTE ");
			ResultSet rs= pst.executeQuery();
			rs.next();
			tamanho=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tamanho;
	}

	@Override
	public Paciente get(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM paciente where nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				paciente.setConvenio(rs.getString("idconv"));
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return paciente;
		
	}

	@Override
	public void remove(int id) {
		//codigo pra conexao de banco de daos postgre
				try {
					con = ConexaoSingleton.getInstance();
					pst = con.prepareStatement("DELETE FROM paciente WHERE idpac = ?");
					pst.setInt(1, id);
					pst.execute();
				}catch(SQLException e) {
					
				}
	}

	@Override
	public ArrayList<Paciente> getLista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResultSet getListaPesquisaCliente() throws SQLException {
		con = ConexaoSingleton.getInstance();
		pst = con.prepareStatement("SELECT cpf, nome FROM paciente order by nome");
		ResultSet rs=pst.executeQuery();
		return rs;
	}

	@Override
	public int getId() {
		int id = 0;
		con = ConexaoSingleton.getInstance();
		try {
			pst = con.prepareStatement("SELECT max(idpac) from paciente");
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
			pst = con.prepareStatement("SELECT *FROM paciente order by idpac");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString(2));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				String conv = CTConvenio.getConvId(rs.getInt("idconv")).getNome();
				paciente.setConvenio(conv);
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));
				TabelaCliente.adcionarLinha(paciente);
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public String [] preencherListaNomes() {
		String [] lista= new String[getTamanho()+1];
		lista[0]="-";
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT nome FROM paciente order by nome");
			ResultSet rs=pst.executeQuery();
			int cont=1;
			while(rs.next()) {
				lista[cont]=rs.getString("nome");
				cont++;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
	
	public String [] ListaNomesEmail() {
		ArrayList<String> lista= new ArrayList<>();
		String [] p  = null;
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("select nome from paciente where email<>'' order by nome");
			ResultSet rs=pst.executeQuery();
			
			lista.add("-");
			while(rs.next()) {
				lista.add(rs.getString("nome"));
				
			}
			p = new String[lista.size()];

			for(int i=0; i<lista.size();i++) {
				p[i]=lista.get(i).toString();

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	
	public String[] ListaNomesZap() {
		ArrayList<String> lista= new ArrayList<>();
		String [] p  = null;
		
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("select nome from paciente where celular<>'' order by nome");
			ResultSet rs=pst.executeQuery();
			
			lista.add("-");
			while(rs.next()) {
				lista.add(rs.getString("nome"));
				
			}
			p = new String[lista.size()];

			for(int i=0; i<lista.size();i++) {
				p[i]=lista.get(i).toString();

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	
	public Paciente getPacienteCPF(String CPF) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT *FROM paciente where cpf=?");
			pst.setString(1, CPF);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				paciente.setConvenio(rs.getString("idconv"));
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return paciente;
		
	}

	public Paciente getPacienteNomeLike(String nome) {
		try {
			con = ConexaoSingleton.getInstance();
			pst = con.prepareStatement("SELECT * from paciente where nome like nome=?");
			pst.setString(1, nome);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				paciente = new Paciente();
				paciente.setId(rs.getInt("idpac"));
				paciente.setCPF(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setDataNasc(rs.getDate("dtnasc"));
				paciente.setRg(rs.getString("rg"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setAltura(rs.getFloat("altura"));
				paciente.setPeso(rs.getFloat("peso"));
				paciente.setIdade(rs.getInt("idade"));
				paciente.setEstadoCivil(rs.getString("estcivil"));
				paciente.setTipoSangue(rs.getString("tiposangue"));
				paciente.setProfissao(rs.getString("prof"));
				paciente.setCor(rs.getString("cor"));
				paciente.setConvenio(rs.getString("idconv"));
				paciente.setNumCartao(rs.getString("numcartao"));
				paciente.setValidadeCrad(rs.getDate("validadecard"));
				paciente.setCep(rs.getString("cep"));
				paciente.setRua(rs.getString("rua"));
				paciente.setNum(rs.getString("num"));
				paciente.setComplemento(rs.getString("comp"));
				paciente.setBairro(rs.getString("bairro"));
				paciente.setCidade(rs.getString("cidade"));
				paciente.setUf(rs.getString("uf"));
				paciente.setCelular1(rs.getString("celular"));
				paciente.setTelFixo(rs.getString("telfixo"));
				paciente.setCelular2(rs.getString("recado"));
				paciente.setEmil(rs.getString("email"));
				paciente.setObs(rs.getString("obs"));
				paciente.setFoto(rs.getString("foto"));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return paciente;
		
	}

}
