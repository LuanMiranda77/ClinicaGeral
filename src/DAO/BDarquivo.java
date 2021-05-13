package DAO;
import DAO.InterFace.FabFactory;
import DAO.InterFace.IAgendaConsulta;
import DAO.InterFace.IAgendaProf;
import DAO.InterFace.ICEP;
import DAO.InterFace.IConvenio;
import DAO.InterFace.IEmpresa;
import DAO.InterFace.IEspecialidade;
import DAO.InterFace.IFuncionario;
import DAO.InterFace.IHistorico;
import DAO.InterFace.IMedicamento;
import DAO.InterFace.IPaciente;
import DAO.InterFace.IProcedimento;
import DAO.InterFace.IProfissional;



public class BDarquivo implements FabFactory {

	public IPaciente fabricarPaciente() {
		return  new PacienteXml();
	}

	public  IFuncionario fabricarFun() {
		return new FunXml();
	}


	@Override
	public IEmpresa fabricarEmpresa() {
		return null;
	}

	@Override
	public IAgendaProf fabricarAgendaProf() {
		// TODO Auto-generated method stub
		return new AgendaProfXml();
	}

	@Override
	public IAgendaConsulta fabricarAgendaCon() {
		// TODO Auto-generated method stub
		return new AgendaConsultaXml();
	}

	@Override
	public IConvenio fabricarConv() {
		// TODO Auto-generated method stub
		return new ConvenioXml();
	}

	@Override
	public IEspecialidade fabricarEspecialidade() {
		// TODO Auto-generated method stub
		return new EspecialidadeXml();
	}

	@Override
	public IProcedimento fabricarProcedimento() {
		// TODO Auto-generated method stub
		return new ProcedimentoXml();
	}

	@Override
	public IProfissional fabricarProfissional() {
		// TODO Auto-generated method stub
		return new ProfissionalXml();
	}
	
	@Override
	public ICEP fabricarCEP() {
		// TODO Auto-generated method stub
		return new CEPXml();
	}

	@Override
	public IMedicamento fabricarMedicamento() {
		// TODO Auto-generated method stub
		return new MedicamentoXml();
	}

	@Override
	public IHistorico fabricarHistorico() {
		// TODO Auto-generated method stub
		return null;
	}
	
	




}
