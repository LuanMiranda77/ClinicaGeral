package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import CONTROL.CTPaciente;
import VIEW.tabela.ModeloTabela;

public class TelaEsolhaPaciente extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private String pes;
	private JLabel lbpes;

	public TelaEsolhaPaciente() {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Pesquisa - Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 589, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(10, 5, 15, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);

		lbpes = new JLabel("F3-pesquisar   -    ESC- Confirmar ");
		lbpes.setForeground(Color.ORANGE);
		lbpes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbpes.setHorizontalAlignment(SwingConstants.CENTER);
		lbpes.setBounds(5, 257, 568, 14);
		
		table();
		setVisible(true);
		pesPaciente();

	}

	@SuppressWarnings("serial")
	public void table() {

		modelo = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table = new JTable(modelo);

		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 10, 568, 246);

		modelo.addColumn("CPF"); // coluna 0
		modelo.addColumn("NOME"); // coluna 1
		contentPane.setLayout(null);
		table.setRowHeight(25);
		contentPane.add(scrollPane);

		contentPane.add(lbpes);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);

		table.getColumnModel().getColumn(0).setCellRenderer(new VIEW.tabela.ColorTable().getCorFundo());
		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F3) {
					pesPaciente();
				}
				else if(e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
					String nome=(String)table.getValueAt(table.getSelectedRow(), 1);
					TelaCadAgenda.esolhaPaciente(nome);
					dispose();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
         ResultSet rs;
		try {
			rs = CTPaciente.getListaPesquisaCli();
			while(rs.next()) {
				modelo.addRow(new Object[] { rs.getString("cpf"), rs.getString("nome") });
			}
		} catch (SQLException e1) {
			new TelaErroLog(e1.getMessage(), "Erro de loanding table", getTitle());
		
		}

	}

	public void pesPaciente() {
		String p= JOptionPane.showInputDialog("Digite o nome do Paciente?");
		if(p!=null) {
			pes =p.toUpperCase();
		boolean band = false;
		for (int i = 0; i < table.getRowCount(); i++) {
			String n = (String) table.getValueAt(i, 1);
			if (n.contains(pes)) {
				band = true;
				table.setRowSelectionInterval(i, i);
				table.requestFocus();
			}
		}
		if (band == false) {
			JOptionPane.showMessageDialog(null, "Paciente nao cadastrado", "Erro de cadastro",
					JOptionPane.ERROR_MESSAGE);
			pesPaciente();
		}

	}
	}
}
