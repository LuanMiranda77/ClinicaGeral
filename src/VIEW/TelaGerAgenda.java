package VIEW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JCalendar;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTAgendaProf;
import CONTROL.CTProfissional;
import MODEL.AgendaConsulta;
import MODEL.Dia;
import MODEL.Profissional;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;

public class TelaGerAgenda extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo1;
	private Profissional medico;
	private JComboBox<String> cbxmedico;
	private JLabel lbldomingo;
	private JLabel lblsegunda;
	private JLabel lblterca;
	private JLabel lblquarta;
	private JLabel lblquinta;
	private JLabel lblsexta;
	private JLabel lblsabado;
	private JButton btnMarcar;
	private JButton button_1;
	private JLabel lblSelecioneOMdico;
	private JLabel lblMedicoEspecialista;
	private JLabel lblAgendaMedica;
	private JCalendar calendar;
	private ArrayList<Dia> listaHora;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new TelaGerAgenda();
	}
	public TelaGerAgenda() {
		setTitle("Gerenciamento - Consultas");
		setBackground(Color.DARK_GRAY);
		setBounds(200, 1, 879, 750);
		ModeloTabela.ativarModeloTela(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(8, 4, 4, 4, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);

		lblSelecioneOMdico = new JLabel("Selecione o M\u00E9dico");
		lblSelecioneOMdico.setBounds(9, 14, 186, 16);
		lblSelecioneOMdico.setForeground(Color.ORANGE);
		lblSelecioneOMdico.setFont(new Font("Masque", Font.PLAIN, 13));

		lblMedicoEspecialista = new JLabel("Medico Especialista:");
		lblMedicoEspecialista.setBounds(19, 42, 314, 16);
		lblMedicoEspecialista.setForeground(Color.BLACK);
		lblMedicoEspecialista.setFont(new Font("Masque", Font.PLAIN, 11));

		cbxmedico = new JComboBox<String>();
		cbxmedico.setBounds(16, 60, 572, 35);
		cbxmedico.setModel(new DefaultComboBoxModel<String>(CTProfissional.getListaNomes()));
		cbxmedico.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxmedico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listaHora = new ArrayList<>();
				if (!cbxmedico.getSelectedItem().equals("-")) {
					medico = CTProfissional.get("" + cbxmedico.getSelectedItem());
					listaHora = CTAgendaProf.getHorario(medico.getAgenda());

					for (int f = 0; f < listaHora.get(1).getHora().size(); f++) {
						modelo1.addRow(new Object[] { "", "", "", "", "", "", "", "" });
					}
					String diaSem = CTAgendaConsulta.getDiaSemana(calendar.getDate());
					carregaDataLabel(diaSem);

				} else {

					limparTabela2();
				}
			}
		});

		lblAgendaMedica = new JLabel("Agenda Medica");
		lblAgendaMedica.setBounds(19, 131, 272, 16);
		lblAgendaMedica.setForeground(Color.ORANGE);
		lblAgendaMedica.setFont(new Font("Masque", Font.PLAIN, 13));

		table();
		setVisible(true);

	}

	@SuppressWarnings("serial")
	public void table() {
		//// modelo da tabela;

		modelo1 = new DefaultTableModel() {
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");

		calendar = new JCalendar();
		calendar.setBounds(19, 153, 383, 198);
		calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (!cbxmedico.getSelectedItem().equals("-")) {
					String diaSem = CTAgendaConsulta.getDiaSemana(calendar.getDate());
					carregaDataLabel(diaSem);
				} else {
					JOptionPane.showMessageDialog(null, "Erro selecione o Medico primeiro", "Erro de seleção",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		table = new JTable(modelo1);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(45);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());
		table.setRowHeight(0, 40);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(19, 404, 806, 248);
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE));

		JPanel panel = new JPanel();
		panel.setBounds(21, 351, 806, 57);
		panel.setBackground(new Color(255, 255, 255));

		JLabel lbhora = new JLabel("Hora");
		lbhora.setOpaque(true);
		lbhora.setHorizontalAlignment(SwingConstants.CENTER);
		lbhora.setForeground(Color.WHITE);
		lbhora.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbhora.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lbhora.setBackground(SystemColor.activeCaption);

		lbldomingo = new JLabel("<html>Domingo<br>");
		lbldomingo.setBackground(SystemColor.activeCaption);
		lbldomingo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbldomingo.setHorizontalAlignment(SwingConstants.CENTER);
		lbldomingo.setForeground(Color.WHITE);
		lbldomingo.setOpaque(true);
		lbldomingo.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		label.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		label.setBackground(SystemColor.activeCaption);

		lblsabado = new JLabel("<html>Sabado<br>");
		lblsabado.setOpaque(true);
		lblsabado.setHorizontalAlignment(SwingConstants.CENTER);
		lblsabado.setForeground(Color.WHITE);
		lblsabado.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblsabado.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblsabado.setBackground(SystemColor.activeCaption);

		lblsexta = new JLabel("<html>Sexta<br>");
		lblsexta.setOpaque(true);
		lblsexta.setHorizontalAlignment(SwingConstants.CENTER);
		lblsexta.setForeground(Color.WHITE);
		lblsexta.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblsexta.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblsexta.setBackground(SystemColor.activeCaption);

		lblquinta = new JLabel("<html>Quinta<br>");
		lblquinta.setOpaque(true);
		lblquinta.setHorizontalAlignment(SwingConstants.CENTER);
		lblquinta.setForeground(Color.WHITE);
		lblquinta.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblquinta.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblquinta.setBackground(SystemColor.activeCaption);

		lblquarta = new JLabel("<html>Quarta<br>");
		lblquarta.setOpaque(true);
		lblquarta.setHorizontalAlignment(SwingConstants.CENTER);
		lblquarta.setForeground(Color.WHITE);
		lblquarta.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblquarta.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblquarta.setBackground(SystemColor.activeCaption);

		lblterca = new JLabel("<html>Ter\u00E7a<br>");
		lblterca.setOpaque(true);
		lblterca.setHorizontalAlignment(SwingConstants.CENTER);
		lblterca.setForeground(Color.WHITE);
		lblterca.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblterca.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblterca.setBackground(SystemColor.activeCaption);

		lblsegunda = new JLabel("<html>Segunda<br>");
		lblsegunda.setOpaque(true);
		lblsegunda.setHorizontalAlignment(SwingConstants.CENTER);
		lblsegunda.setForeground(Color.WHITE);
		lblsegunda.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblsegunda.setBorder(new MatteBorder(4, 4, 4, 4, (Color) Color.GRAY));
		lblsegunda.setBackground(SystemColor.activeCaption);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addComponent(lbhora, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				.addComponent(lbldomingo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblsegunda, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblterca, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblquarta, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblquinta, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblsexta, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(98).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblsabado, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(15)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lbhora, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbldomingo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblsegunda, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblterca, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblquarta, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblquinta, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblsexta, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblsabado, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))));
		panel.setLayout(gl_panel);

		btnMarcar = new JButton("Marcar");
		btnMarcar.setBounds(279, 658, 118, 35);
		btnMarcar.setIcon(new ImageIcon("icon\\sal.png"));
		btnMarcar.setHorizontalAlignment(SwingConstants.LEFT);
		btnMarcar.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		btnMarcar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null, "Escolha um Horario disponivel", "Erro de Agendamento",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
				int column = table.getSelectedColumn();
				Date datamarc = new Date();
				if (column == 1) {
					String[] d = lbldomingo.getText().split("<br>");
					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else if (column == 2) {
					String[] d = lblsegunda.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

				else if (column == 3) {
					String[] d = lblterca.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

				else if (column == 4) {
					String[] d = lblquarta.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}

				else if (column == 5) {
					String[] d = lblquinta.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else if (column == 6) {
					String[] d = lblsexta.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else if (column == 7) {
					String[] d = lblsabado.getText().split("<br>");

					try {
						datamarc = formato.parse(d[1]);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				String disp = "" + table.getValueAt(table.getSelectedRow(), column);
				if (disp.equals("Disponivel")) {
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, -1);
					Date ontem = new Date(calendar.getTime().getTime());
					if(datamarc.before(ontem)) {
						JOptionPane.showMessageDialog(null, "Erro data já passou ", "Erro de Agendamento",
								JOptionPane.ERROR_MESSAGE);
					}
					else {
					AgendaConsulta consulta = new AgendaConsulta();
					consulta.setData(datamarc);
					consulta.setHora(""+table.getValueAt(table.getSelectedRow(), 0));
					consulta.setMatMedico(medico.getMatricula());
					dispose();
					new TelaCadAgenda(consulta, 0);
					listaHora = null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Horario e data Indisponivel", "Erro de Agendamento",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			}
		});

		button_1 = new JButton("Cancelar");
		button_1.setBounds(403, 658, 118, 35);
		button_1.setIcon(new ImageIcon("icon\\del.png"));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		button_1.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		JButton btnConsultaAgendadas = new JButton("Consulta Agendadas");
		btnConsultaAgendadas.setBounds(635, 291, 181, 54);
		btnConsultaAgendadas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if(!cbxmedico.getSelectedItem().equals("-")) {
				new TelaConsultaAg(medico);
			}
			else {
				JOptionPane.showMessageDialog(null, "Selecione um medico");
			}
				
			}
		});
		btnConsultaAgendadas.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsultaAgendadas.setFont(new Font("Marcellus SC", Font.BOLD, 14));
		contentPane.setLayout(null);
		contentPane.add(lblSelecioneOMdico);
		contentPane.add(lblMedicoEspecialista);
		contentPane.add(cbxmedico);
		contentPane.add(panel);
		contentPane.add(scrollPane);
		contentPane.add(btnMarcar);
		contentPane.add(button_1);
		contentPane.add(calendar);
		contentPane.add(btnConsultaAgendadas);
		contentPane.add(lblAgendaMedica);
	
	}

	public void carregarAgenda(String nome, Date dia, int diaSemana) {
		String hdisp = "Disponivel";

		String hnao = "AGENDADA";
	
	
		if (!cbxmedico.getSelectedItem().equals("-")) {

			int cont = 0;
			TableCellRenderer tcr = new CellColor();

			for (String k : listaHora.get(1).getHora()) {
				modelo1.setValueAt(k, cont, 0);
				tcr.getTableCellRendererComponent(table, k, false, false, 0, 0);

				cont++;
			}
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		}

		if (listaHora.get(diaSemana) != null) {
			for (int j = 0; j < listaHora.get(diaSemana).getHora().size(); j++) {
				if (CTAgendaConsulta.testData(dia, listaHora.get(diaSemana).getHora().get(j), nome) == true) {
					modelo1.setValueAt(hnao, j, diaSemana + 1);
				} else
					modelo1.setValueAt(hdisp, j, diaSemana + 1);
			}
		}
	}

	public void limparTabela2() {
		while (table.getModel().getRowCount() > 0) {
			modelo1.removeRow(0);
		}
		try {

		for (int f = 0; f < listaHora.get(1).getHora().size(); f++) {
			modelo1.addRow(new Object[] { "", "", "", "", "", "", "", "" });
		}
		}catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
	}

	void visibleTudo() {
		
		lbldomingo.setVisible(true);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		
		lblsegunda.setVisible(true);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
		
		lblterca.setVisible(true);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		
		lblquarta.setVisible(true);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		
		lblquinta.setVisible(true);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		
		lblsexta.setVisible(true);
		table.getColumnModel().getColumn(6).setMinWidth(100);
		table.getColumnModel().getColumn(6).setMaxWidth(100);
		
		lblsabado.setVisible(true);
		table.getColumnModel().getColumn(7).setMinWidth(100);
		table.getColumnModel().getColumn(7).setMaxWidth(100);

	}

	class CellColor extends JLabel implements TableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			setText("" + value);
			setHorizontalAlignment(JLabel.CENTER);
			setVerticalAlignment(JLabel.CENTER);
			setFont(new Font("SansSerif", Font.BOLD, 15));
			setOpaque(true);
			setBackground(Color.GRAY);
			setForeground(Color.WHITE);

			return this;
		}
	}

	public void carregarTableDia(int dia) {
		limparTabela2();
		Date data = new Date();
		
		for (int i = dia; i <7; i++) {

			if (i == 0) {
				String[] d = lbldomingo.getText().split("<br>");

				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 1) {
				String[] d = lblsegunda.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 2) {
				String[] d = lblterca.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 3) {
				String[] d = lblquarta.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 4) {
				String[] d = lblquinta.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 5) {
				String[] d = lblsexta.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			if (i == 6) {
				String[] d = lblsabado.getText().split("<br>");
				try {
					data = formato.parse(d[1]);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			carregarAgenda(medico.getMatricula(), data, i);
		}
	}

	@SuppressWarnings("deprecation")
	public void carregaDataLabel(String diaSem) {
		if (!cbxmedico.getSelectedItem().equals("-")) {

			switch (diaSem) {

			case "DOM":
				visibleTudo();

				lbldomingo.setText("");
				lbldomingo.setText("<html>Domingo<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 0));

				lblsegunda.setText("");
				lblsegunda.setText("<html>Segunda<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				lblterca.setText("");
				lblterca.setText("<html>Terca<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 2));
				lblquarta.setText("");
				lblquarta.setText("<html>Quarta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 3));
				lblquinta.setText("");

				lblquinta.setText("<html>Quinta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 4));

				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 5));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 6));

				carregarTableDia(0);
				break;

			case "SEG":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				
				lblsegunda.setText("");
				lblsegunda.setText("<html>Segunda<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				lblterca.setText("");
				lblterca.setText("<html>Terca<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				lblquarta.setText("");
				lblquarta.setText("<html>Quarta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 2));
				lblquinta.setText("");
				lblquinta.setText("<html>Quinta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 3));
				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 4));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 5));
				carregarTableDia(1);

				break;

			case "TER":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				lblsegunda.setVisible(false);
				table.getColumnModel().getColumn(2).setMinWidth(0);
				table.getColumnModel().getColumn(2).setMaxWidth(0);
				lblterca.setText("");
				lblterca.setText("<html>Terca<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				lblquarta.setText("");
				lblquarta.setText("<html>Quarta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				lblquinta.setText("");
				lblquinta.setText("<html>Quinta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 2));
				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 3));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 4));
				carregarTableDia(2);
				break;

			case "QUA":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				lblsegunda.setVisible(false);
				table.getColumnModel().getColumn(2).setMinWidth(0);
				table.getColumnModel().getColumn(2).setMaxWidth(0);
				lblterca.setVisible(false);
				table.getColumnModel().getColumn(3).setMinWidth(0);
				table.getColumnModel().getColumn(3).setMaxWidth(0);
				lblquarta.setText("");
				lblquarta.setText("<html>Quarta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				lblquinta.setText("");
				lblquinta.setText("<html>Quinta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 2));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 3));
				carregarTableDia(3);
				break;

			case "QUI":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				lblsegunda.setVisible(false);
				table.getColumnModel().getColumn(2).setMinWidth(0);
				table.getColumnModel().getColumn(2).setMaxWidth(0);
				lblterca.setVisible(false);
				table.getColumnModel().getColumn(3).setMinWidth(0);
				table.getColumnModel().getColumn(3).setMaxWidth(0);
				lblquarta.setVisible(false);
				table.getColumnModel().getColumn(4).setMinWidth(0);
				table.getColumnModel().getColumn(4).setMaxWidth(0);
				lblquinta.setText("");
				lblquinta.setText("<html>Quinta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 2));
				carregarTableDia(4);
				break;

			case "SEX":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				lblsegunda.setVisible(false);
				table.getColumnModel().getColumn(2).setMinWidth(0);
				table.getColumnModel().getColumn(2).setMaxWidth(0);
				lblterca.setVisible(false);
				table.getColumnModel().getColumn(3).setMinWidth(0);
				table.getColumnModel().getColumn(3).setMaxWidth(0);
				lblquarta.setVisible(false);
				table.getColumnModel().getColumn(4).setMinWidth(0);
				table.getColumnModel().getColumn(4).setMaxWidth(0);
				lblquinta.setVisible(false);
				table.getColumnModel().getColumn(5).setMinWidth(0);
				table.getColumnModel().getColumn(5).setMaxWidth(0);
				lblsexta.setText("");
				lblsexta.setText("<html>Sexta<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate() + 1));
				carregarTableDia(5);
				break;

			case "SAB":
				visibleTudo();

				lbldomingo.setVisible(false);
				table.getColumnModel().getColumn(1).setMinWidth(0);
				table.getColumnModel().getColumn(1).setMaxWidth(0);
				lblsegunda.setVisible(false);
				table.getColumnModel().getColumn(2).setMinWidth(0);
				table.getColumnModel().getColumn(2).setMaxWidth(0);
				lblterca.setVisible(false);
				table.getColumnModel().getColumn(3).setMinWidth(0);
				table.getColumnModel().getColumn(3).setMaxWidth(0);
				lblquarta.setVisible(false);
				table.getColumnModel().getColumn(4).setMinWidth(0);
				table.getColumnModel().getColumn(4).setMaxWidth(0);
				lblquinta.setVisible(false);
				table.getColumnModel().getColumn(5).setMinWidth(0);
				table.getColumnModel().getColumn(5).setMaxWidth(0);
				lblsexta.setVisible(false);
				table.getColumnModel().getColumn(6).setMinWidth(0);
				table.getColumnModel().getColumn(6).setMaxWidth(0);
				lblsabado.setText("");
				lblsabado.setText("<html>Sabado<br>"
						+ CTAgendaConsulta.getDateAuto(calendar.getDate(), calendar.getDate().getDate()));
				carregarTableDia(6);
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro selecione um Medico", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
