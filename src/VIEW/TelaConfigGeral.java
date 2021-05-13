package VIEW;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import CONTROL.CTConfig;
import CONTROL.JavaMail;
import MODEL.Config;
import VIEW.tabela.ModeloTabela;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class TelaConfigGeral extends Principal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Config config = CTConfig.getConfig();
	private JPanel contentPane;
	private JTextField txtemail;
	private JTextField txtconfirme;
	private JTextField txtservidor;
	private JTextField txtporta;
	private JPasswordField txtsenha;
	private JTextField txtsenhaview;
	private JTextField txturl;
	private JTextField txtuser;
	private JPasswordField txtsenhabd;
	private JPasswordField txtsenhalib;
	private JCheckBox chbzap;
	private JTextField txttitulo;
	private JTextField txtdest;
	private JTextField txtdata;
	private JTextField txthora;
	private JTextField txtmsa;
	private JTextField txtmsb;


	public TelaConfigGeral(int op) {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Tela de configuração");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 538, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 6, 513, 342);
		contentPane.add(tabbedPane);

		JPanel panel_Email = new JPanel();
		panel_Email.setBackground(Color.WHITE);
		tabbedPane.addTab("E-mail ", new ImageIcon("icon\\emailp.png"), panel_Email, null);
		panel_Email.setLayout(null);

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setBounds(6, 5, 177, 16);
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		panel_Email.add(label);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(16, 24, 170, 13);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Masque", Font.PLAIN, 11));
		panel_Email.add(lblEmail);

		txtemail = new JTextField(config.getEmail());
		txtemail.setBounds(16, 36, 261, 32);
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtemail.setColumns(10);
		panel_Email.add(txtemail);

		txtconfirme = new JTextField(config.getEmail());
		txtconfirme.setForeground(Color.BLACK);
		txtconfirme.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtconfirme.setColumns(10);
		txtconfirme.setBounds(16, 92, 261, 32);
		panel_Email.add(txtconfirme);

		JLabel lblConfirmeEmail = new JLabel("Confirme Email:");
		lblConfirmeEmail.setForeground(Color.BLACK);
		lblConfirmeEmail.setFont(new Font("Masque", Font.PLAIN, 11));
		lblConfirmeEmail.setBounds(16, 80, 261, 13);
		panel_Email.add(lblConfirmeEmail);

		JLabel lblServidorDoEmail = new JLabel("Servidor do Email:");
		lblServidorDoEmail.setForeground(Color.BLACK);
		lblServidorDoEmail.setFont(new Font("Masque", Font.PLAIN, 11));
		lblServidorDoEmail.setBounds(16, 136, 261, 13);
		panel_Email.add(lblServidorDoEmail);

		txtservidor = new JTextField(config.getServidor_Email());
		txtservidor.setForeground(Color.BLACK);
		txtservidor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtservidor.setColumns(10);
		txtservidor.setBounds(16, 148, 261, 32);
		panel_Email.add(txtservidor);

		JLabel lblPortaDoServidor = new JLabel("Porta do Servidor:");
		lblPortaDoServidor.setForeground(Color.BLACK);
		lblPortaDoServidor.setFont(new Font("Masque", Font.PLAIN, 11));
		lblPortaDoServidor.setBounds(16, 192, 261, 13);
		panel_Email.add(lblPortaDoServidor);

		txtporta = new JTextField(config.getPorta_Email());
		txtporta.setForeground(Color.BLACK);
		txtporta.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtporta.setColumns(10);
		txtporta.setBounds(16, 204, 117, 32);
		panel_Email.add(txtporta);

		JLabel lblSenhaEmail = new JLabel("Senha Email:");
		lblSenhaEmail.setForeground(Color.BLACK);
		lblSenhaEmail.setFont(new Font("Masque", Font.PLAIN, 11));
		lblSenhaEmail.setBounds(16, 249, 261, 13);
		panel_Email.add(lblSenhaEmail);

		JLabel lblgraasloftgmailcom = new JLabel("\"gra\u00E7asoft@gmail.com\"");
		lblgraasloftgmailcom.setForeground(Color.LIGHT_GRAY);
		lblgraasloftgmailcom.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblgraasloftgmailcom.setBounds(289, 41, 170, 23);
		panel_Email.add(lblgraasloftgmailcom);

		JLabel lblsmtpgmailcom = new JLabel("\"smtp.gmail.com\"");
		lblsmtpgmailcom.setForeground(Color.LIGHT_GRAY);
		lblsmtpgmailcom.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblsmtpgmailcom.setBounds(289, 146, 170, 23);
		panel_Email.add(lblsmtpgmailcom);

		JLabel label_1 = new JLabel("\"gra\u00E7asoft@gmail.com\"");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		label_1.setBounds(289, 101, 170, 23);
		panel_Email.add(label_1);

		JLabel lblOu = new JLabel("\"465 ou 587\"");
		lblOu.setForeground(Color.LIGHT_GRAY);
		lblOu.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblOu.setBounds(145, 213, 170, 23);
		panel_Email.add(lblOu);

		txtsenha = new JPasswordField(config.getSenha_Email());
		txtsenha.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtsenha.setBounds(16, 264, 167, 32);
		panel_Email.add(txtsenha);

		txtsenhaview = new JTextField(config.getSenha_Email());
		txtsenhaview.setForeground(Color.BLACK);
		txtsenhaview.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtsenhaview.setColumns(10);
		txtsenhaview.setBounds(16, 264, 167, 32);
		panel_Email.add(txtsenhaview);
		txtsenhaview.setVisible(false);

		JLabel olho = new JLabel(new ImageIcon("Icon/olhof.png"));
		olho.setBounds(195, 256, 50, 50);
		panel_Email.add(olho);
		olho.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@SuppressWarnings("deprecation")
			public void mousePressed(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhoa.png"));
				txtsenha.setVisible(false);
				txtsenhaview.setText(txtsenha.getText());
				txtsenhaview.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icon/olhof.png"));
				txtsenha.setVisible(true);
				txtsenhaview.setVisible(false);
			}

		});

		JButton btnNewButton = new JButton("Click Aqui para liberar o uso do Email");
		btnNewButton.setBounds(273, 241, 234, 44);
		panel_Email.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://myaccount.google.com/lesssecureapps"));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}

			}
		});

		JPanel panel_zap = new JPanel();
		panel_zap.setBackground(Color.WHITE);
		tabbedPane.addTab("Whatsapp", new ImageIcon("icon\\zapp.png"), panel_zap, null);
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		tabbedPane.setForegroundAt(1, Color.BLACK);
		panel_zap.setLayout(null);

		chbzap = new JCheckBox("Mensagem automática via Whatsapp ?");
		chbzap.setFont(new Font("SansSerif", Font.BOLD, 15));
		chbzap.setBounds(16, 6, 441, 20);
		panel_zap.add(chbzap);

		JLabel lblModelo = new JLabel("Modelo de Mensagem");
		lblModelo.setForeground(new Color(0, 102, 51));
		lblModelo.setFont(new Font("Masque", Font.PLAIN, 13));
		lblModelo.setBounds(6, 32, 271, 16);
		panel_zap.add(lblModelo);

		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Masque", Font.PLAIN, 11));
		lblTitulo.setBounds(16, 51, 170, 13);
		panel_zap.add(lblTitulo);

		String ms = config.getModelo_mesagem_zap();
		String[] msg = ms.split(";");
		ms = "";
		for (int i = 1; i < msg.length; i++) {
			ms += msg[i] + ";";
		}
		String[] ms1 = new String[5];
		if (!config.getModelo_mesagem_zap().equals("")) {
			ms1 = config.getModelo_mesagem_zap().split(";");
		}
		txttitulo = new JTextField(ms1[0]);
		txttitulo.setForeground(Color.BLACK);
		txttitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txttitulo.setColumns(10);
		txttitulo.setBounds(16, 63, 241, 32);
		panel_zap.add(txttitulo);
		if (config.getUsar_Envio_automaico_zap().equals("N")) {
			chbzap.setSelected(false);
		} else
			chbzap.setSelected(true);

		JLabel lblMensagem = new JLabel("Destinat\u00E1rio:");
		lblMensagem.setForeground(Color.BLACK);
		lblMensagem.setFont(new Font("Masque", Font.PLAIN, 11));
		lblMensagem.setBounds(269, 46, 170, 13);
		panel_zap.add(lblMensagem);

		txtdest = new JTextField(ms1[1]);
		txtdest.setForeground(Color.BLACK);
		txtdest.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtdest.setColumns(10);
		txtdest.setBounds(269, 63, 238, 32);
		panel_zap.add(txtdest);

		JLabel lblModeloData = new JLabel("Modelo data:");
		lblModeloData.setForeground(Color.BLACK);
		lblModeloData.setFont(new Font("Masque", Font.PLAIN, 11));
		lblModeloData.setBounds(16, 107, 170, 20);
		panel_zap.add(lblModeloData);

		txtdata = new JTextField(ms1[2]);
		txtdata.setForeground(Color.BLACK);
		txtdata.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtdata.setColumns(10);
		txtdata.setBounds(16, 124, 491, 32);
		panel_zap.add(txtdata);

		JLabel lblModeloHora = new JLabel("Modelo hora:");
		lblModeloHora.setForeground(Color.BLACK);
		lblModeloHora.setFont(new Font("Masque", Font.PLAIN, 11));
		lblModeloHora.setBounds(16, 168, 170, 20);
		panel_zap.add(lblModeloHora);

		txthora = new JTextField(ms1[3]);
		txthora.setForeground(Color.BLACK);
		txthora.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txthora.setColumns(10);
		txthora.setBounds(16, 185, 491, 32);
		panel_zap.add(txthora);

		JLabel lblModeloMedico = new JLabel("Mensagem A:");
		lblModeloMedico.setForeground(Color.BLACK);
		lblModeloMedico.setFont(new Font("Masque", Font.PLAIN, 11));
		lblModeloMedico.setBounds(16, 229, 170, 20);
		panel_zap.add(lblModeloMedico);

		txtmsa = new JTextField(ms1[4]);
		txtmsa.setForeground(Color.BLACK);
		txtmsa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtmsa.setColumns(10);
		txtmsa.setBounds(16, 246, 241, 32);
		panel_zap.add(txtmsa);

		JLabel lblMensagemB = new JLabel("Mensagem B:");
		lblMensagemB.setForeground(Color.BLACK);
		lblMensagemB.setFont(new Font("Masque", Font.PLAIN, 11));
		lblMensagemB.setBounds(269, 229, 170, 20);
		panel_zap.add(lblMensagemB);

		txtmsb = new JTextField(ms1[5]);
		txtmsb.setForeground(Color.BLACK);
		txtmsb.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtmsb.setColumns(10);
		txtmsb.setBounds(269, 246, 241, 32);
		panel_zap.add(txtmsb);
		
				JPanel panel_BD = new JPanel();
				panel_BD.setBackground(Color.WHITE);
				tabbedPane.addTab("BD-Dados", new ImageIcon("icon\\bd.png"), panel_BD, null);
				panel_BD.setLayout(null);
				
						JLabel label_2 = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
						label_2.setBounds(6, 5, 177, 16);
						label_2.setForeground(new Color(0, 102, 51));
						label_2.setFont(new Font("Masque", Font.PLAIN, 13));
						panel_BD.add(label_2);
						
								JLabel lblSenhaDeLiberao = new JLabel("Senha de libera\u00E7\u00E3o:");
								lblSenhaDeLiberao.setForeground(Color.BLACK);
								lblSenhaDeLiberao.setFont(new Font("Masque", Font.PLAIN, 11));
								lblSenhaDeLiberao.setBounds(16, 213, 154, 13);
								panel_BD.add(lblSenhaDeLiberao);
								lblSenhaDeLiberao.setVisible(false);
								
										txtsenhalib = new JPasswordField((String) null);
										txtsenhalib.setFont(new Font("SansSerif", Font.BOLD, 20));
										txtsenhalib.setBounds(16, 231, 167, 32);
										txtsenhalib.setVisible(false);
										panel_BD.add(txtsenhalib);
										txtsenhalib.addKeyListener(new KeyListener() {

											@Override
											public void keyTyped(KeyEvent e) {
												// TODO Auto-generated method stub

											}

											@Override
											public void keyReleased(KeyEvent e) {
												if (e.getKeyCode() == KeyEvent.VK_ENTER) {
													char[] senha = txtsenhalib.getPassword();
													String sh = "";
													for (int i = 0; i < senha.length; i++) {
														sh += senha[i];
													}
													if (sh.equals("ads54321")) {
														txtsenhabd.setEnabled(true);
														txturl.setEnabled(true);
														txtuser.setEnabled(true);
														txtsenhalib.setVisible(false);
														lblSenhaDeLiberao.setVisible(false);
													} else {
														JOptionPane.showMessageDialog(null, "Erro senha inválida", "erro", JOptionPane.ERROR_MESSAGE);
													}
												}

											}

											@Override
											public void keyPressed(KeyEvent e) {
												// TODO Auto-generated method stub

											}
										});
										
												JLabel lblUrl = new JLabel("URL:");
												lblUrl.setBounds(16, 33, 42, 13);
												lblUrl.setForeground(Color.BLACK);
												lblUrl.setFont(new Font("Masque", Font.PLAIN, 11));
												panel_BD.add(lblUrl);
												
														txturl = new JTextField(config.getURL());
														txturl.setEnabled(false);
														txturl.setBounds(16, 44, 491, 32);
														txturl.setForeground(Color.BLACK);
														txturl.setFont(new Font("SansSerif", Font.PLAIN, 15));
														txturl.setColumns(10);
														txturl.addMouseListener(new MouseListener() {

															@Override
															public void mouseReleased(MouseEvent e) {
																// TODO Auto-generated method stub

															}

															@Override
															public void mousePressed(MouseEvent e) {
																// TODO Auto-generated method stub

															}

															@Override
															public void mouseExited(MouseEvent e) {
																// TODO Auto-generated method stub

															}

															@Override
															public void mouseEntered(MouseEvent e) {
																// TODO Auto-generated method stub

															}

															@Override
															public void mouseClicked(MouseEvent e) {
																int cont = e.getClickCount();
																if (cont == 2) {
																	txtsenhalib.setVisible(true);
																	lblSenhaDeLiberao.setVisible(true);
																}

															}
														});
														panel_BD.add(txturl);
														
																JLabel lblUsuarioDoBd = new JLabel("Usuario do BD:");
																lblUsuarioDoBd.setForeground(Color.BLACK);
																lblUsuarioDoBd.setFont(new Font("Masque", Font.PLAIN, 11));
																lblUsuarioDoBd.setBounds(16, 88, 167, 13);
																panel_BD.add(lblUsuarioDoBd);
																
																		txtuser = new JTextField(config.getUSER());
																		txtuser.setEnabled(false);
																		txtuser.setForeground(Color.BLACK);
																		txtuser.setFont(new Font("SansSerif", Font.PLAIN, 15));
																		txtuser.setColumns(10);
																		txtuser.setBounds(16, 99, 280, 32);
																		panel_BD.add(txtuser);
																		
																				JLabel lblSenha = new JLabel("Senha:");
																				lblSenha.setForeground(Color.BLACK);
																				lblSenha.setFont(new Font("Masque", Font.PLAIN, 11));
																				lblSenha.setBounds(16, 147, 129, 13);
																				panel_BD.add(lblSenha);
																				
																						txtsenhabd = new JPasswordField(config.getPASS());
																						txtsenhabd.setEnabled(false);
																						txtsenhabd.setFont(new Font("SansSerif", Font.PLAIN, 20));
																						txtsenhabd.setBounds(16, 165, 167, 32);
																						panel_BD.add(txtsenhabd);

		JButton btnOk = new JButton("OK");
		btnOk.setHorizontalAlignment(SwingConstants.LEFT);
		btnOk.setIcon(new ImageIcon("icon\\sal.png"));
		btnOk.setBounds(421, 348, 90, 32);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				testEmail();
				salvar(op);
			}
		});
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void salvar(int op) {
		if (!txtemail.getText().equals("") && !txtsenha.getText().equals("") && !txtservidor.getText().equals("")
				&& !txtporta.getText().equals("") && !txtconfirme.getText().equals("")) {
			config.setEmail(txtemail.getText());
			config.setServidor_Email(txtservidor.getText());
			config.setPorta_Email(txtporta.getText());
			config.setSenha_Email(txtsenha.getText());
			config.setURL(txturl.getText());
			config.setUSER(txtuser.getText());
			config.setPASS(txtsenhabd.getText());
			if (chbzap.isSelected()) {
				config.setUsar_Envio_automaico_zap("S");
			} else {
				config.setUsar_Envio_automaico_zap("N");
			}
			config.setModelo_mesagem_zap(txttitulo.getText() + ";" + txtdest.getText() + ";" + txtdata.getText() + ";"
					+ txthora.getText() + ";" + txtmsa.getText() + ";" + txtmsb.getText());
				if(JavaMail.validaEmail(txtemail.getText())==false) {
					JOptionPane.showMessageDialog(null, "Email inválido","Erro de Email",JOptionPane.ERROR_MESSAGE);
			}
			else {
			CTConfig.insert(config);
			JOptionPane.showMessageDialog(null, "Configuração salva com sucesso!");
			if(op==0) {
				dispose();
			}
			else {
				new TelaLogin();
			}
			
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Erro campo em branco", "erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void testEmail() {
		if (!txtemail.getText().equals(txtconfirme.getText())) {
			JOptionPane.showMessageDialog(null, "Erro email são diferentes", "erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
