package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import CONTROL.CTConfig;
import CONTROL.CTEmitente;
import CONTROL.ControlCentral;
import CONTROL.JavaMail;
import MODEL.Config;
import MODEL.Emitente;
import MODEL.Conexao.CXChaveSerial;
import VIEW.tabela.ModeloTabela;
import javax.swing.JPasswordField;

public class TelaConfig extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1069232838159054518L;
	private JPanel contentPane;
	private JFormattedTextField txtcnpj;
	private JTextField txtrasao;
	private JFormattedTextField txtcep;
	private JTextField txtrua;
	private JTextField txtnum;
	private JTextField txtbairro;
	private JTextField txtcidade;

	private JLabel lblSenhaLiberao;

	private JComboBox<String> cbxuf;
	private JTextField txtemail;
	private JTextField txtnome;
	private JTextField txtibge;
	private JFormattedTextField txtrecado;
	private JFormattedTextField txtfixo;
	private JFormattedTextField txtcel1;
	private JTextField txtEstadual;
	private JLabel logo;

	private Emitente novo;
	private MaskFormatter cnpj = null;
	private MaskFormatter cel1 = null;
	private MaskFormatter cep = null;
	private MaskFormatter cel2 = null;
	private MaskFormatter fonefix = null;
	private String local = "";
	private BufferedImage imagem = null;
	private JTextField txtbd;
	private JTextField txtBkp;
	private JPasswordField txtSenha;
	private JComboBox<String> cbxTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaConfig();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaConfig() {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Cadastro - Empresa");
		setSize(736, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			cel1 = new MaskFormatter("(##)#.####-####");
			cel1.setValidCharacters("0123456789");
			fonefix = new MaskFormatter("(##)####-####");
			fonefix.setValidCharacters("0123456789");
			cnpj = new MaskFormatter("**.***.***/****-**");
			cnpj.setValidCharacters("0123456789");
			cep = new MaskFormatter("#####-###");
			cep.setValidCharacters("0123456789");
			cel2 = new MaskFormatter("(**) *.****-****");
			cel2.setValidCharacters("0123456789");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(8, 19, 186, 16);
		contentPane.add(label);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setForeground(Color.BLACK);
		lblCnpj.setFont(new Font("Masque", Font.PLAIN, 11));
		lblCnpj.setBounds(28, 36, 80, 16);
		contentPane.add(lblCnpj);

		txtrasao = new JTextField();
		txtrasao.setForeground(Color.BLACK);
		txtrasao.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtrasao.setColumns(10);
		txtrasao.setBounds(181, 52, 327, 30);
		contentPane.add(txtrasao);

		JLabel lblRasoSocial = new JLabel("Ras\u00E3o social:");
		lblRasoSocial.setForeground(Color.BLACK);
		lblRasoSocial.setFont(new Font("Masque", Font.PLAIN, 11));
		lblRasoSocial.setBounds(183, 36, 325, 16);
		contentPane.add(lblRasoSocial);

		JLabel label_1 = new JLabel("CEP:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 10));
		label_1.setBounds(27, 166, 32, 16);
		contentPane.add(label_1);

		txtcep = new JFormattedTextField(cep);
		txtcep.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtcep.setBounds(27, 183, 92, 30);
		contentPane.add(txtcep);

		JLabel label_2 = new JLabel("LAGADOURO:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Masque", Font.PLAIN, 10));
		label_2.setBounds(136, 166, 321, 16);
		contentPane.add(label_2);

		txtrua = new JTextField();
		txtrua.setForeground(Color.BLACK);
		txtrua.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtrua.setColumns(10);
		txtrua.setBounds(131, 182, 326, 30);
		contentPane.add(txtrua);

		txtnum = new JTextField();
		txtnum.setForeground(Color.BLACK);
		txtnum.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnum.setColumns(10);
		txtnum.setBounds(466, 182, 55, 30);
		contentPane.add(txtnum);

		JLabel label_3 = new JLabel("NUM:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Masque", Font.PLAIN, 10));
		label_3.setBounds(472, 166, 32, 16);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("BAIRRO:");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Masque", Font.PLAIN, 10));
		label_4.setBounds(29, 219, 97, 16);
		contentPane.add(label_4);

		txtbairro = new JTextField();
		txtbairro.setForeground(Color.BLACK);
		txtbairro.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtbairro.setColumns(10);
		txtbairro.setBounds(27, 235, 242, 30);
		contentPane.add(txtbairro);

		txtcidade = new JTextField();
		txtcidade.setForeground(Color.BLACK);
		txtcidade.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcidade.setColumns(10);
		txtcidade.setBounds(279, 235, 242, 30);
		contentPane.add(txtcidade);

		JLabel label_5 = new JLabel("CIDADE:");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Masque", Font.PLAIN, 10));
		label_5.setBounds(283, 219, 97, 16);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("ENDERE\u00C7O");
		label_6.setForeground(new Color(0, 102, 51));
		label_6.setFont(new Font("Masque", Font.PLAIN, 13));
		label_6.setBounds(17, 148, 92, 16);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("CONTATO");
		label_7.setForeground(new Color(0, 102, 51));
		label_7.setFont(new Font("Masque", Font.PLAIN, 13));
		label_7.setBounds(14, 280, 186, 16);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("TEL.CELULAR:");
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Masque", Font.PLAIN, 10));
		label_8.setBounds(24, 300, 126, 16);
		contentPane.add(label_8);

		txtcel1 = new JFormattedTextField(cel1);
		txtcel1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcel1.setBounds(24, 317, 126, 30);
		contentPane.add(txtcel1);

		txtfixo = new JFormattedTextField(fonefix);
		txtfixo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfixo.setBounds(160, 317, 126, 30);
		contentPane.add(txtfixo);

		JLabel label_9 = new JLabel("TEL.RESIDENCIAL:");
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Masque", Font.PLAIN, 10));
		label_9.setBounds(160, 300, 126, 16);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("TEL.RECADO:");
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Masque", Font.PLAIN, 10));
		label_10.setBounds(296, 300, 126, 16);
		contentPane.add(label_10);

		txtrecado = new JFormattedTextField(cel2);
		txtrecado.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrecado.setBounds(296, 317, 126, 30);
		contentPane.add(txtrecado);

		txtemail = new JTextField();
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtemail.setColumns(10);
		txtemail.setBounds(430, 317, 282, 30);
		contentPane.add(txtemail);

		JLabel label_11 = new JLabel("e-mail:");
		label_11.setForeground(Color.BLACK);
		label_11.setFont(new Font("Masque", Font.PLAIN, 10));
		label_11.setBounds(430, 300, 229, 16);
		contentPane.add(label_11);

		JLabel lblFotox = new JLabel("Logo 200x120");
		lblFotox.setOpaque(true);
		lblFotox.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotox.setForeground(Color.BLACK);
		lblFotox.setFont(new Font("Rockwell", Font.BOLD, 15));
		lblFotox.setBackground(new Color(0, 102, 51));
		lblFotox.setBounds(518, 19, 200, 16);
		contentPane.add(lblFotox);

		logo = new JLabel("");
		logo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 102, 51), new Color(0, 102, 51)));
		logo.setBounds(518, 36, 200, 120);
		contentPane.add(logo);

		if (CTEmitente.getEmitente() == null) {
			CortaImagem corte = new CortaImagem();
			File arquivo = new File("icon/logoGraca.png");// arquivo
			try {
				imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
				logo.setIcon(new ImageIcon(corte.setCorte120x200(imagem)));
				local = "icon/logoGraca.png";// seta no jlabel
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		JButton btnup = new JButton("");
		btnup.setIcon(new ImageIcon("icon\\clip.png"));
		btnup.setBounds(648, 157, 32, 28);
		contentPane.add(btnup);
		btnup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Criação do FileChooser
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
						logo.setIcon(new ImageIcon(corte.setCorte120x200(imagem)));// seta no jlabel
						local = fileChooser.getSelectedFile().getPath();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});

		JButton btndel = new JButton("");
		btndel.setIcon(new ImageIcon("icon\\del.png"));
		btndel.setBounds(683, 157, 32, 28);
		contentPane.add(btndel);
		btndel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logo.setIcon(new ImageIcon(""));
			}
		});

		cbxuf = new JComboBox<String>();
		cbxuf.setModel(new DefaultComboBoxModel<String>(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbxuf.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbxuf.setBounds(531, 234, 57, 30);
		contentPane.add(cbxuf);

		JLabel label_14 = new JLabel("UF:");
		label_14.setForeground(Color.BLACK);
		label_14.setFont(new Font("Masque", Font.PLAIN, 10));
		label_14.setBounds(536, 219, 32, 16);
		contentPane.add(label_14);

		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);
		txtnome.setBounds(181, 104, 327, 30);
		contentPane.add(txtnome);

		txtibge = new JTextFieldSoNumero();
		txtibge.setForeground(Color.BLACK);
		txtibge.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtibge.setColumns(10);
		txtibge.setBounds(598, 235, 122, 30);
		contentPane.add(txtibge);

		JLabel lblCodibge = new JLabel("Cod.IBGE:");
		lblCodibge.setForeground(Color.BLACK);
		lblCodibge.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCodibge.setBounds(600, 219, 97, 16);
		contentPane.add(lblCodibge);

		txtcnpj = new JFormattedTextField(cnpj);
		txtcnpj.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtcnpj.setBounds(25, 52, 146, 30);
		contentPane.add(txtcnpj);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia:");
		lblNomeFantasia.setForeground(Color.BLACK);
		lblNomeFantasia.setFont(new Font("Masque", Font.PLAIN, 11));
		lblNomeFantasia.setBounds(183, 88, 145, 16);
		contentPane.add(lblNomeFantasia);

		JButton btnsalvar = new JButton("salvar");
		btnsalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btnsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvar.setFont(new Font("Masque", Font.BOLD, 11));
		btnsalvar.setBounds(598, 410, 113, 39);
		contentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				novo = new Emitente();
				novo.setCNPJ(txtcnpj.getText());
				novo.setInscEst(txtEstadual.getText());
				novo.setRazao(txtrasao.getText().toUpperCase());
				novo.setNomeFatasia(txtnome.getText().toUpperCase());
				novo.setCep(txtcep.getText());
				novo.setRua(txtrua.getText().toUpperCase());
				novo.setNum(txtnum.getText());
				novo.setBairro(txtbairro.getText().toUpperCase());
				novo.setCidade(txtcidade.getText().toUpperCase());
				novo.setUF("" + cbxuf.getSelectedItem());
				if (!txtibge.getText().equals("")) {
					novo.setCodIbge(Integer.parseInt(txtibge.getText()));
				}

				novo.setCel1(txtcel1.getText());
				novo.setFone(txtfixo.getText());
				novo.setFax(txtrecado.getText());
				novo.setEmail(txtemail.getText().toLowerCase());
				novo.setLogo(local);
				Config cf =CTConfig.getConfig();
				
				if (CTEmitente.getEmitente() == null) {
					if (!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText()) == false) {
						JOptionPane.showMessageDialog(null, "Email inválido", "Erro de Email",
								JOptionPane.ERROR_MESSAGE);

					} else {
						cf.setTipoInstalacao(""+	cbxTipo.getSelectedItem());
						cf.setVersao("V-1.5-01/03/2020");
						CTConfig.insert(cf);
						CTEmitente.insert(novo);
						ControlCentral.setLocal(txtbd.getText());
						JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");
						CXChaveSerial novo = new CXChaveSerial();
						novo.criarChave();
						dispose();
						if(cbxTipo.getSelectedItem().equals("ESTACAO")) {
							new TelaConfigGeral(1);
						}
						else {
						new TelaLogin();
						}
					}

				} else {
					if (!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText()) == false) {
						JOptionPane.showMessageDialog(null, "Email inválido", "Erro de Email",
								JOptionPane.ERROR_MESSAGE);
					} else {
						cf.setTipoInstalacao(""+	cbxTipo.getSelectedItem());
						CTConfig.insert(cf);
						CTEmitente.update(novo);
						ControlCentral.setLocal(txtbd.getText());
						JOptionPane.showMessageDialog(null, "Empresa Alterada com sucesso!");
						dispose();
					}
				}
			}
		});

		JLabel lblLocalBd = new JLabel("Local BD");
		lblLocalBd.setForeground(new Color(0, 102, 51));
		lblLocalBd.setFont(new Font("Masque", Font.PLAIN, 13));
		lblLocalBd.setBounds(8, 357, 186, 16);
		contentPane.add(lblLocalBd);

		txtbd = new JTextField();
		txtbd.setEnabled(false);
		txtbd.setText("xml");
		txtbd.setForeground(Color.BLACK);
		txtbd.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtbd.setColumns(10);
		txtbd.setBounds(18, 390, 268, 25);
		contentPane.add(txtbd);
		txtbd.addMouseListener(new MouseListener() {

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
				if (e.getClickCount() == 2) {
					lblSenhaLiberao.setVisible(true);
					txtSenha.setVisible(true);

				}

			}
		});

		JLabel lblEndereoDoBanco = new JLabel("Endere\u00E7o do banco de dados:");
		lblEndereoDoBanco.setForeground(Color.BLACK);
		lblEndereoDoBanco.setFont(new Font("Masque", Font.PLAIN, 10));
		lblEndereoDoBanco.setBounds(23, 374, 200, 16);
		contentPane.add(lblEndereoDoBanco);

		JLabel lblEndereoDoBanco_1 = new JLabel("Endere\u00E7o do banco de bacukp:");
		lblEndereoDoBanco_1.setForeground(Color.BLACK);
		lblEndereoDoBanco_1.setFont(new Font("Masque", Font.PLAIN, 10));
		lblEndereoDoBanco_1.setBounds(21, 422, 265, 16);
		contentPane.add(lblEndereoDoBanco_1);

		txtBkp = new JTextField();
		txtBkp.setText("bkp");
		txtBkp.setForeground(Color.BLACK);
		txtBkp.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtBkp.setEnabled(false);
		txtBkp.setColumns(10);
		txtBkp.setBounds(16, 437, 270, 25);
		contentPane.add(txtBkp);

		txtEstadual = new JTextFieldSoNumero();
		txtEstadual.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEstadual.setBounds(25, 104, 146, 30);
		contentPane.add(txtEstadual);

		JLabel lblInstestadual = new JLabel("Inst.Estadual:");
		lblInstestadual.setForeground(Color.BLACK);
		lblInstestadual.setFont(new Font("Masque", Font.PLAIN, 11));
		lblInstestadual.setBounds(28, 88, 143, 16);
		contentPane.add(lblInstestadual);

		lblSenhaLiberao = new JLabel("Senha Libera\u00E7\u00E3o:");
		lblSenhaLiberao.setForeground(Color.BLACK);
		lblSenhaLiberao.setFont(new Font("Masque", Font.PLAIN, 10));
		lblSenhaLiberao.setBounds(296, 422, 161, 16);
		contentPane.add(lblSenhaLiberao);
		lblSenhaLiberao.setVisible(false);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(296, 437, 161, 25);
		contentPane.add(txtSenha);
		txtSenha.setVisible(false);
		txtSenha.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtSenha.getText().equals("ads54321")) {
						txtBkp.setEnabled(true);
						txtbd.setEnabled(true);
						cbxTipo.setEnabled(true);
						lblSenhaLiberao.setVisible(false);
						txtSenha.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Senha Invalida lique para o Suporte: (83)9.9638-6694");
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		cbxTipo = new JComboBox<String>();
		cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "SERVIDOR", "ESTACAO" }));
		cbxTipo.setEditable(true);
		cbxTipo.setEnabled(false);
		cbxTipo.setFont(new Font("Tahoma", Font.BOLD, 15));
		cbxTipo.setBounds(296, 390, 161, 25);
		contentPane.add(cbxTipo);

		JLabel lblTypeInstall = new JLabel("Type Install");
		lblTypeInstall.setForeground(Color.BLACK);
		lblTypeInstall.setFont(new Font("Masque", Font.PLAIN, 10));
		lblTypeInstall.setBounds(301, 374, 121, 16);
		contentPane.add(lblTypeInstall);

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		if (CTEmitente.getEmitente() != null) {
			preencher();
		}
		setVisible(true);
		repaint();
	}

	public void preencher() {
		novo = CTEmitente.getEmitente();
		txtcnpj.setText(novo.getCNPJ());
		txtrasao.setText(novo.getRazao());
		txtnome.setText(novo.getNomeFatasia());
		txtEstadual.setText(novo.getInscEst());
		txtcep.setText(novo.getCep());
		txtrua.setText(novo.getRua());
		txtnum.setText(novo.getNum());
		txtbairro.setText(novo.getBairro());
		txtcidade.setText(novo.getCidade());
		cbxuf.setSelectedItem(novo.getUF());
		txtcel1.setText(novo.getCel1());
		txtfixo.setText(novo.getFone());
		txtrecado.setText(novo.getFax());
		txtemail.setText(novo.getEmail());
		txtibge.setText("" + novo.getCodIbge());
		txtbd.setText(ControlCentral.getLocal());

		if (!novo.getLogo().equals("")) {
			CortaImagem corte = new CortaImagem();
			File arquivo = new File(novo.getLogo());// arquivo
			try {
				imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
				logo.setIcon(new ImageIcon(corte.setCorte120x200(imagem)));
				local = novo.getLogo();// seta no jlabel
			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}

	}
}
