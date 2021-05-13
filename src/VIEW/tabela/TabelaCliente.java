package VIEW.tabela;

import java.awt.Font;
import java.text.SimpleDateFormat;

/**
 * 
 * @author luan
 * 
 */
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import CONTROL.CTPaciente;
import MODEL.Paciente;

public class TabelaCliente extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679019858685912438L;
	private static DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("serial")
	public void gerarTabela(JFrame janela) {

		modelo = new DefaultTableModel() {
		
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	};

		// modelo da tabela
		modelo.addColumn("Prontuario"); // coluna 0
		modelo.addColumn("CPF");
		modelo.addColumn("RG"); // coluna 1
		modelo.addColumn("Nome"); // coluna 2
		modelo.addColumn("Celular");
		modelo.addColumn("CEP");// coluna 3
		modelo.addColumn("Rua"); // coluna 4
		modelo.addColumn("Num"); // coluna 5
		modelo.addColumn("Bairro"); // coluna 6
		modelo.addColumn("Cidade"); // coluna 7
		modelo.addColumn("UF"); // coluna 10//coluna 8
		modelo.addColumn("E-mail");
		modelo.addColumn("Data Nasc");
		modelo.addColumn("Peso");
		modelo.addColumn("Altura");
		modelo.addColumn("Sexo");
		modelo.addColumn("Est.Civil");// coluna 9
		modelo.addColumn("Cor");// coluna 9
		modelo.addColumn("Grupo sangue");// coluna 9
		modelo.addColumn("Profissoa");// coluna 9
		modelo.addColumn("Convenio");// coluna 9
		modelo.addColumn("Nº Cartão");
		modelo.addColumn("Validade card");
		modelo.addColumn("Whatsapp");
		modelo.addColumn("Telf-fixo");
		modelo.addColumn("Observações");

		tabela = new JTable(modelo);

		tabela.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(280);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(8).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(9).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(10).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(11).setPreferredWidth(200);
		tabela.getColumnModel().getColumn(12).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(16).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(18).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(19).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(20).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(21).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(22).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(23).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(24).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(25).setPreferredWidth(300);
		tabela.setRowHeight(25);
		tabela.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());

		contener = new JScrollPane(tabela);
		contener.setBounds(10, 100, 1000, 450);
		tabela.setBorder(UIManager.getBorder("FormattedTextField.border"));
		tabela.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		CTPaciente.preencherLista();
		
		
		

		janela.add(contener);
		tabela.repaint();

	}
	
	

	public int getRowSelect() {
		return tabela.getSelectedRowCount();
	}

	public int selecionarLinha() {
		return tabela.getSelectedRow();
	}

	public void limparTabela(int linha) {
		modelo.removeRow(linha);
	}

	public void limparTabela2() {
		while (tabela.getModel().getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	public int retornarId() {
		try {
			return (int) tabela.getValueAt(tabela.getSelectedRow(), 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public boolean filtroNome(String n) {
		boolean band = false;
		for (Paciente p : CTPaciente.getLista()) {
			String nsc = null;
			String val = null;
			if (p.getDataNasc() != null && p.getValidadeCrad() != null) {
				nsc = formato.format(p.getDataNasc());
				val = formato.format(p.getValidadeCrad());
			}
			if (p.getNome().contains(n)) {
				limparTabela2();
				band = true;
				modelo.addRow(new Object[] { p.getId(),

						p.getCPF(), p.getRg(), p.getNome(), p.getCelular1(), p.getCep(), p.getRua(), p.getNum(),
						p.getBairro(), p.getCidade(), p.getUf(), p.getEmil(), nsc, p.getPeso(), p.getAltura(),
						p.getSexo(), p.getEstadoCivil(), p.getCor(), p.getTipoSangue(), p.getProfissao(),
						p.getConvenio(), p.getNumCartao(), val, p.getCelular2(), p.getTelFixo(), p.getObs() });
			}
		}

		return band;
	}

	public boolean selecionarLinha(String n) {
		boolean band = false;
		for (int i = 0; i < tabela.getRowCount(); i++) {
			String nome = (String) tabela.getValueAt(i, 3);
			if (nome.contains(n)) {
				band = true;
				tabela.setRowSelectionInterval(i, i);
				tabela.requestFocus();
			}
		}

		return band;
	}

	public static void adcionarLinha(Paciente p) {
		String nsc = null;
		String val = null;
		if (p.getDataNasc() != null) {
			nsc = formato.format(p.getDataNasc());

		}
		if (p.getValidadeCrad() != null) {
			val = formato.format(p.getValidadeCrad());
		}
		modelo.addRow(new Object[] { p.getId(), p.getCPF(), p.getRg(), p.getNome(), p.getCelular2(), p.getCep(),
				p.getRua(), p.getNum(), p.getBairro(), p.getCidade(), p.getUf(), p.getEmil(), nsc, p.getPeso(),
				p.getAltura(), p.getSexo(), p.getEstadoCivil(), p.getCor(), p.getTipoSangue(), p.getProfissao(),
				p.getConvenio(), p.getNumCartao(), val, p.getCelular1(), p.getTelFixo(), p.getObs() });

	}
}
