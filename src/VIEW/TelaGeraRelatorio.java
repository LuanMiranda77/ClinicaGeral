package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;
import com.itextpdf.text.DocumentException;
import CONTROL.CTProfissional;
import CONTROL.CTRelatorio;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;

public class TelaGeraRelatorio extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtiniciomed;
	private JTextField txtfimmed;
	private JTextField txtinciototal;
	private JTextField txtfimtotal;
	private JRadioButton rbtotal;
	private JRadioButton rbdia;
	private JRadioButton rbmedico;
	private JRadioButton rbcancelar;
	private MaskFormatter forma;
	private JComboBox<String> cbxMedico;


	public TelaGeraRelatorio() {
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.DARK_GRAY);
		setTitle("Gerar - Relatorios");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 728, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			forma = new MaskFormatter("##/##/####");
			forma.setValidCharacters("0123456789");
		} catch (ParseException e) {
			e.printStackTrace();
		}


		JLabel lblEscolhaOTipo = new JLabel("Escolha o tipo de relata\u00F3rio");
		lblEscolhaOTipo.setForeground(new Color(0, 102, 51));
		lblEscolhaOTipo.setFont(new Font("Masque", Font.PLAIN, 13));
		lblEscolhaOTipo.setBounds(258, 16, 266, 22);
		contentPane.add(lblEscolhaOTipo);

		rbdia = new JRadioButton("RELATORIO DO DIA");
		rbdia.setForeground(new Color(0, 0, 0));
		rbdia.setBackground(Color.WHITE);
		rbdia.setFont(new Font("Verdana", Font.BOLD, 15));
		rbdia.setBounds(22, 105, 208, 23);
		contentPane.add(rbdia);
		rbdia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rbmedico.setSelected(false);
				rbtotal.setSelected(false);
				rbcancelar.setSelected(false);
				txtiniciomed.setEnabled(false);
				txtfimmed.setEnabled(false);
				txtinciototal.setEnabled(false);
				txtfimtotal.setEnabled(false);
				cbxMedico.setEnabled(false);
			}
		});

		cbxMedico = new JComboBox<String>();
		cbxMedico.setEnabled(false);
		cbxMedico.setModel(new DefaultComboBoxModel<String>(CTProfissional.getListaNomes()));
		cbxMedico.setFont(new Font("Verdana", Font.BOLD, 12));
		cbxMedico.setBounds(404, 179, 289, 35);
		contentPane.add(cbxMedico);

		rbmedico = new JRadioButton("RELATORIO POR MEDICO");
		rbmedico.setForeground(Color.BLACK);
		rbmedico.setFont(new Font("Verdana", Font.BOLD, 15));
		rbmedico.setBackground(Color.WHITE);
		rbmedico.setBounds(407, 83, 286, 23);
		contentPane.add(rbmedico);
		rbmedico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbmedico.isSelected()) {
					txtfimmed.setEnabled(true);
					txtiniciomed.setEnabled(true);
					cbxMedico.setEnabled(true);
					txtiniciomed.requestFocus();
					rbdia.setSelected(false);
					rbtotal.setSelected(false);
					rbcancelar.setSelected(false);
					txtinciototal.setEnabled(false);
					txtfimtotal.setEnabled(false);
				} else {
					txtfimmed.setEnabled(false);
					txtiniciomed.setEnabled(false);
					cbxMedico.setEnabled(false);
				}

			}
		});

		rbtotal = new JRadioButton("RELATORIO DO TOTAL");
		rbtotal.setForeground(Color.BLACK);
		rbtotal.setFont(new Font("Verdana", Font.BOLD, 15));
		rbtotal.setBackground(Color.WHITE);
		rbtotal.setBounds(407, 240, 236, 23);
		contentPane.add(rbtotal);
		rbtotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rbtotal.isSelected()) {
					txtinciototal.setEnabled(true);
					txtfimtotal.setEnabled(true);
					txtinciototal.requestFocus();
					rbmedico.setSelected(false);
					rbcancelar.setSelected(false);
					rbdia.setSelected(false);
					txtiniciomed.setEnabled(false);
					txtfimmed.setEnabled(false);
					cbxMedico.setEnabled(false);
				} else {
					txtinciototal.setEnabled(false);
					txtfimtotal.setEnabled(false);
					

				}
			}
		});

		rbcancelar = new JRadioButton("RELATORIO CONSULTA CANCELADA");
		rbcancelar.setForeground(Color.BLACK);
		rbcancelar.setFont(new Font("Verdana", Font.BOLD, 15));
		rbcancelar.setBackground(Color.WHITE);
		rbcancelar.setBounds(22, 154, 325, 23);
		contentPane.add(rbcancelar);
		rbcancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rbmedico.setSelected(false);
				rbtotal.setSelected(false);
				rbdia.setSelected(false);
				txtiniciomed.setEnabled(false);
				txtfimmed.setEnabled(false);
				txtinciototal.setEnabled(false);
				txtfimtotal.setEnabled(false);
				cbxMedico.setEnabled(false);
				
			}
		});

		JLabel lblDataInicial = new JLabel("DATA INICIAL:");
		lblDataInicial.setForeground(Color.BLACK);
		lblDataInicial.setFont(new Font("Masque", Font.PLAIN, 12));
		lblDataInicial.setBounds(410, 108, 106, 16);
		contentPane.add(lblDataInicial);


		txtiniciomed = new JFormattedTextField(forma);
		txtiniciomed.setEnabled(false);
		txtiniciomed.setForeground(Color.BLACK);
		txtiniciomed.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtiniciomed.setColumns(10);
		txtiniciomed.setBounds(407, 125, 135, 32);
		contentPane.add(txtiniciomed);

		JLabel lblDataFinal = new JLabel("DATA FINAL:");
		lblDataFinal.setForeground(Color.BLACK);
		lblDataFinal.setFont(new Font("Masque", Font.PLAIN, 12));
		lblDataFinal.setBounds(561, 108, 106, 16);
		contentPane.add(lblDataFinal);

		txtfimmed = new JFormattedTextField(forma);
		txtfimmed.setEnabled(false);
		txtfimmed.setForeground(Color.BLACK);
		txtfimmed.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtfimmed.setColumns(10);
		txtfimmed.setBounds(558, 125, 135, 32);
		contentPane.add(txtfimmed);

		JLabel label = new JLabel("Medico Especialista:");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Masque", Font.PLAIN, 11));
		label.setBounds(407, 161, 247, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("DATA INICIAL:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 12));
		label_1.setBounds(410, 267, 106, 16);
		contentPane.add(label_1);

		txtinciototal = new JFormattedTextField(forma);
		txtinciototal.setEnabled(false);
		txtinciototal.setForeground(Color.BLACK);
		txtinciototal.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtinciototal.setColumns(10);
		txtinciototal.setBounds(407, 284, 135, 32);
		contentPane.add(txtinciototal);

		JLabel label_2 = new JLabel("DATA FINAL:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Masque", Font.PLAIN, 12));
		label_2.setBounds(561, 267, 106, 16);
		contentPane.add(label_2);

		txtfimtotal = new JFormattedTextField(forma);
		txtfimtotal.setEnabled(false);
		txtfimtotal.setForeground(Color.BLACK);
		txtfimtotal.setFont(new Font("SansSerif", Font.BOLD, 20));
		txtfimtotal.setColumns(10);
		txtfimtotal.setBounds(558, 284, 135, 32);
		contentPane.add(txtfimtotal);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(378, 50, 10, 266);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 102, 51), new Color(0, 102, 51)));
		contentPane.add(lblNewLabel);

		JLabel lblRelatorioSimples = new JLabel("Relatorio Simples");
		lblRelatorioSimples.setForeground(new Color(0, 102, 51));
		lblRelatorioSimples.setFont(new Font("Masque", Font.PLAIN, 13));
		lblRelatorioSimples.setBounds(75, 50, 266, 22);
		contentPane.add(lblRelatorioSimples);

		JLabel lblRelatorioPorDatas = new JLabel("Relatorio por Datas");
		lblRelatorioPorDatas.setForeground(new Color(0, 102, 51));
		lblRelatorioPorDatas.setFont(new Font("Masque", Font.PLAIN, 13));
		lblRelatorioPorDatas.setBounds(469, 50, 216, 22);
		contentPane.add(lblRelatorioPorDatas);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.setIcon(new ImageIcon("C:\\Users\\luanp\\eclipse-workspace\\AppClinica 2.0\\icon\\sal.png"));
		btnGerar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGerar.setFont(new Font("Marcellus SC", Font.BOLD, 15));
		btnGerar.setBounds(258, 344, 118, 35);
		contentPane.add(btnGerar);
		btnGerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					if (rbdia.isSelected()) {
						try {
							Date d = new Date();
							String hoje = DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
							
                            new TelaLoanding();
							RelatorioFinanceiroPdf.gerarHistorico(CTRelatorio.geraRelatorioFinanceiroDia(), hoje,"");
						} catch (DocumentException | ParseException | IOException e1) {
							new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
						}
					}
					else if(rbcancelar.isSelected()) {
						try {
							 new TelaLoanding();
							RelatorioFinanceiroPdf.gerarHistorico(CTRelatorio.geraRelatorioConsultasCancelas(),"","");
						} catch (DocumentException | ParseException | IOException e1) {
							new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
						}
					}
					else if(rbmedico.isSelected()) {
						boolean band = false;
						if(!ComandText.dataValida(txtiniciomed)) {
							band=true;
							JOptionPane.showMessageDialog(null, "Data inicial Inválida","Erro de Data",JOptionPane.ERROR_MESSAGE);
						}
						else if(!ComandText.dataValida(txtfimmed)) {
							band=true;
							JOptionPane.showMessageDialog(null, "Data final Inválida","Erro de Data",JOptionPane.ERROR_MESSAGE);
						}
						try {
						if(band==false) {
							
						Profissional medico = CTProfissional.get(""+cbxMedico.getSelectedItem());
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						Date inicio;
						
							inicio = new Date(format.parse(txtiniciomed.getText()).getTime());
							Date fim = new Date(format.parse(txtfimmed.getText()).getTime());
							try {
								 new TelaLoanding();
								RelatorioFinanceiroPorMedicoPdf.gerarHistorico(CTRelatorio.geraRelatorioConsultaPorMedico(medico, inicio, fim),
										medico,txtiniciomed.getText(),txtfimmed.getText());
							} catch (DocumentException | IOException e1) {
								new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
							}
						}
							
						} catch (ParseException e1) {
							new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
						}
						
					}
					else {
						boolean band = false;
						
							if(!ComandText.dataValida(txtinciototal)) {
								band=true;
								JOptionPane.showMessageDialog(null, "Data inicial Inválida","Erro de Data",JOptionPane.ERROR_MESSAGE);
							}
							else if(!ComandText.dataValida(txtfimtotal)) {
								band=true;
								JOptionPane.showMessageDialog(null, "Data final Inválida","Erro de Data",JOptionPane.ERROR_MESSAGE);
							}
						try {
						if(band==false) {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						Date inicio = new Date(format.parse(txtinciototal.getText()).getTime());
						Date fim;
					
							fim = new Date(format.parse(txtfimtotal.getText()).getTime());
							try {
								 new TelaLoanding();
								RelatorioFinanceiroPdf.gerarHistorico(CTRelatorio.geraRelatorioFinanceiroTotalConsulta(inicio, fim),
										txtinciototal.getText(),txtfimtotal.getText());
							} catch (DocumentException | IOException e1) {
								new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
							}
						}
						} catch (ParseException e1) {
							new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
						}
						
					}
				} catch (SQLException e1) {
					new TelaErroLog(e1.getMessage(), "erro relatorio", getTitle());
				}

			}
		});

		JButton bntCancelar = new JButton("Cancelar");
		bntCancelar.setIcon(new ImageIcon("C:\\Users\\luanp\\eclipse-workspace\\AppClinica 2.0\\icon\\del.png"));
		bntCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		bntCancelar.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		bntCancelar.setBounds(382, 344, 118, 35);
		contentPane.add(bntCancelar);
		bntCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		setVisible(true);
	}
	
}
