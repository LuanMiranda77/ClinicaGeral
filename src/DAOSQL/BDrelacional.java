package DAOSQL;

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

public class BDrelacional implements FabFactory {

	@Override
	public IPaciente fabricarPaciente() {
		return new PacienteSql();
	}

	@Override
	public IFuncionario fabricarFun() {
		return new FunSql();
	}

	@Override
	public IEmpresa fabricarEmpresa() {
		return null;
	}

	@Override
	public IAgendaConsulta fabricarAgendaCon() {
		return new AgendaConsultaSql();
	}

	@Override
	public IAgendaProf fabricarAgendaProf() {
		// TODO Auto-generated method stub
		return new AgendaProfSql();
	}

	@Override
	public IConvenio fabricarConv() {
		// TODO Auto-generated method stub
		return new ConvenioSql();
	}

	@Override
	public IEspecialidade fabricarEspecialidade() {
		// TODO Auto-generated method stub
		return new EspecialidadeSql();
	}

	@Override
	public IProcedimento fabricarProcedimento() {
		// TODO Auto-generated method stub
		return new ProcedimentoSql();
	}

	@Override
	public IProfissional fabricarProfissional() {
		// TODO Auto-generated method stub
		return new ProfissionalSql();
	}

	@Override
	public ICEP fabricarCEP() {
		return new CepSql();
	}

	@Override
	public IMedicamento fabricarMedicamento() {
		// TODO Auto-generated method stub
		return new MedicamentoSql();
	}

	@Override
	public IHistorico fabricarHistorico() {
		// TODO Auto-generated method stub
		return new HistoricoPacienteSql();
	}



	

	


}
