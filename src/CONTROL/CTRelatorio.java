package CONTROL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import DAOSQL.RelatorioSql;
import MODEL.Profissional;

public class CTRelatorio {
	private static RelatorioSql con = new RelatorioSql();;
	
	
	public static ResultSet geraRelatorioConsultaPorMedico(Profissional medico,Date inicio,Date fim) throws SQLException {
		return con.geraRelatorioConsultaPorMedico(medico,inicio, fim);
	}
	
	public static ResultSet geraRelatorioFinanceiroTotalConsulta(Date inicio,Date fim) throws SQLException {
		return con.geraRelatorioFinanceiroTotalConsulta(inicio, fim);
	}
	
	public static ResultSet geraRelatorioFinanceiroDia() throws SQLException {
		
		return con.geraRelatorioFinanceiroDia();
	}
	
	public static ResultSet geraRelatorioConsultasCancelas() throws SQLException {
		return con.geraRelatorioConsultasCancelas();
	}

}
