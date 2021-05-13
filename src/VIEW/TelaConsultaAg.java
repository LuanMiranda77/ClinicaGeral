package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import com.twilio.rest.preview.hostedNumbers.AuthorizationDocumentUpdater;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTPaciente;
import CONTROL.CTProcedimento;
import MODEL.AgendaConsulta;
import MODEL.ListaChegada;
import MODEL.Profissional;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;
import VIEW.tabela.TabelaCliente;
import javax.swing.border.TitledBorder;

public class TelaConsultaAg extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JTable table;
	private JTable tableOrdem;
	private DefaultTableModel modelo1;
	private DefaultTableModel modeloOrd;
	private JCalendar calendar;
	private JTextField txtpaciente;
	private JTextField txtproced;
	private JTextField txtdata;
	private JTextField txthora;
	private JLabel lbDesc;
	private JLabel lblValorDaConsulta;
	private JLabel lbvalor;
	private DecimalFormat convert = new DecimalFormat("0.00");
	private AgendaConsulta consulta;
	private JTextArea txtobs;
	private JLabel lblValorText;
	private Profissional medicog;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private CTAgendaConsulta conx;
	private int q=0;
	private float t=0;

	private int num = 0;

	public TelaConsultaAg(Profissional medico) {
		setTitle("Gerenciamento - Consultas");
		setBackground(Color.DARK_GRAY);
		ModeloTabela.ativarModeloTela(this);
		pegarResolucao();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(8, 4, 4, 4, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		medicog = medico;

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 11, 186, 16);
		contentPane.add(label);

		JLabel lblCodConsulta = new JLabel("Cod Consulta:");
		lblCodConsulta.setForeground(Color.BLACK);
		lblCodConsulta.setFont(new Font("Masque", Font.PLAIN, 12));
		lblCodConsulta.setBounds(23, 37, 139, 16);
		contentPane.add(lblCodConsulta);

		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setForeground(Color.GREEN);
		txtid.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtid.setColumns(10);
		txtid.setBounds(20, 53, 114, 30);
		contentPane.add(txtid);

		calendar = new JCalendar();
		calendar.setBounds(445, 53, 405, 278);
		contentPane.add(calendar);
		calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				limparCampo();
				addLinhaDia(calendar.getDate(),medico);
			}
		});

		JLabel lblNomeDoPaciente = new JLabel("Nome do Paciente:");
		lblNomeDoPaciente.setForeground(Color.BLACK);
		lblNomeDoPaciente.setFont(new Font("Masque", Font.PLAIN, 12));
		lblNomeDoPaciente.setBounds(149, 37, 170, 16);
		contentPane.add(lblNomeDoPaciente);

		txtpaciente = new JTextField();
		txtpaciente.setEditable(false);
		txtpaciente.setForeground(new Color(0, 51, 153));
		txtpaciente.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtpaciente.setColumns(10);
		txtpaciente.setBounds(146, 53, 277, 30);
		contentPane.add(txtpaciente);

		txtproced = new JTextField();
		txtproced.setEditable(false);
		txtproced.setForeground(new Color(0, 0, 102));
		txtproced.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtproced.setColumns(10);
		txtproced.setBounds(23, 108, 400, 30);
		contentPane.add(txtproced);

		JLabel lblProcedimento = new JLabel("Procedimento:");
		lblProcedimento.setForeground(Color.BLACK);
		lblProcedimento.setFont(new Font("Masque", Font.PLAIN, 12));
		lblProcedimento.setBounds(26, 92, 139, 16);
		contentPane.add(lblProcedimento);

		JLabel lblCalendarioDeAgenda = new JLabel("Calendario da Consulta");
		lblCalendarioDeAgenda.setForeground(new Color(0, 102, 51));
		lblCalendarioDeAgenda.setFont(new Font("Masque", Font.PLAIN, 13));
		lblCalendarioDeAgenda.setBounds(445, 25, 228, 16);
		contentPane.add(lblCalendarioDeAgenda);

		JLabel lblObservaesDaConsulta = new JLabel("Observa\u00E7\u00F5es da consulta:");
		lblObservaesDaConsulta.setForeground(new Color(204, 0, 0));
		lblObservaesDaConsulta.setFont(new Font("Masque", Font.PLAIN, 12));
		lblObservaesDaConsulta.setBounds(23, 208, 218, 16);
		contentPane.add(lblObservaesDaConsulta);

		txtobs = new JTextArea();
		txtobs.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtobs.setForeground(new Color(204, 0, 0));
		txtobs.setBounds(26, 223, 397, 108);
		contentPane.add(txtobs);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Masque", Font.PLAIN, 12));
		lblData.setBounds(26, 150, 139, 16);
		contentPane.add(lblData);

		txtdata = new JTextField();
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setForeground(new Color(0, 0, 102));
		txtdata.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtdata.setEditable(false);
		txtdata.setColumns(10);
		txtdata.setBounds(23, 166, 114, 30);
		contentPane.add(txtdata);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Masque", Font.PLAIN, 12));
		lblHora.setBounds(180, 150, 139, 16);
		contentPane.add(lblHora);

		txthora = new JTextField();
		txthora.setHorizontalAlignment(SwingConstants.CENTER);
		txthora.setForeground(new Color(0, 0, 102));
		txthora.setFont(new Font("SansSerif", Font.BOLD, 15));
		txthora.setEditable(false);
		txthora.setColumns(10);
		txthora.setBounds(177, 166, 105, 30);
		contentPane.add(txthora);

		lbDesc = new JLabel("0,00");
		lbDesc.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbDesc.setForeground(new Color(255, 255, 255));
		lbDesc.setBackground(new Color(102, 0, 0));
		lbDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lbDesc.setBounds(873, 68, 228, 108);
		contentPane.add(lbDesc);
		lbDesc.setOpaque(true);

		lblValorDaConsulta = new JLabel("Valor da Consulta");
		lblValorDaConsulta.setForeground(new Color(0, 102, 51));
		lblValorDaConsulta.setFont(new Font("Masque", Font.PLAIN, 13));
		lblValorDaConsulta.setBounds(873, 25, 228, 16);
		contentPane.add(lblValorDaConsulta);

		lbvalor = new JLabel("0,00");
		lbvalor.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbvalor.setForeground(Color.BLACK);
		lbvalor.setBackground(new Color(0, 102, 255));
		lbvalor.setHorizontalAlignment(SwingConstants.CENTER);
		lbvalor.setBounds(873, 223, 228, 108);
		contentPane.add(lbvalor);
		lbvalor.setOpaque(true);

		JLabel lblDesconto = new JLabel("Desconto:");
		lblDesconto.setForeground(Color.BLACK);
		lblDesconto.setFont(new Font("Masque", Font.PLAIN, 10));
		lblDesconto.setBounds(873, 53, 139, 16);
		contentPane.add(lblDesconto);

		lblValorText = new JLabel("Valor:");
		lblValorText.setForeground(Color.BLACK);
		lblValorText.setFont(new Font("Masque", Font.PLAIN, 10));
		lblValorText.setBounds(873, 207, 139, 16);
		contentPane.add(lblValorText);

		JLabel lblListaDasConsultas = new JLabel("Lista das Consultas Agendadas\r\n");
		lblListaDasConsultas.setForeground(new Color(0, 102, 51));
		lblListaDasConsultas.setFont(new Font("Masque", Font.PLAIN, 13));
		lblListaDasConsultas.setBounds(10, 343, 299, 16);
		contentPane.add(lblListaDasConsultas);
		table(medico);

		setVisible(true);
		repaint();
	}

	@SuppressWarnings("serial")
	public void table(Profissional m) {
		//// modelo da tabela;

		modelo1 = new DefaultTableModel() {
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		modelo1.addColumn("COD");
		modelo1.addColumn("DATA");
		modelo1.addColumn("HORA");
		modelo1.addColumn("PACIENTE");
		modelo1.addColumn("PROCEDIMENTO");
		modelo1.addColumn("DESC");
		modelo1.addColumn("VALOR");
		modelo1.addColumn("STATUS");
		modelo1.addColumn("");

		table = new JTable(modelo1);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(45);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());
		table.setRowHeight(0, 40);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(0);

	    addLinhaDia(new Date(), m);
		
	
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 361, 1078, 322);
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE));
		contentPane.add(scrollPane);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon("icon\\sal.png"));
		btnAlterar.setFont(new Font("Marcellus SC", Font.BOLD, 22));
		btnAlterar.setBounds(1113, 568, 216, 50);
		contentPane.add(btnAlterar);
		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Erro Selecione uma consulta", "Erro de consulta",
							JOptionPane.ERROR_MESSAGE);
				} 
				
				else if(table.getValueAt(linha, 7).equals("FINALIZADA")) {
					JOptionPane.showMessageDialog(null, "Erro Consulta Finalizada", "Erro de consulta",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					consulta = CTAgendaConsulta.get((int) table.getValueAt(linha, 0));
					dispose();
					new TelaCadAgenda(consulta, linha + 1);
				}
			}

		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("icon\\del.png"));
		btnExcluir.setFont(new Font("Marcellus SC", Font.BOLD, 22));
		btnExcluir.setBounds(1113, 630, 216, 50);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Erro de seleção -  selecione uma linha");
				} 
				
				else if(table.getValueAt(row, 7).equals("FINALIZADA")) {
					JOptionPane.showMessageDialog(null, "Erro Consulta Finalizada", "Erro de consulta",
							JOptionPane.ERROR_MESSAGE);
				}else {
					int op = JOptionPane.showConfirmDialog(null, "Deseja realemte Excluir ?", "informação",
							JOptionPane.YES_NO_OPTION);
					if (op == 0) {
						int id = (int) table.getValueAt(row, 0);
						CTAgendaConsulta.remove(id);
						modelo1.removeRow(row);
					}

				}
				

			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(1104, 343, 235, 223);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBorder(new TitledBorder(null, "Comando da lista de chegada", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 51)));

		JLabel lblOrdemDeChegada = new JLabel("Ordem de Chegada:");
		lblOrdemDeChegada.setForeground(new Color(0, 102, 51));
		lblOrdemDeChegada.setFont(new Font("Masque", Font.PLAIN, 13));
		lblOrdemDeChegada.setBounds(1113, 25, 228, 16);
		contentPane.add(lblOrdemDeChegada);

		modeloOrd = new DefaultTableModel() {
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		modeloOrd.addColumn("Nª");
		modeloOrd.addColumn("PACIENTE");
		tableOrdem = new JTable(modeloOrd);
		tableOrdem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOrdem.setRowHeight(30);
		tableOrdem.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		tableOrdem.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		tableOrdem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableOrdem.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());

		tableOrdem.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableOrdem.getColumnModel().getColumn(1).setPreferredWidth(190);

		JScrollPane scrollPane_1 = new JScrollPane(tableOrdem);
		scrollPane_1.setBounds(1111, 46, 218, 286);
		contentPane.add(scrollPane_1);
		
		 atualizarListaOrdem();

		

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setSelectedIcon(new ImageIcon("icon\\enviar2.png"));
		btnEnviar.setIcon(new ImageIcon("icon\\enviar.png"));
		btnEnviar.setFont(new Font("Marcellus SC", Font.BOLD, 42));
		btnEnviar.setBounds(1113, 361, 216, 57);
		contentPane.add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conx = new CTAgendaConsulta();
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Erro de seleção -  Escolha um paciente","Aviso de erro", JOptionPane.ERROR_MESSAGE);
				} else {
					int idcon =(int) table.getValueAt(row, 0);
					
					if(conx.verificarOrdem(idcon)) {
						JOptionPane.showMessageDialog(null, "Erro ao enviar -  Consulta já foi enviada","Aviso de erro", JOptionPane.ERROR_MESSAGE);
					}
					else if(table.getValueAt(row, 7).equals("FINALIZADA")) {
						JOptionPane.showMessageDialog(null, "Erro ao enviar -  Consulta Finalizada","Aviso de erro", JOptionPane.ERROR_MESSAGE);
					}
					else {
					consulta = CTAgendaConsulta.get(idcon);	
					consulta.setStatus("ENVIADA");
					CTAgendaConsulta.update(consulta);
					//atualizarListaOrdem();
					// criando lista de consulta	
					ListaChegada consulta = new ListaChegada();
					consulta.setIdConsulta(idcon);
			
					consulta.setDataConsulta((String)table.getValueAt(row, 1));
					consulta.setHora((String) table.getValueAt(row, 2));
					String nome=(String) table.getValueAt(row, 3);
					consulta.setPaciente(nome);
					consulta.setProced((String) table.getValueAt(row, 4));
					consulta.setDes((String) table.getValueAt(row, 5));
					consulta.setValor1((String) table.getValueAt(row, 6));
					consulta.setStatus("ENVIADA");
					consulta.setObs(""+table.getValueAt(row, 8));
					
					//adicionar lista na ordem
					addTableOrdem(consulta,nome);
					atualizarListaOrdem();
					}

				}
			}
		});

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setSelectedIcon(new ImageIcon("icon\\del.png"));
		btnDeletar.setIcon(new ImageIcon("icon\\del2.png"));
		btnDeletar.setFont(new Font("Marcellus SC", Font.BOLD, 36));
		btnDeletar.setBounds(1113, 430, 216, 57);
		contentPane.add(btnDeletar);
		
		
		btnDeletar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conx = new CTAgendaConsulta();
				int row = tableOrdem.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Erro de seleção -  selecione uma linha");
				} else {
					modeloOrd.removeRow(row);
					conx.removeListaOrdem(row);
				}
			}
		});
		
		JButton button = new JButton("Atualizar");
		button.setSelectedIcon(new ImageIcon("icon\\atualizar.png"));
		button.setIcon(new ImageIcon("icon\\atualizar2.png"));
		button.setFont(new Font("Marcellus SC", Font.BOLD, 26));
		button.setBounds(1113, 499, 216, 57);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarListaOrdem();
				addLinhaDia(new Date(), medicog);
			}
		});

		

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				

			}

			@Override
			public void mousePressed(MouseEvent e) {
				

			}

			@Override
			public void mouseExited(MouseEvent e) {
				

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtid.setText(String.format("%010d", table.getValueAt(table.getSelectedRow(), 0)));
				txtdata.setText("" + table.getValueAt(table.getSelectedRow(), 1));
				txthora.setText("" + table.getValueAt(table.getSelectedRow(), 2));
				txtpaciente.setText("" + table.getValueAt(table.getSelectedRow(), 3));
				txtproced.setText("" + table.getValueAt(table.getSelectedRow(), 4));
				String g = (String) table.getValueAt(table.getSelectedRow(), 8);
				if (g == null) {
					g = "";
				}
				txtobs.setText(g);
				lbDesc.setText("" + table.getValueAt(table.getSelectedRow(), 5));
				lbvalor.setText("" + table.getValueAt(table.getSelectedRow(), 6));

			}
		});
	}

	public void addLinhaDia(Date data,Profissional m) {
		limparTabela();
		try {
			ResultSet rs = CTAgendaConsulta.preencherListaPelaData(data, m.getMatricula());
			while (rs.next()) {
				AgendaConsulta a = new AgendaConsulta();
				a.setId(rs.getInt("ID"));
				a.setHora(rs.getString("hora"));
				a.setData(rs.getDate("dataConsult"));
				a.setIdPaciente(Integer.parseInt(rs.getString("idPaciente")));
				a.setIdProce(rs.getString("idProced"));
				a.setMatMedico(rs.getString("marMedico"));
				a.setStatus(rs.getString("status"));
				a.setDesc(rs.getFloat("descont"));
				a.setValor(rs.getFloat("valor"));
				a.setObs(rs.getString("obs"));
				String desc = convert.format(a.getDesc());
				String v = convert.format(a.getValor());
				modelo1.addRow(new Object[] { a.getId(), formato.format(a.getData()), a.getHora(),
						CTPaciente.get(a.getIdPaciente()).getNome(),
						CTProcedimento.getProcedimento(Integer.parseInt(a.getIdProce())).getDesc()
						, desc, v,
						a.getStatus(),a.getObs() });
				if(a.getStatus().equals("MARCADA") || a.getStatus().equals("ENVIADA") ) {
					q++;
				}
				t+=a.getValor();
			}
		} catch (SQLException e1) {
			new TelaErroLog(e1.getMessage(), "Erro de carregamento", getTitle());
		}

	}

	public void limparTabela() {
		while (table.getModel().getRowCount() > 0) {
			modelo1.removeRow(0);
		}
		q=0;
		t=0;
	}
	
	public void limparTabelaOrdem() {
		while (tableOrdem.getModel().getRowCount() > 0) {
			modeloOrd.removeRow(0);
		}
	}

	public void limparCampo() {
		txtid.setText("");
		txtpaciente.setText("");
		txtdata.setText("");
		txthora.setText("");
		txtproced.setText("");
		txtobs.setText("");
	}

	public void pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dimensao = t.getScreenSize();
		this.setSize((dimensao.width - 5), (dimensao.height - 38));
	}

	public void addTableOrdem(ListaChegada novo,String nomePaciente) {
		conx = new CTAgendaConsulta();
		num=tableOrdem.getRowCount();
		if(num==0) {
			num=1;
		}
		else {
			num=Integer.parseInt((String) tableOrdem.getValueAt(num-1,0))+1;
		}
		modeloOrd.addRow(new Object[] { num, nomePaciente });
		novo.setNum(""+num);
		novo.setMedico(medicog.getMatricula());
		novo.setQuant(q);
		novo.setTotal(t);
		conx.addListaOrdem(novo);
		addLinhaDia(new Date(), medicog);
		
	 
	}
	public void atualizarListaOrdem() {
		limparTabelaOrdem();
		conx = new CTAgendaConsulta();
		for (ListaChegada l : conx.getListaOrdem()) {
			if (l.getMedico().equals(medicog.getMatricula())) {
				modeloOrd.addRow(new Object[] { l.getNum(), l.getPaciente() });
			}
		}
	}
}
