package DAOSQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import MODEL.Profissional;

public class RelatorioSql {
	private Connection con = null;
	private PreparedStatement pst = null;

	public ResultSet geraRelatorioConsultaPorMedico(Profissional medico,java.util.Date inicio, java.util.Date fim) throws SQLException {
		con = ConexaoSingleton.getInstance();
		// data inicio
				Date i = new Date(inicio.getTime());
				// data do fim
				Date f = new Date(fim.getTime());
		pst = con.prepareStatement(
				"select c.id,c.dataconsult,c.hora,P.nome,proc.nome,c.valor from agendaconsulta c join paciente p "
						+ "on c.idpaciente=p.idpac join profissional m on c.marmedico=m.matmedico "
						+ "join procedimento proc on c.idproced=proc.idproced where m.matmedico=? AND status=?  AND DATACONSULT BETWEEN ? AND ? ");
		pst.setString(1, medico.getMatricula());
		pst.setString(2, "FINALIZADA");
		pst.setDate(3, i);
		pst.setDate(4, f);
		ResultSet rs = pst.executeQuery();
		return rs;
	}

	public ResultSet geraRelatorioFinanceiroTotalConsulta(java.util.Date inicio, java.util.Date fim)
			throws SQLException {
		con = ConexaoSingleton.getInstance();

		// data inicio
		Date i = new Date(inicio.getTime());
		// data do fim
		Date f = new Date(fim.getTime());

		// consulta
		pst = con.prepareStatement("select c.id,c.dataconsult,c.hora,P.NOME,proc.nome,c.valor"
				+ " from agendaconsulta c join paciente p on c.idpaciente=p.idpac join profissional m on c.marmedico=m.matmedico join procedimento proc on c.idproced=proc.idproced"
				+ " where status=? AND DATACONSULT BETWEEN ? AND ? ");
		pst.setString(1, "FINALIZADA");
		pst.setDate(2, i);
		pst.setDate(3, f);
		
		ResultSet rs = pst.executeQuery();
		return rs;
	}

	public ResultSet geraRelatorioFinanceiroDia() throws SQLException {
		con = ConexaoSingleton.getInstance();
		java.util.Date hoje = new java.util.Date();
		// data inicio
		Date i = new Date(hoje.getTime());
		pst = con.prepareStatement("select c.id,c.dataconsult,c.hora,P.NOME,proc.nome,c.valor" + 
				" from agendaconsulta c join paciente p on c.idpaciente=p.idpac join profissional m on c.marmedico=m.matmedico join procedimento proc on c.idproced=proc.idproced" + 
				" where status=? AND DATACONSULT=? ");
		
		pst.setString(1, "FINALIZADA");
		pst.setDate(2, i);
        //resultado da consulta
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

	public ResultSet geraRelatorioConsultasCancelas() throws SQLException {
		con = ConexaoSingleton.getInstance();
		pst = con.prepareStatement("select c.id,c.dataconsult,c.hora,P.NOME,proc.nome,c.valor" + 
				" from agendaconsulta c join paciente p on c.idpaciente=p.idpac join profissional m on c.marmedico=m.matmedico join procedimento proc on c.idproced=proc.idproced" + 
				" where status=?");
		
		pst.setString(1, "CANCELADA");
        //resultado da consulta
		ResultSet rs = pst.executeQuery();
		return rs;
	}

}
