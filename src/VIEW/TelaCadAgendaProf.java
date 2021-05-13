package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import CONTROL.CTAgendaProf;
import MODEL.AgendaProf;
import MODEL.Dia;
import VIEW.tabela.ModeloTabela;

public class TelaCadAgendaProf extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private MaskFormatter hora;
	private JTable tbsegunda;
	private JTable tbterca;
	private JTable tbquarta;
	private JTable tbquinta;
	private JTable tbsexta;
	private JTable tbsabado;
	private JTable tbdomingo;
	private DefaultTableModel modelo1;
	private DefaultTableModel modelo2;
	private DefaultTableModel modelo3;
	private DefaultTableModel modelo4;
	private DefaultTableModel modelo5;
	private DefaultTableModel modelo6;
	private DefaultTableModel modelo7;
	private Dia segunda = new Dia();
	private Dia terca= new Dia();;
	private Dia quarta= new Dia();;
	private Dia quinta= new Dia();;
	private Dia sexta= new Dia();;
	private Dia sabado= new Dia();;
	private Dia domingo= new Dia();
	private AgendaProf agenda;
	
	public TelaCadAgendaProf() {
		setTitle("Ficha de Cadastro Agenda");
		setResizable(false);// seuJFrame
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setLocationRelativeTo(null);
		ModeloTabela.ativarModeloTela(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 100, 867, 407);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		modelo1 = new DefaultTableModel();
		modelo1.addColumn("");
		modelo2 = new DefaultTableModel();
		modelo2.addColumn("");
		modelo3 = new DefaultTableModel();
		modelo3.addColumn("");
		modelo4 = new DefaultTableModel();
		modelo4.addColumn("");
		modelo5 = new DefaultTableModel();
		modelo5.addColumn("");
		modelo6 = new DefaultTableModel();
		modelo6.addColumn("");
		modelo7 = new DefaultTableModel();
		modelo7.addColumn("");

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(6, 10, 186, 16);
		contentPane.add(label);

		JLabel lblNomeDaAgenda = new JLabel("Nome da agenda:");
		lblNomeDaAgenda.setForeground(Color.BLACK);
		lblNomeDaAgenda.setFont(new Font("Masque", Font.PLAIN, 10));
		lblNomeDaAgenda.setBounds(19, 25, 139, 16);
		contentPane.add(lblNomeDaAgenda);

		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);
		txtnome.setBounds(16, 41, 277, 25);
		contentPane.add(txtnome);
		txtnome.requestFocus();

		tbsegunda = new JTable(modelo1);
		JScrollPane scrollPane = new JScrollPane(tbsegunda);
		scrollPane.setBounds(16, 123, 76, 197);
		contentPane.add(scrollPane);
		tbsegunda.setRowHeight(20);
		tbsegunda.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbsegunda.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));

		JLabel lblSegunda = new JLabel("Segunda");
		lblSegunda.setForeground(Color.ORANGE);
		lblSegunda.setFont(new Font("Masque", Font.PLAIN, 13));
		lblSegunda.setBounds(16, 72, 76, 16);
		contentPane.add(lblSegunda);

		try {
			hora = new MaskFormatter("##:##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hora.setValidCharacters("0123456789");

		JFormattedTextField txtsegunda = new JFormattedTextField((hora));
		txtsegunda.setHorizontalAlignment(SwingConstants.CENTER);
		txtsegunda.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtsegunda.setBounds(16, 92, 76, 25);
		contentPane.add(txtsegunda);

		JButton bnts1 = new JButton("");
		bnts1.setIcon(new ImageIcon("icon\\sal.png"));
		bnts1.setBounds(94, 92, 32, 25);
		contentPane.add(bnts1);
		bnts1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtsegunda.getText().equals("  :  ")) {
					lista = txtsegunda.getText().split(":");

					if (testHora(lista)) {
						modelo1.addRow(new Object[] { txtsegunda.getText() });
						segunda.addHora(txtsegunda.getText());
						txtsegunda.setText("");
						txtsegunda.requestFocus();
					}
				}

			}
		});

		JFormattedTextField txtterca = new JFormattedTextField(hora);
		txtterca.setHorizontalAlignment(SwingConstants.CENTER);
		txtterca.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtterca.setBounds(130, 92, 76, 25);
		contentPane.add(txtterca);

		JButton bntd1 = new JButton("");
		bntd1.setIcon(new ImageIcon("icon\\del.png"));
		bntd1.setBounds(94, 120, 32, 25);
		contentPane.add(bntd1);
		bntd1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbsegunda.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo1.removeRow(linha);
				segunda.removerHora(linha);

			}
		});

		JLabel lblTerca = new JLabel("Ter\u00E7a");
		lblTerca.setForeground(Color.ORANGE);
		lblTerca.setFont(new Font("Masque", Font.PLAIN, 13));
		lblTerca.setBounds(130, 72, 76, 16);
		contentPane.add(lblTerca);

		tbterca = new JTable(modelo2);
		JScrollPane scrollPane_1 = new JScrollPane(tbterca);
		scrollPane_1.setBounds(130, 123, 76, 197);
		contentPane.add(scrollPane_1);

		tbterca.setRowHeight(20);
		tbterca.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbterca.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));

		JFormattedTextField txtquarta = new JFormattedTextField(hora);
		txtquarta.setHorizontalAlignment(SwingConstants.CENTER);
		txtquarta.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtquarta.setBounds(249, 92, 76, 25);
		contentPane.add(txtquarta);

		JButton bnts2 = new JButton();
		bnts2.setIcon(new ImageIcon("icon\\sal.png"));
		bnts2.setBounds(208, 92, 32, 25);
		contentPane.add(bnts2);
		bnts2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtterca.getText().equals("  :  ")) {
					lista = txtterca.getText().split(":");

					if (testHora(lista)) {
						modelo2.addRow(new Object[] { txtterca.getText() });
						terca.addHora(txtterca.getText());
						txtterca.setText("");
						txtterca.requestFocus();
					}
				}

			}
		});

		JButton bntd2 = new JButton(new ImageIcon("icon\\del.png"));
		bntd2.setBounds(208, 120, 32, 25);
		contentPane.add(bntd2);
		bntd2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbterca.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo2.removeRow(linha);
				terca.removerHora(linha);

			}
		});

		JLabel lblQuarta = new JLabel("Quarta");
		lblQuarta.setForeground(Color.ORANGE);
		lblQuarta.setFont(new Font("Masque", Font.PLAIN, 13));
		lblQuarta.setBounds(249, 72, 76, 16);
		contentPane.add(lblQuarta);

		tbquarta = new JTable(modelo3);
		JScrollPane scrollPane_2 = new JScrollPane(tbquarta);
		scrollPane_2.setBounds(249, 123, 76, 197);
		contentPane.add(scrollPane_2);

		tbquarta.setRowHeight(20);
		tbquarta.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbquarta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));

		JButton bnts3 = new JButton();
		bnts3.setIcon(new ImageIcon("icon\\sal.png"));
		bnts3.setBounds(327, 92, 32, 25);
		contentPane.add(bnts3);
		bnts3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtquarta.getText().equals("  :  ")) {
					lista = txtquarta.getText().split(":");

					if (testHora(lista)) {
						modelo3.addRow(new Object[] { txtquarta.getText() });
						quarta.addHora(txtquarta.getText());
						txtquarta.setText("");
						txtquarta.requestFocus();
					}
				}

			}
		});

		JButton bntd3 = new JButton(new ImageIcon("icon\\del.png"));
		bntd3.setBounds(327, 120, 32, 25);
		contentPane.add(bntd3);
		bntd3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbquarta.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo3.removeRow(linha);
				quarta.removerHora(linha);

			}
		});

		JLabel lblQuinta = new JLabel("Quinta");
		lblQuinta.setForeground(Color.ORANGE);
		lblQuinta.setFont(new Font("Masque", Font.PLAIN, 13));
		lblQuinta.setBounds(371, 72, 76, 16);
		contentPane.add(lblQuinta);

		JFormattedTextField txtquinta = new JFormattedTextField(hora);
		txtquinta.setHorizontalAlignment(SwingConstants.CENTER);
		txtquinta.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtquinta.setBounds(371, 92, 76, 25);
		contentPane.add(txtquinta);

		tbquinta = new JTable(modelo4);
		JScrollPane scrollPane_3 = new JScrollPane(tbquinta);
		scrollPane_3.setBounds(371, 123, 76, 197);
		contentPane.add(scrollPane_3);
		tbquinta.setRowHeight(20);
		tbquinta.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbquinta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));

		JButton bnts4 = new JButton("");
		bnts4.setIcon(new ImageIcon("icon\\sal.png"));
		bnts4.setBounds(449, 92, 32, 25);
		contentPane.add(bnts4);
		bnts4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtquinta.getText().equals("  :  ")) {
					lista = txtquinta.getText().split(":");

					if (testHora(lista)) {
						modelo4.addRow(new Object[] { txtquinta.getText() });
						quinta.addHora(txtquinta.getText());
						txtquinta.setText("");
						txtquinta.requestFocus();
					}
				}

			}
		});

		JButton bntd4 = new JButton(new ImageIcon("icon\\del.png"));
		bntd4.setBounds(449, 120, 32, 25);
		contentPane.add(bntd4);
		bntd4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbquinta.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo4.removeRow(linha);
				quinta.removerHora(linha);

			}
		});

		JLabel lblSexta = new JLabel("Sexta");
		lblSexta.setForeground(Color.ORANGE);
		lblSexta.setFont(new Font("Masque", Font.PLAIN, 13));
		lblSexta.setBounds(493, 72, 76, 16);
		contentPane.add(lblSexta);

		JFormattedTextField txtsexta = new JFormattedTextField(hora);
		txtsexta.setHorizontalAlignment(SwingConstants.CENTER);
		txtsexta.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtsexta.setBounds(493, 92, 76, 25);
		contentPane.add(txtsexta);

		JButton bnts5 = new JButton("");
		bnts5.setIcon(new ImageIcon("icon\\sal.png"));
		bnts5.setBounds(571, 92, 32, 25);
		contentPane.add(bnts5);
		bnts5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtsexta.getText().equals("  :  ")) {
					lista = txtsexta.getText().split(":");

					if (testHora(lista)) {
						modelo5.addRow(new Object[] { txtsexta.getText() });
						sexta.addHora(txtsexta.getText());
						txtsexta.setText("");
						txtsexta.requestFocus();
					}
				}

			}
		});

		JButton bntd5 = new JButton(new ImageIcon("icon\\del.png"));
		bntd5.setBounds(571, 120, 32, 25);
		contentPane.add(bntd5);
		bntd5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbsexta.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo5.removeRow(linha);
				sexta.removerHora(linha);

			}
		});

		tbsexta = new JTable(modelo5);
		JScrollPane scrollPane_4 = new JScrollPane(tbsexta);
		scrollPane_4.setBounds(493, 123, 76, 197);
		contentPane.add(scrollPane_4);
		tbsexta.setRowHeight(20);
		tbsexta.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbsexta.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));

		JLabel lblSabado = new JLabel("Sabado");
		lblSabado.setForeground(Color.ORANGE);
		lblSabado.setFont(new Font("Masque", Font.PLAIN, 13));
		lblSabado.setBounds(611, 72, 76, 16);
		contentPane.add(lblSabado);

		JFormattedTextField txtsabado = new JFormattedTextField(hora);
		txtsabado.setHorizontalAlignment(SwingConstants.CENTER);
		txtsabado.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtsabado.setBounds(611, 92, 76, 25);
		contentPane.add(txtsabado);

		JButton bnts6 = new JButton("");
		bnts6.setIcon(new ImageIcon("icon\\sal.png"));
		bnts6.setBounds(689, 92, 32, 25);
		contentPane.add(bnts6);
		bnts6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;

				if (!txtsabado.getText().equals("  :  ")) {
					lista = txtsabado.getText().split(":");

					if (testHora(lista)) {
						modelo6.addRow(new Object[] { txtsabado.getText() });
						sabado.addHora(txtsabado.getText());
						txtsabado.setText("");
						txtsabado.requestFocus();
					}
				}

			}
		});

		JButton bntd6 = new JButton(new ImageIcon("icon\\del.png"));
		bntd6.setBounds(689, 120, 32, 25);
		contentPane.add(bntd6);
		bntd6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbsegunda.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo6.removeRow(linha);
					sabado.removerHora(linha);

			}
		});

		tbsabado = new JTable(modelo6);
		JScrollPane srcoll = new JScrollPane(tbsabado);
		srcoll.setBounds(611, 123, 76, 197);
		tbsabado.setRowHeight(20);
		tbsabado.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbsabado.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		contentPane.add(srcoll);

		JLabel lblDomingo = new JLabel("Domingo");
		lblDomingo.setForeground(Color.ORANGE);
		lblDomingo.setFont(new Font("Masque", Font.PLAIN, 13));
		lblDomingo.setBounds(733, 72, 76, 16);
		contentPane.add(lblDomingo);

		JFormattedTextField txtdomingo = new JFormattedTextField(hora);
		txtdomingo.setHorizontalAlignment(SwingConstants.CENTER);
		txtdomingo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtdomingo.setBounds(733, 92, 76, 25);
		contentPane.add(txtdomingo);

		tbdomingo = new JTable(modelo7);
		JScrollPane scrollPane_6 = new JScrollPane(tbdomingo);
		scrollPane_6.setBounds(733, 123, 76, 197);
		tbdomingo.setRowHeight(20);
		tbdomingo.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbdomingo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		contentPane.add(scrollPane_6);

		JButton bnts7 = new JButton("");
		bnts7.setIcon(new ImageIcon("icon\\sal.png"));
		bnts7.setBounds(811, 92, 32, 25);
		contentPane.add(bnts7);
		bnts7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] lista = null;
				if (!txtdomingo.getText().equals("  :  ")) {
					lista = txtdomingo.getText().split(":");

					if (testHora(lista)) {
						modelo7.addRow(new Object[] { txtdomingo.getText() });
						domingo.addHora(txtdomingo.getText());
						txtdomingo.setText("");
						txtdomingo.requestFocus();
					}
				}

			}
		});

		JButton bntd7 = new JButton(new ImageIcon("icon\\del.png"));
		bntd7.setBounds(811, 120, 32, 25);
		contentPane.add(bntd7);
		bntd7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbsegunda.getSelectedRow();

				if (linha < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma hora para remoção");
				} else
					modelo7.removeRow(linha);
				    domingo.removerHora(linha);

			}
		});

		JButton button_14 = new JButton("Salvar");
		button_14.setIcon(new ImageIcon("icon\\sal.png"));
		button_14.setHorizontalAlignment(SwingConstants.LEFT);
		button_14.setFont(new Font("Masque", Font.BOLD, 11));
		button_14.setBounds(597, 332, 113, 39);
		contentPane.add(button_14);
		button_14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtnome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "erro digite o nome da agenda", "erro sistema",
							JOptionPane.ERROR_MESSAGE);
				} else {
					agenda= new AgendaProf();
					agenda.setNome(txtnome.getText().toUpperCase());
					agenda.setDomingo(domingo);
					agenda.setSegunda(segunda);
					agenda.setTerca(terca);
					agenda.setQuarta(quarta);
					agenda.setQuinta(quinta);
					agenda.setSexta(sexta);
					agenda.setSabado(sabado);
					CTAgendaProf.insert(agenda);
					JOptionPane.showMessageDialog(null, "Agenda cadastrada com sucesso!");
					agenda=null;
					dispose();
				}

			}
		});

		JButton button_15 = new JButton("Cancelar");
		button_15.setIcon(new ImageIcon("icon\\del.png"));
		button_15.setHorizontalAlignment(SwingConstants.LEFT);
		button_15.setFont(new Font("Masque", Font.BOLD, 11));
		button_15.setBounds(713, 332, 130, 39);
		contentPane.add(button_15);
		button_15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		setVisible(true);
	}

	public boolean testHora(String[] lista) {
		int hora = Integer.parseInt(lista[0]);
		int minuto = Integer.parseInt(lista[1]);

		if (hora <= 23 && hora >= 00 && minuto < 60 && minuto >= 00) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Hora inválida", "erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}
