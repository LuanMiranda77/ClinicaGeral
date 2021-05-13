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

import CONTROL.CTAgendaConsulta;
import CONTROL.CTListaChegada;
import CONTROL.CTPaciente;
import CONTROL.CTProcedimento;
import CONTROL.CTProfissional;
import MODEL.AgendaConsulta;
import MODEL.ListaChegada;
import MODEL.Profissional;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class TelaConsulta extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JTable table;
	private DefaultTableModel modelo1;
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
	private JLabel lbquant;
	private JLabel lbtotal;
	private int quant=0;;
	private float total=0;;
	private CTAgendaConsulta conx;
	private int g=0;
	private JLabel label;
	private JLabel lblCodConsulta;
	private JLabel lblNomeDoPaciente;
	private JLabel lblCalendarioDeAgenda;
	private JLabel lblProcedimento;
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblObservaesDaConsulta;
	private JLabel lblDesconto;
	private JLabel lblListaDasConsultas;


	public static void main(String[] args) {
		Profissional d = CTProfissional.get("LUNA");
		new TelaConsulta(d);
	}

	public TelaConsulta(Profissional medico) {
		setTitle("Gerenciamento - Consultas");
		setBackground(new Color(255, 255, 255));
		ModeloTabela.ativarModeloTela(this);
		pegarResolucao();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(8, 4, 4, 4, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		medicog = medico;

		label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Masque", Font.PLAIN, 13));

		lblCodConsulta = new JLabel("Cod Consulta:");
		lblCodConsulta.setForeground(Color.BLACK);
		lblCodConsulta.setFont(new Font("Masque", Font.PLAIN, 12));

		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setForeground(Color.GREEN);
		txtid.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtid.setColumns(10);

		calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				limparCampo();

				String d = formato.format(calendar.getDate());
				String h = formato.format(new Date());
				
				if (d.equals(h)) {
					preencherListaOrdem();
					g=CTListaChegada.getSise();
					lbquant.setText(""+g);
					lbtotal.setText(convert.format(total));
					
				} else {
					addLinhaDia(calendar.getDate(), medico);
					lbtotal.setText(convert.format(total));
				}
			}
		});

		lblNomeDoPaciente = new JLabel("Nome do Paciente:");
		lblNomeDoPaciente.setForeground(Color.BLACK);
		lblNomeDoPaciente.setFont(new Font("Masque", Font.PLAIN, 12));

		txtpaciente = new JTextField();
		txtpaciente.setEditable(false);
		txtpaciente.setForeground(new Color(0, 51, 153));
		txtpaciente.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtpaciente.setColumns(10);

		txtproced = new JTextField();
		txtproced.setEditable(false);
		txtproced.setForeground(new Color(0, 0, 102));
		txtproced.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtproced.setColumns(10);

		lblProcedimento = new JLabel("Procedimento:");
		lblProcedimento.setForeground(Color.BLACK);
		lblProcedimento.setFont(new Font("Masque", Font.PLAIN, 12));

		lblCalendarioDeAgenda = new JLabel("Calendario da Consulta");
		lblCalendarioDeAgenda.setForeground(Color.ORANGE);
		lblCalendarioDeAgenda.setFont(new Font("Masque", Font.PLAIN, 13));

		lblObservaesDaConsulta = new JLabel("Observa\u00E7\u00F5es da consulta:");
		lblObservaesDaConsulta.setForeground(new Color(204, 0, 0));
		lblObservaesDaConsulta.setFont(new Font("Masque", Font.PLAIN, 12));

		txtobs = new JTextArea();
		txtobs.setFont(new Font("SansSerif", Font.BOLD, 18));
		txtobs.setForeground(new Color(204, 0, 0));

		lblData = new JLabel("Data:");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Masque", Font.PLAIN, 12));

		txtdata = new JTextField();
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setForeground(new Color(0, 0, 102));
		txtdata.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtdata.setEditable(false);
		txtdata.setColumns(10);

		lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Masque", Font.PLAIN, 12));

		txthora = new JTextField();
		txthora.setHorizontalAlignment(SwingConstants.CENTER);
		txthora.setForeground(new Color(0, 0, 102));
		txthora.setFont(new Font("SansSerif", Font.BOLD, 15));
		txthora.setEditable(false);
		txthora.setColumns(10);

		lbDesc = new JLabel("0,00");
		lbDesc.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbDesc.setForeground(new Color(255, 255, 255));
		lbDesc.setBackground(new Color(102, 0, 0));
		lbDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lbDesc.setOpaque(true);

		lblValorDaConsulta = new JLabel("Valor da Consulta");
		lblValorDaConsulta.setForeground(Color.ORANGE);
		lblValorDaConsulta.setFont(new Font("Masque", Font.PLAIN, 13));

		lbvalor = new JLabel("0,00");
		lbvalor.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbvalor.setForeground(Color.BLACK);
		lbvalor.setBackground(new Color(0, 102, 255));
		lbvalor.setHorizontalAlignment(SwingConstants.CENTER);
		lbvalor.setOpaque(true);

		lblDesconto = new JLabel("Desconto:");
		lblDesconto.setForeground(Color.BLACK);
		lblDesconto.setFont(new Font("Masque", Font.PLAIN, 10));

		lblValorText = new JLabel("Valor:");
		lblValorText.setForeground(Color.BLACK);
		lblValorText.setFont(new Font("Masque", Font.PLAIN, 10));

		lblListaDasConsultas = new JLabel("Lista das Consultas Agendadas\r\n");
		lblListaDasConsultas.setForeground(Color.ORANGE);
		lblListaDasConsultas.setFont(new Font("Masque", Font.PLAIN, 13));
		table(medico);

		setVisible(true);
	}

	@SuppressWarnings("serial")
	public void table(Profissional m) {
		//// modelo da tabela;

		modelo1 = new DefaultTableModel() {
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		modelo1.addColumn("ORDEM");
		modelo1.addColumn("DATA");
		modelo1.addColumn("HORA");
		modelo1.addColumn("PACIENTE");
		modelo1.addColumn("PROCEDIMENTO");
		modelo1.addColumn("DESC");
		modelo1.addColumn("VALOR");
		modelo1.addColumn("STATUS");
		modelo1.addColumn("");
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
		table.getColumnModel().getColumn(9).setPreferredWidth(0);
		
		limparCampo();
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(Color.BLACK);
		lblQuantidade.setFont(new Font("Masque", Font.PLAIN, 10));
		g=CTListaChegada.getSise();
		lbquant = new JLabel(""+g);
		lbquant.setOpaque(true);
		lbquant.setHorizontalAlignment(SwingConstants.CENTER);
		lbquant.setForeground(Color.WHITE);
		lbquant.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbquant.setBackground(new Color(0, 102, 0));
		
		JLabel lblTotalDasConsultas = new JLabel("Total das Consultas:");
		lblTotalDasConsultas.setForeground(Color.BLACK);
		lblTotalDasConsultas.setFont(new Font("Masque", Font.PLAIN, 10));
		
		
		lbtotal = new JLabel("");
		lbtotal.setOpaque(true);
		lbtotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbtotal.setForeground(new Color(255, 0, 0));
		lbtotal.setFont(new Font("SansSerif", Font.BOLD, 40));
		lbtotal.setBackground(new Color(0, 0, 102));

	
			preencherListaOrdem();
			lbtotal.setText(convert.format(total));
		

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE));


		JButton btnHistorico = new JButton("Anamnese");
		btnHistorico.setSelectedIcon(new ImageIcon("icon\\his2.png"));
		btnHistorico.setIcon(new ImageIcon("icon\\his1.png"));
		btnHistorico.setFont(new Font("Marcellus SC", Font.BOLD, 26));
		btnHistorico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Erro Selecione uma consulta", "Erro de consulta",
							JOptionPane.ERROR_MESSAGE);
				} else {
					consulta = CTAgendaConsulta.get((int) table.getValueAt(linha, 8));
					consulta.setObs(txtobs.getText());
					dispose();
					new TelaHistoricoClinico(consulta);
			
				}

			}
		});

		JButton btncancelar = new JButton("Cancelar");
		btncancelar.setIcon(new ImageIcon("icon\\del.png"));
		btncancelar.setFont(new Font("Marcellus SC", Font.BOLD, 26));
		btncancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblDadosDaConsultas = new JLabel("Dados da Consultas:");
		lblDadosDaConsultas.setForeground(Color.ORANGE);
		lblDadosDaConsultas.setFont(new Font("Masque", Font.PLAIN, 13));
		
		

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setSelectedIcon(new ImageIcon("icon\\atualizar.png"));
		btnAtualizar.setIcon(new ImageIcon("icon\\atualizar2.png"));
		btnAtualizar.setFont(new Font("Marcellus SC", Font.BOLD, 26));
		btnAtualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preencherListaOrdem();

			}
		});

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setIcon(new ImageIcon("icon\\salva2.png"));
		btnFinalizar.setSelectedIcon(new ImageIcon("icon\\salva1.png"));
		btnFinalizar.setFont(new Font("Marcellus SC", Font.BOLD, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodConsulta, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(126)
									.addComponent(lblNomeDoPaciente, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))))
					.addGap(126)
					.addComponent(lblCalendarioDeAgenda, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(200)
					.addComponent(lblValorDaConsulta, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(lblDadosDaConsultas, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(txtpaciente, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblProcedimento, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(txtproced, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(txtdata, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(txthora, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(txtobs, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblObservaesDaConsulta, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))))
					.addGap(22)
					.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDesconto, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDesc, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValorText, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbvalor, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbquant, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbtotal, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalDasConsultas, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblListaDasConsultas, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHistorico, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addComponent(btncancelar, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodConsulta, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNomeDoPaciente, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(lblCalendarioDeAgenda))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(lblValorDaConsulta))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(lblDadosDaConsultas)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtid, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpaciente, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(9)
							.addComponent(lblProcedimento, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtproced, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtdata, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(txthora, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(txtobs, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblObservaesDaConsulta, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
						.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDesconto, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lbDesc, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
							.addGap(31)
							.addComponent(lblValorText, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(lbvalor, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidade, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lbquant, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addComponent(lbtotal, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTotalDasConsultas, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
					.addGap(23)
					.addComponent(lblListaDasConsultas)
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnHistorico, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btncancelar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addGap(20))
		);
		contentPane.setLayout(gl_contentPane);
		btnFinalizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = table.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Erro Selecione uma consulta", "Erro de consulta",
							JOptionPane.ERROR_MESSAGE);
				} else {
					conx= new CTAgendaConsulta();
					consulta = CTAgendaConsulta.get((int) table.getValueAt(linha, 8));
					consulta.setObs(txtobs.getText());
					consulta.setStatus("FINALIZADA");
					CTAgendaConsulta.update(consulta);
					conx.removeConsultFinalizada(consulta.getId());
					modelo1.removeRow(linha);
					int k=--g;
					if(k>0) {
					JOptionPane.showMessageDialog(null, "Consulta Finalizada com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
					preencherListaOrdem();
					}
					else if(k==0) {
						JOptionPane.showMessageDialog(null, "Todas as consultas Foram Atendidas!", "Aviso Importante", JOptionPane.INFORMATION_MESSAGE);
					}
					
					
					lbquant.setText(""+k);
					
					
					
				}

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
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				txtid.setText(String.format("%010d", table.getValueAt(table.getSelectedRow(), 8)));
				txtdata.setText("" + table.getValueAt(table.getSelectedRow(), 1));
				txthora.setText("" + table.getValueAt(table.getSelectedRow(), 2));
				txtpaciente.setText("" + table.getValueAt(table.getSelectedRow(), 3));
				txtproced.setText("" + table.getValueAt(table.getSelectedRow(), 4));
				String g = (String) table.getValueAt(table.getSelectedRow(), 9);
				if (g == null) {
					g = "";
				}
				txtobs.setText(g);
				lbDesc.setText("" + table.getValueAt(table.getSelectedRow(), 5));
				lbvalor.setText("" + table.getValueAt(table.getSelectedRow(), 6));

			}
		});
	}
	


	public void addLinhaDia(Date data, Profissional m) {
		limparTabela();
		try {
			quant=0;
			total=0;
			ResultSet rs = CTAgendaConsulta.preencherListaPelaData(data, m.getMatricula());
			while (rs.next()) {
				AgendaConsulta a = new AgendaConsulta();
				a.setId(rs.getInt("ID"));
				a.setHora(rs.getString("hora"));
				a.setData(rs.getDate("dataConsult"));
				a.setIdPaciente(rs.getInt("idPaciente"));
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
						CTProcedimento.getProcedimento(Integer.parseInt(a.getIdProce())).getDesc(), desc, v,
						a.getStatus() });
				quant++;
				total+=a.getValor();
				
			}
			lbquant.setText(""+quant);
		} catch (SQLException e1) {
			new TelaErroLog(e1.getMessage(), "", "TelaConsultaAg");
		}

	}

	public void preencherListaOrdem() {
		conx = new CTAgendaConsulta();
		limparTabela();
		total=0;
		for (ListaChegada l : conx.getListaOrdem()) {
			if (l.getMedico().equals(medicog.getMatricula()) && l.getStatus().equals("ENVIADA")) {
				modelo1.addRow(new Object[] { l.getNum(), l.getDataConsulta(), l.getHora(), l.getPaciente(),
						l.getProced(), l.getDes(), l.getValor1(), l.getStatus(), l.getIdConsulta(), l.getObs() });
				total=l.getTotal();
			}
		}
		lbquant.setText(""+CTListaChegada.getSise());
	}

	public void limparTabela() {
		while (table.getModel().getRowCount() > 0) {
			modelo1.removeRow(0);
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
}
