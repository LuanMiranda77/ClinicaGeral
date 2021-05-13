package RoboTest;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.EspecialidadeXml;
import DAO.FunXml;
import DAO.PacienteXml;
import DAO.ProcedimentoXml;
import DAO.ProfissionalXml;
import DAOSQL.EspecialidadeSql;
import DAOSQL.FunSql;
import DAOSQL.PacienteSql;
import DAOSQL.ProcedimentoSql;
import DAOSQL.ProfissionalSql;
import MODEL.Especialidade;
import MODEL.Funcionario;
import MODEL.Paciente;
import MODEL.Procedimento;
import MODEL.Profissional;

public class Robo {

	public static void main(String[] args) {

		FunXml func = new FunXml();
		ProfissionalXml prof = new ProfissionalXml();
		EspecialidadeXml espc = new EspecialidadeXml();
		  //PacienteXml pac = new PacienteXml();

		ProcedimentoXml proced = new ProcedimentoXml();
		
		for (Profissional f : prof.getLista()) {
			ProfissionalSql c = new ProfissionalSql();
			try {
				c.set(f);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null," Erro :" +e.getMessage());
			}
		}

		for (Funcionario f : func.getLista()) {
			FunSql c = new FunSql();
			c.set(f);
		}

		
		for (Especialidade f : espc.getLista()) {
			EspecialidadeSql c = new EspecialidadeSql();
			c.set(f);
		}
		for (Procedimento f : proced.getlista()) {
			ProcedimentoSql c = new ProcedimentoSql();
			c.set(f);
		}
		/*
		 * for (Paciente f : pac.getLista()) { PacienteSql c = new PacienteSql(); try {
		 * c.set(f); } catch (Exception e) {
		 * JOptionPane.showMessageDialog(null," Erro :" +e.getMessage()); } }
		 */
		JOptionPane.showMessageDialog(null," Copia do banco concluida");
	}

}
