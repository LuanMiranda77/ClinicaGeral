package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import CONTROL.CTAgendaProf;
import CONTROL.CTEspecialidade;
import CONTROL.CTProcedimento;
import CONTROL.CTProfissional;
import CONTROL.JavaMail;
import MODEL.Dia;
import MODEL.Procedimento;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;

public class TelaCadProf extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtemail;
	private JTextField txtmat;
	private MaskFormatter fone = null;
	private MaskFormatter fonefix = null;
	private DefaultTableModel modelo1;
	private static DefaultTableModel modelo2;
	private JCheckBox domingo;
	private JCheckBox segunda;
	private JCheckBox terca;
	private JCheckBox quarta;
	private JCheckBox quinta;
	private JCheckBox sexta;
	private JCheckBox sabado;
	private JComboBox<String> cbxagenda;
	private JTable tbAgenda;
	private JTable tbProcedimento;
	private static ArrayList<String> listaProced = new ArrayList<>();
	private BufferedImage imagem = null;
	private JLabel foto;
	private String localfoto = "";
	private Profissional prof;
	private JComboBox<String> cbxespecialidade;
	private JFormattedTextField txtcelular;
	private JFormattedTextField txtfixo;
	private JFormattedTextField txtrecado;

	public TelaCadProf(String matricula) {
		setTitle("Ficha de cadastro de profissional");
		setResizable(false);// seuJFrame
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setLocationRelativeTo(null);
		ModeloTabela.ativarModeloTela(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 50, 715, 699);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setBounds(10, 15, 186, 16);
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));

		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(160, 31, 97, 16);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 11));

		try {
			fone = new MaskFormatter("(##)#.####-####");
			fonefix = new MaskFormatter("(##)####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtnome = new JTextFieldSoLetras();
		txtnome.setBounds(158, 47, 354, 30);
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);

		cbxespecialidade = new JComboBox<String>();
		cbxespecialidade.setModel(new DefaultComboBoxModel<String>(CTEspecialidade.getListaNomes()));
		cbxespecialidade.setBounds(21, 99, 378, 30);
		cbxespecialidade.setFont(new Font("Masque", Font.PLAIN, 11));

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(22, 84, 377, 16);
		lblEspecialidade.setForeground(Color.BLACK);
		lblEspecialidade.setFont(new Font("Masque", Font.PLAIN, 11));

		JButton bntcarregar = new JButton("");
		bntcarregar.setIcon(new ImageIcon("icon\\clip.png"));
		bntcarregar.setBounds(657, 95, 32, 28);
		bntcarregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Imagens em JPEG", "jpg", "jpeg");
				FileFilter filter2 = new FileNameExtensionFilter("Imagens em PNG", "png");
				CortaImagem corte = new CortaImagem();
				fileChooser.setDialogTitle("Importar Imagem");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.addChoosableFileFilter(filter2);

				if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
					File arquivo = fileChooser.getSelectedFile();// arquivo
					try {
						imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
						foto.setIcon(new ImageIcon(corte.setCorte(imagem)));// seta no jlabel
						localfoto = fileChooser.getSelectedFile().getPath();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}

			}
		});

		JButton bntdel = new JButton("");
		bntdel.setIcon(new ImageIcon("icon\\del.png"));
		bntdel.setBounds(657, 123, 32, 28);
		bntdel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foto.setIcon(new ImageIcon("icon/fundfoto.png"));

			}
		});

		JLabel label_8 = new JLabel("CONTATO");
		label_8.setBounds(10, 135, 186, 16);
		label_8.setForeground(new Color(0, 102, 51));
		label_8.setFont(new Font("Masque", Font.PLAIN, 13));

		JLabel label_9 = new JLabel("TEL.CELULAR:");
		label_9.setBounds(20, 151, 126, 16);
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Masque", Font.PLAIN, 11));

		txtcelular = new JFormattedTextField(fone);
		txtcelular.setBounds(20, 168, 126, 30);
		txtcelular.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel label_10 = new JLabel("TEL.RESIDENCIAL:");
		label_10.setBounds(156, 151, 126, 16);
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Masque", Font.PLAIN, 11));

		txtfixo = new JFormattedTextField(fonefix);
		txtfixo.setBounds(156, 168, 126, 30);
		txtfixo.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel label_11 = new JLabel("TEL.RECADO:");
		label_11.setBounds(292, 151, 126, 16);
		label_11.setForeground(Color.BLACK);
		label_11.setFont(new Font("Masque", Font.PLAIN, 11));

		txtrecado = new JFormattedTextField(fone);
		txtrecado.setBounds(292, 168, 126, 30);
		txtrecado.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtemail = new JTextField();
		txtemail.setBounds(425, 167, 229, 30);
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtemail.setColumns(10);

		JLabel label_12 = new JLabel("e-mail:");
		label_12.setBounds(425, 151, 229, 16);
		label_12.setForeground(Color.BLACK);
		label_12.setFont(new Font("Masque", Font.PLAIN, 11));

		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(21, 31, 80, 16);
		lblMatricula.setForeground(Color.BLACK);
		lblMatricula.setFont(new Font("Masque", Font.PLAIN, 11));

		txtmat = new JTextField();
		txtmat.setBounds(20, 48, 126, 30);
		txtmat.setForeground(Color.GREEN);
		txtmat.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtmat.setColumns(10);

		JLabel label_6 = new JLabel("Foto 100x120");
		label_6.setBounds(554, 11, 100, 16);
		label_6.setOpaque(true);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_6.setBackground(Color.ORANGE);

		foto = new JLabel("");
		foto.setIcon(new ImageIcon("icon\\fundfoto.png"));
		foto.setBounds(554, 26, 100, 120);
		foto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.ORANGE, Color.ORANGE));

		JButton bntcadespec = new JButton("");
		bntcadespec.setIcon(new ImageIcon("icon\\mais.png"));
		bntcadespec.setBounds(400, 99, 32, 30);
		bntcadespec.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaCadEspecialidade();

			}
		});

		JLabel lblProcedimento = new JLabel("Selecione uma Agenda");
		lblProcedimento.setBounds(10, 367, 272, 16);
		lblProcedimento.setForeground(new Color(0, 102, 51));
		lblProcedimento.setFont(new Font("Masque", Font.PLAIN, 13));

		JPanel panel = new JPanel();
		panel.setBounds(21, 391, 668, 37);
		panel.setBorder(new LineBorder(new Color(255, 200, 0), 3));
		panel.setBackground(new Color(102, 204, 255));
		panel.setLayout(null);

		domingo = new JCheckBox("Domingo");
		domingo.setBounds(2, 9, 90, 18);
		domingo.setSelected(false);
		domingo.setBackground(new Color(102, 204, 255));
		panel.add(domingo);
		domingo.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel label_3 = new JLabel(" ");
		label_3.setBounds(93, 8, 4, 19);
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_3.setBackground(new Color(0, 204, 153));
		panel.add(label_3);

		segunda = new JCheckBox("Segunda");
		segunda.setBounds(98, 9, 87, 18);
		segunda.setSelected(true);
		segunda.setFont(new Font("Georgia", Font.BOLD, 14));
		segunda.setBackground(new Color(102, 204, 255));
		panel.add(segunda);

		JLabel label_2 = new JLabel(" ");
		label_2.setBounds(185, 8, 4, 19);
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_2.setBackground(new Color(0, 204, 153));
		panel.add(label_2);

		terca = new JCheckBox("Ter\u00E7a");
		terca.setBounds(197, 9, 66, 18);
		terca.setSelected(true);
		terca.setFont(new Font("Georgia", Font.BOLD, 15));
		terca.setBackground(new Color(102, 204, 255));
		panel.add(terca);

		quarta = new JCheckBox("Quarta");
		quarta.setBounds(284, 8, 76, 18);
		quarta.setSelected(true);
		quarta.setFont(new Font("Georgia", Font.BOLD, 15));
		quarta.setBackground(new Color(102, 204, 255));
		panel.add(quarta);

		JLabel label_4 = new JLabel(" ");
		label_4.setBounds(372, 8, 4, 19);
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_4.setBackground(new Color(0, 204, 153));
		panel.add(label_4);

		quinta = new JCheckBox("Quinta");
		quinta.setBounds(380, 9, 74, 18);
		quinta.setSelected(true);
		quinta.setFont(new Font("Georgia", Font.BOLD, 15));
		quinta.setBackground(new Color(102, 204, 255));
		panel.add(quinta);

		JLabel label_5 = new JLabel(" ");
		label_5.setBounds(466, 8, 4, 19);
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_5.setBackground(new Color(0, 204, 153));
		panel.add(label_5);

		sexta = new JCheckBox("Sexta");
		sexta.setBounds(482, 8, 64, 18);
		sexta.setSelected(true);
		sexta.setFont(new Font("Georgia", Font.BOLD, 15));
		sexta.setBackground(new Color(102, 204, 255));
		panel.add(sexta);

		JLabel label_13 = new JLabel(" ");
		label_13.setBounds(556, 8, 4, 19);
		label_13.setOpaque(true);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(Color.BLACK);
		label_13.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_13.setBackground(new Color(0, 204, 153));
		panel.add(label_13);

		sabado = new JCheckBox("S\u00E1bado");
		sabado.setSelected(true);
		sabado.setBounds(566, 9, 79, 18);
		sabado.setFont(new Font("Georgia", Font.BOLD, 15));
		sabado.setBackground(new Color(102, 204, 255));
		panel.add(sabado);

		JButton bntsalvar = new JButton("salvar");
		bntsalvar.setIcon(new ImageIcon("icon\\sal.png"));
		bntsalvar.setBounds(440, 593, 113, 39);
		bntsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		bntsalvar.setFont(new Font("Masque", Font.BOLD, 11));
		bntsalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matricula.equals("")) {
					prof = new Profissional();
				}
				prof.setMatricula(txtmat.getText());
				prof.setNome(txtnome.getText().toUpperCase());

				prof.setEspecialidade(CTEspecialidade.get("" + cbxespecialidade.getSelectedItem()).getCbo());
				prof.setCelular1(txtcelular.getText());
				prof.setTelFixo(txtfixo.getText());
				prof.setCelular2(txtrecado.getText());
				prof.setEmil(txtemail.getText());
				prof.setProcedimento(listaProced);
				prof.setFoto(localfoto);
				prof.setAgenda((String) cbxagenda.getSelectedItem());

				if (!txtmat.getText().equals("") && !txtnome.getText().equals("")
						&& !cbxespecialidade.getSelectedItem().equals("-") && !cbxagenda.getSelectedItem().equals("-")
						&& listaProced.size() != 0) {
					if (matricula.equals("")) {
						try {
							if(!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText())==false) {
									JOptionPane.showMessageDialog(null, "Email inválido","Erro de Email",JOptionPane.ERROR_MESSAGE);
							}
							else {
							CTProfissional.insert(prof);
							JOptionPane.showMessageDialog(null, "Profissional cadastrado com sucesso!");
							TelaGerProf.addLinha(prof);
							JOptionPane.showMessageDialog(null,
									"Atenção ao cadastrar um medico é necessario criar o usuario do mesmo !",
									"Informação", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							new TelaCadFunc(0, "");
							}
						} catch (SQLException e1) {
							new TelaErroLog(e1.getMessage(), "Erro no insert", "telacadMedico");
						}

					} else {
						if(!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText())==false) {
						
								JOptionPane.showMessageDialog(null, "Email inválido","Erro de Email",JOptionPane.ERROR_MESSAGE);
						}
						else {
						CTProfissional.update(prof);
						JOptionPane.showMessageDialog(null, "Profissional Alterado com sucesso!");
						dispose();
						new TelaGerProf();
					}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Erro Campo em branco", "Erro de campo",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JButton bntcancela = new JButton("cancelar");
		bntcancela.setIcon(new ImageIcon("icon\\del.png"));
		bntcancela.setBounds(554, 593, 130, 39);
		bntcancela.setHorizontalAlignment(SwingConstants.LEFT);
		bntcancela.setFont(new Font("Masque", Font.BOLD, 11));
		bntcancela.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		cbxagenda = new JComboBox<String>();
		cbxagenda.setBounds(21, 577, 342, 30);
		cbxagenda.setModel(new DefaultComboBoxModel<String>(CTAgendaProf.getListaNomes()));
		cbxagenda.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxagenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carga15();
			}
		});

		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(lblMatricula);
		contentPane.add(label_1);
		contentPane.add(txtmat);
		contentPane.add(txtnome);
		contentPane.add(cbxespecialidade);
		contentPane.add(lblEspecialidade);
		contentPane.add(bntcadespec);
		contentPane.add(label_8);
		contentPane.add(label_9);
		contentPane.add(label_10);
		contentPane.add(label_11);
		contentPane.add(foto);
		contentPane.add(label_6);
		contentPane.add(bntcarregar);
		contentPane.add(bntdel);
		contentPane.add(txtcelular);
		contentPane.add(txtfixo);
		contentPane.add(txtrecado);
		contentPane.add(label_12);
		contentPane.add(txtemail);
		contentPane.add(lblProcedimento);
		contentPane.add(cbxagenda);
		contentPane.add(panel);

		JComboBox<String> cbxprocedimento = new JComboBox<String>();
		cbxprocedimento.setModel(new DefaultComboBoxModel<String>(CTProcedimento.getListaNomes()));
		cbxprocedimento.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxprocedimento.setBounds(21, 217, 342, 30);
		contentPane.add(cbxprocedimento);

		JButton bntcadproced = new JButton("");
		bntcadproced.setIcon(new ImageIcon("icon\\mais.png"));
		bntcadproced.setBounds(437, 217, 32, 30);
		contentPane.add(bntcadproced);
		bntcadproced.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCadProcedimento();

			}
		});

		JButton bntaddproc = new JButton("");
		bntaddproc.setIcon(new ImageIcon("icon\\sal.png"));
		bntaddproc.setBounds(364, 217, 32, 30);
		contentPane.add(bntaddproc);
		bntaddproc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cbxprocedimento.getSelectedItem().equals("-")) {

					Procedimento novo = CTProcedimento.getProcedimento("" + cbxprocedimento.getSelectedItem());
					addProced(novo);

				}
			}
		});

		JButton bntdelporc = new JButton("");
		bntdelporc.setIcon(new ImageIcon("icon\\del.png"));
		bntdelporc.setBounds(400, 217, 32, 30);
		contentPane.add(bntdelporc);
		bntdelporc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = tbProcedimento.getSelectedRow();
				if (linha < 0)
					JOptionPane.showMessageDialog(null, "Erro selecione um item para remover!", "Tela informativa",
							JOptionPane.ERROR_MESSAGE);
				else
					modelo2.removeRow(linha);
				listaProced.remove(linha);

			}
		});

		JLabel label_7 = new JLabel(" ");
		label_7.setOpaque(true);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Rockwell", Font.BOLD, 15));
		label_7.setBackground(new Color(0, 204, 153));
		label_7.setBounds(275, 8, 4, 19);
		panel.add(label_7);
		contentPane.add(bntsalvar);
		contentPane.add(bntcancela);
		table();
		if (!matricula.equals("")) {
			preencherCampos(matricula);
		}
		setVisible(true);

	}

	public void table() {
		//// modelo da tabela;
		modelo1 = new DefaultTableModel();
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");
		modelo1.addColumn("");

		tbAgenda = new JTable(modelo1);
		tbAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbAgenda.setRowHeight(35);
		tbAgenda.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		tbAgenda.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		tbAgenda.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbAgenda.setAlignmentX(CENTER_ALIGNMENT);

		JScrollPane scrollPane = new JScrollPane(tbAgenda);
		scrollPane.setViewportBorder(new LineBorder(Color.ORANGE));
		scrollPane.setBounds(22, 417, 667, 153);
		contentPane.add(scrollPane);

		JLabel lblSelecioneOsProcedimento = new JLabel("Selecione os Procedimentos");
		lblSelecioneOsProcedimento.setForeground(new Color(0, 102, 51));
		lblSelecioneOsProcedimento.setFont(new Font("Masque", Font.PLAIN, 13));
		lblSelecioneOsProcedimento.setBounds(10, 200, 272, 16);
		contentPane.add(lblSelecioneOsProcedimento);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 249, 580, 110);
		contentPane.add(scrollPane_1);

		modelo2 = new DefaultTableModel();
		tbProcedimento = new JTable(modelo2);
		scrollPane_1.setViewportView(tbProcedimento);

		modelo2.addColumn("Cod");
		modelo2.addColumn("Descrição do procedimento");
		modelo2.addColumn("Valor");

		tbProcedimento.getColumnModel().getColumn(0).setPreferredWidth(75);
		tbProcedimento.getColumnModel().getColumn(1).setPreferredWidth(399);
		tbProcedimento.getColumnModel().getColumn(2).setPreferredWidth(100);

		tbProcedimento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbProcedimento.setRowHeight(15);
		tbProcedimento.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		tbProcedimento.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.ORANGE));
		tbProcedimento.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	public void carga15() {
		ArrayList<Dia> listaHora;
		if (tbAgenda != null) {
			limpatabela();
		}
		if (!cbxagenda.getSelectedItem().equals("-")) {
			listaHora = CTAgendaProf.getHorario("" + cbxagenda.getSelectedItem());

			for (int f = 0; f < listaHora.get(1).getHora().size(); f++) {
				modelo1.addRow(new Object[] { "", "", "", "", "", "", "" });
			}

			for (Dia i : listaHora) {
				if (i == listaHora.get(0) && i != null && domingo.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(0).getHora()) {
						modelo1.setValueAt(k, cont, 0);
						cont++;
					}
				}
				if (i == listaHora.get(1) && i != null && segunda.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(1).getHora()) {
						modelo1.setValueAt(k, cont, 1);
						cont++;
					}
				}
				if (i == listaHora.get(2) && i != null && terca.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(2).getHora()) {
						modelo1.setValueAt(k, cont, 2);
						cont++;
					}
				}
				if (i == listaHora.get(3) && i != null && quarta.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(3).getHora()) {
						modelo1.setValueAt(k, cont, 3);
						cont++;
					}
				}
				if (i == listaHora.get(4) && i != null && quinta.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(4).getHora()) {
						modelo1.setValueAt(k, cont, 4);
						cont++;
					}
				}
				if (i == listaHora.get(5) && i != null && sexta.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(5).getHora()) {
						modelo1.setValueAt(k, cont, 5);
						cont++;
					}
				}
				if (i == listaHora.get(6) && i != null && sabado.isSelected()) {
					int cont = 0;
					for (String k : listaHora.get(6).getHora()) {
						modelo1.setValueAt(k, cont, 6);
						cont++;
					}
				}
			}
		}
	}

	public void limpatabela() {
		while (tbAgenda.getModel().getRowCount() > 0) {
			modelo1.removeRow(0);
		}

	}

	public static void addProced(Procedimento novo) {
		listaProced.add("" + novo.getId());
		modelo2.addRow(new Object[] { novo.getId(), novo.getDesc(), novo.getValor() });
	}

	public void preencherCampos(String matricula) {
		txtmat.setEditable(false);
		prof = CTProfissional.getMedico(matricula);
		txtmat.setText(prof.getMatricula());
		txtnome.setText(prof.getNome());
		cbxespecialidade.setSelectedItem(CTEspecialidade.getCbo(prof.getEspecialidade()).getNome());
		cbxagenda.setSelectedItem(prof.getAgenda());
		listaProced= new ArrayList<>();
		for (String p : prof.getProcedimento()) {
			Procedimento proced = CTProcedimento.getProcedimento(p);
			addProced(proced);
			CTProfissional.removerProced(prof.getMatricula(), proced.getId());
		}
		txtcelular.setText(prof.getCelular1());
		txtfixo.setText(prof.getTelFixo());
		txtrecado.setText(prof.getCelular2());
		txtemail.setText(prof.getEmil());
		
		CortaImagem corte = new CortaImagem();
		File arquivo = null;
		if (!prof.getFoto().equals("")) {
			arquivo = new File(prof.getFoto());
			try {
				imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
				foto.setIcon(new ImageIcon(corte.setCorte120x200(imagem)));
				localfoto = prof.getFoto();// seta no jlabel
			} catch (IOException ex) {

			}
		}

	}
}
