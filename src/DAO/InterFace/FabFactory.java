package DAO.InterFace;

public interface FabFactory {
	
	public IPaciente fabricarPaciente();
	
	public IFuncionario fabricarFun();
	
	public IEmpresa fabricarEmpresa();
	
	public IAgendaConsulta fabricarAgendaCon();
	
	public IAgendaProf fabricarAgendaProf();
	
	public IConvenio fabricarConv();
	
	public IEspecialidade fabricarEspecialidade();
	
	public IProcedimento fabricarProcedimento();
	
	public IProfissional fabricarProfissional();

	public 	ICEP fabricarCEP();
	
	public IMedicamento fabricarMedicamento();
	
	public IHistorico fabricarHistorico();

	
	
}
