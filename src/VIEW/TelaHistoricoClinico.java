package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTHistorico;
import CONTROL.CTMedicamento;
import CONTROL.CTPaciente;
import CONTROL.CTProfissional;
import MODEL.AgendaConsulta;
import MODEL.Historico;
import MODEL.Medicamento;
import MODEL.Paciente;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;

public class TelaHistoricoClinico extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuso;
	private JTextField txtquant;
	private JTable table;
	private static DefaultTableModel modelo;
	private ArrayList<Medicamento> listaMed = new ArrayList<>();
	private JComboBox<String> cbxremed;
	@SuppressWarnings("unused")
	private Paciente paciente;
	private JComboBox<String> cbxvia;
	private CTAgendaConsulta conx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaConsulta a = new AgendaConsulta();
					a.setData(new Date());
					a.setIdPaciente(1);
					new TelaHistoricoClinico(a);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaHistoricoClinico(AgendaConsulta consulta) {
		setTitle("Atendimento - Consultas");
		setBackground(new Color(255, 255, 255));
		ModeloTabela.ativarModeloTela(this);
		pegarResolucao();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(8, 4, 4, 4, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 387, 596, 297);
		contentPane.add(scrollPane);

		paciente = CTPaciente.get(consulta.getIdPaciente());
		JTextArea txtH_novo = new JTextArea();
		txtH_novo.setText("Data da Consulta: " + DateFormat.getDateInstance(DateFormat.MEDIUM).format(consulta.getData())+"\n\r");
	
		txtH_novo.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 15));
		txtH_novo.setLineWrap(true);
		scrollPane.setColumnHeaderView(txtH_novo);

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(6, 13, 186, 16);
		contentPane.add(label);

		JLabel lblHistoricoClnico = new JLabel("Historico Cl\u00EDnico:");
		lblHistoricoClnico.setForeground(Color.BLACK);
		lblHistoricoClnico.setFont(new Font("Masque", Font.PLAIN, 12));
		lblHistoricoClnico.setBounds(19, 41, 139, 16);
		contentPane.add(lblHistoricoClnico);

		JLabel lblSelecioneOsMedicamentos = new JLabel("Selecione os Medicamentos");
		lblSelecioneOsMedicamentos.setForeground(new Color(0, 0, 0));
		lblSelecioneOsMedicamentos.setFont(new Font("Masque", Font.PLAIN, 13));
		lblSelecioneOsMedicamentos.setBounds(640, 41, 272, 16);
		contentPane.add(lblSelecioneOsMedicamentos);

		cbxremed = new JComboBox<String>();
		cbxremed.setModel(new DefaultComboBoxModel<String>(CTMedicamento.getListaNomes()));
		cbxremed.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxremed.setBounds(640, 58, 420, 30);
		contentPane.add(cbxremed);

		cbxvia = new JComboBox<String>();
		cbxvia.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-", "USO ORAL","USO SUBLINGUAL", "USO RETAL", "USO INTRAD\u00C9RMICA", "USO SUBCUT\u00C2NEA ",
						"USO INTRAMUSCULAR", "USO ENDOVENOSA", "USO RESPIRAT\u00D3RIA", "USO OCULAR", "USO INTRANASAL ", "USO AURICULAR" }));
		cbxvia.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxvia.setBounds(1072, 116, 187, 30);
		contentPane.add(cbxvia);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon("icon\\sal.png"));
		btnAdicionar.setBounds(967, 158, 124, 30);
		contentPane.add(btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String med = "\nMedicamentos Prescrito:\n";
				if (!cbxremed.getSelectedItem().equals("-") && !txtquant.getText().equals("")
						&& !txtuso.getText().equals("") && !cbxvia.getSelectedItem().equals("-")) {
					String m = (String) cbxremed.getSelectedItem();
					String[] lista = m.split(" - ");
					Medicamento novo = CTMedicamento.getMedicamento(lista[0]);
					novo.setQuant(txtquant.getText());
					novo.setModoUso(txtuso.getText());
					novo.setVia("" + cbxvia.getSelectedItem());
					if (listaMed.size() != 0) {
						med = "";
					}
					addProced(novo);

					txtH_novo.setText(txtH_novo.getText()+ med +"Modo de Uso: "+novo.getModoUso().toUpperCase() + " - Via Aplic: " + novo.getVia()
					+"\nRemedio: "+ novo.getDesc().toUpperCase() + " - " + (int)novo.getmg() + "mg - " + "Quant: "+novo.getQuant().toUpperCase()+ "\n" );
					limparCampo();
				} else {
					JOptionPane.showMessageDialog(null, "Erro selecione o medicamento!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon("icon\\del.png"));
		btnRemover.setBounds(1103, 158, 128, 30);
		contentPane.add(btnRemover);
		btnRemover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();
				if (linha < 0)
					JOptionPane.showMessageDialog(null, "Erro selecione um item para remover!", "Tela informativa",
							JOptionPane.ERROR_MESSAGE);
				else
					modelo.removeRow(linha);
				listaMed.remove(linha);

			}
		});

		JLabel lblModoDeUso = new JLabel("Modo de uso:");
		lblModoDeUso.setForeground(Color.BLACK);
		lblModoDeUso.setFont(new Font("Masque", Font.PLAIN, 11));
		lblModoDeUso.setBounds(640, 100, 229, 16);
		contentPane.add(lblModoDeUso);

		txtuso = new JTextField();
		txtuso.setForeground(Color.BLACK);
		txtuso.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtuso.setColumns(10);
		txtuso.setBounds(640, 116, 420, 30);
		contentPane.add(txtuso);

		JLabel lblViaDeAplicao = new JLabel("Via de aplica\u00E7\u00E3o:");
		lblViaDeAplicao.setForeground(Color.BLACK);
		lblViaDeAplicao.setFont(new Font("Masque", Font.PLAIN, 11));
		lblViaDeAplicao.setBounds(1072, 100, 134, 16);
		contentPane.add(lblViaDeAplicao);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Masque", Font.PLAIN, 11));
		lblQuantidade.setBounds(1072, 41, 95, 16);
		contentPane.add(lblQuantidade);

		txtquant = new JTextField();
		txtquant.setForeground(Color.BLACK);
		txtquant.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtquant.setColumns(10);
		txtquant.setBounds(1072, 57, 187, 30);
		contentPane.add(txtquant);

		JButton btnFinalzar = new JButton("Finalzar");
		btnFinalzar.setIcon(new ImageIcon("icon\\sal.png"));
		btnFinalzar.setFont(new Font("Marcellus SC", Font.BOLD, 13));
		btnFinalzar.setBounds(1008, 630, 120, 42);
		contentPane.add(btnFinalzar);
		btnFinalzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Deseja relamente finalizar?", "Tela de confirmação",
						JOptionPane.YES_NO_OPTION);
				
				if (op == 0) {
					conx= new CTAgendaConsulta();
					conx.removeConsultFinalizada(consulta.getId());
					consulta.setStatus("FINALIZADA");
					CTAgendaConsulta.update(consulta);
					Historico h = new Historico();
					h.setPaciente_CPF(""+consulta.getIdPaciente());
					h.setMatMedico(consulta.getMatMedico());
					h.setHistorio(txtH_novo.getText());
					h.setDateHist(consulta.getData());
					CTHistorico.insert(h);
					

					int op2 = JOptionPane.showConfirmDialog(null, "Deseja marcar um retorno?", "Tela de confirmação",
							JOptionPane.YES_NO_OPTION);
					try {
						dispose();
						new TelaLoanding();
						ReceitaPdf.gerarHistorico(consulta, listaMed);
						Profissional prof = CTProfissional.getMedico(consulta.getMatMedico());
						new TelaConsulta(prof);
					} catch (DocumentException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de documento aberto", getTitle());
					} catch (ParseException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de inesperado", getTitle());
					} catch (IOException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de IoExpec", getTitle());
					}
					try {
					if (op2 == 0) {
						dispose();
						ReceitaPdf.gerarHistorico(consulta, listaMed);
						new TelaGerAgenda();
					}
					} catch (DocumentException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de documento aberto", getTitle());
					} catch (ParseException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de inesperado", getTitle());
					} catch (IOException e1) {
						new TelaErroLog(e1.getMessage(), "Erro de IoExpec", getTitle());
					}

				}

			}
		});

		JButton button_1 = new JButton("Cancelar");
		button_1.setIcon(new ImageIcon("icon\\del.png"));
		button_1.setFont(new Font("Marcellus SC", Font.BOLD, 14));
		button_1.setBounds(1128, 630, 120, 42);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		table();
		preencherHistoria(CTHistorico.getHistoricoIdpac(consulta.getIdPaciente()));
		setVisible(true);
		txtH_novo.requestFocus();
		repaint();
	}
	
	
 private  static JTextArea txtHistoriaAntiga;

	public void table() {
		//// modelo da table;
		modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Descição");
		modelo.addColumn("Quant");
		modelo.addColumn("Modo uso");
		modelo.addColumn("Via Aplicação");

		table = new JTable(modelo);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAlignmentX(CENTER_ALIGNMENT);
		table.getColumnModel().getColumn(0).setCellRenderer(new VIEW.tabela.ColorTable().getCorFundo());

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(640, 206, 619, 252);
		contentPane.add(scrollPane_1);

		JLabel lblNovoHistoria = new JLabel("Novo Historia:");
		lblNovoHistoria.setForeground(Color.BLACK);
		lblNovoHistoria.setFont(new Font("Masque", Font.PLAIN, 12));
		lblNovoHistoria.setBounds(19, 369, 272, 16);
		contentPane.add(lblNovoHistoria);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 60, 596, 297);
		contentPane.add(scrollPane);
		
		txtHistoriaAntiga = new JTextArea();
		txtHistoriaAntiga.setFont(new Font("SansSerif", Font.BOLD, 16));
		scrollPane.setViewportView(txtHistoriaAntiga);
		txtHistoriaAntiga.setEditable(false);
	
		

	}
public void preencherHistoria(String text) {
		txtHistoriaAntiga.setText(text);
	}

	public void limpatable() {
		while (table.getModel().getRowCount() > 0) {
			modelo.removeRow(0);
		}
	}

	public void salvar() {

	}

	public void addProced(Medicamento novo) {
		listaMed.add(novo);
		modelo.addRow(new Object[] { novo.getId(), novo.getDesc() + " - " + novo.getmg() + "mg", novo.getQuant(),
				novo.getModoUso(), novo.getVia() });
	}

	public void limparCampo() {
		cbxremed.setSelectedItem("-");
		txtquant.setText("");
		txtuso.setText("");
		cbxvia.setSelectedItem("-");

	}
}
