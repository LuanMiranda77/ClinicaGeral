package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTPaciente;
import MODEL.Paciente;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;

public class TelaHistorico extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private Paciente paciente;
	 private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	 private JComboBox<String> cbxNome;


	public TelaHistorico() {
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.DARK_GRAY);
		setTitle("Consultar - Historico");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setBounds(500, 150, 688, 374);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 11, 217, 22);
		contentPane.add(label);

		JLabel lblNomeDoPaciente = new JLabel("Nome do Paciente:");
		lblNomeDoPaciente.setForeground(Color.BLACK);
		lblNomeDoPaciente.setFont(new Font("Masque", Font.PLAIN, 12));
		lblNomeDoPaciente.setBounds(23, 44, 314, 16);
		contentPane.add(lblNomeDoPaciente);
		
		cbxNome = new JComboBox<String>();
		cbxNome.setFont(new Font("SansSerif", Font.BOLD, 12));
		cbxNome.setModel(new DefaultComboBoxModel<String>(CTPaciente.getListaNomes()));
		cbxNome.setBounds(23, 62, 314, 35);
		cbxNome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addLinha(CTAgendaConsulta.historicoCompleto(""+cbxNome.getSelectedItem()));
				
			}
		});
		
		JButton btnimp = new JButton("Imprimir");
		btnimp.setHorizontalAlignment(SwingConstants.LEFT);
		btnimp.setFont(new Font("Masque", Font.BOLD, 10));
		btnimp.setBounds(349, 59, 103, 35);
		contentPane.add(btnimp);
		btnimp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxNome.getSelectedItem().equals("-")) {
				   JOptionPane.showMessageDialog(null, "Pesquise por uma paciente primeiro","Erro",JOptionPane.ERROR_MESSAGE);
			}
			else {
				try {
					paciente=CTPaciente.get(""+cbxNome.getSelectedItem());
					HistoricoPacientePdf.gerarHistorico(paciente, table);
				} catch (DocumentException e1) {
					  JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
					
				} catch (ParseException e1) {
					 JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					 JOptionPane.showMessageDialog(null, e1.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
		});

		
		table();
		setVisible(true);
	}

	public void table() {

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(19, 106, 646, 216);
		contentPane.add(scrollPane);

		modelo.addColumn("COD"); // coluna 0
		modelo.addColumn("Data"); // coluna 1
		modelo.addColumn("Hora");
		modelo.addColumn("Medico"); // coluna 0
		modelo.addColumn("Procedimento"); // coluna 1
		modelo.addColumn("Status"); // coluna 1
		table.setRowHeight(25);
		
	
		
		contentPane.add(cbxNome);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());

	}

	public void addLinha(ResultSet rs) {
        try {
        	limparTabela();
			while(rs.next()) {
				String hoje = formato.format(rs.getDate("dataConsult"));
				modelo.addRow(new Object[] {rs.getInt("ID"), hoje, rs.getString("hora"), rs.getString("nome"), rs.getString(5),rs.getString(6)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void limparTabela() {
		while(table.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}
		}
}
