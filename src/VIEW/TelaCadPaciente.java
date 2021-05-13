package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import CONTROL.CTCEP;
import CONTROL.CTConvenio;
import CONTROL.CTPaciente;
import CONTROL.JavaMail;
import MODEL.CEP;
import MODEL.Paciente;
import VIEW.tabela.ModeloTabela;

public class TelaCadPaciente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtcod;
	private JTextField txtnome;
	private JTextField txtrg;
	private JFormattedTextField txtpeso;
	private JTextField txtprof;
	private JTextField txtrua;
	private JTextField txtcomp;
	private JTextField txtnum;
	private JTextField txtbairro;
	private JTextField txtcidade;
	private JTextField txtemail;
	private JTextField txtidade;
	private MaskFormatter cpf = null;
	private MaskFormatter telefone = null;
	private MaskFormatter cep = null;
	private MaskFormatter fone = null;
	private MaskFormatter fonefix = null;
	private MaskFormatter at = null;
	private MaskFormatter pe = null;
	private JDateChooser txtDatanasc;
	private BufferedImage imagem = null;
	private String local="";
	private JTextField txtcartao;
	private JDateChooser txtvalidade;
	private JComboBox<String> cbxconvenio;
	private JTextArea txtobs;
	private JComboBox<String> cbxuf;
	private Paciente con;
	private JComboBox<String> txtsexo;
	private JFormattedTextField txtcpf;
	private JComboBox<String> txtcor;
	private JFormattedTextField txtaltura;
	private JComboBox<String> cbxsangue; 
	private JComboBox<String> txtcivil;
	private JFormattedTextField txtcep;
	private JTextFieldSoNumero txtzap;
    private JFormattedTextField txtfone;
    private JFormattedTextField txtrecado;
    private JLabel foto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		new TelaCadPaciente(0);
	}

	/**
	 * Create the frame.
	 */
	public TelaCadPaciente(int id) {

		setTitle("Ficha de cadastro do Paciente");
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);// seuJFrame
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setBounds(100, 100, 725, 591);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ModeloTabela.ativarModeloTela(this);
		this.setBackground(new Color(255, 255, 255));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInf = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		lblInf.setForeground(Color.ORANGE);
		lblInf.setFont(new Font("Masque", Font.PLAIN, 13));
		lblInf.setBounds(10, 12, 186, 16);
		contentPane.add(lblInf);

		JLabel lblNome = new JLabel("++Nome :");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Masque", Font.PLAIN, 10));
		lblNome.setBounds(102, 26, 97, 16);
		contentPane.add(lblNome);

		txtnome = new JTextFieldSoLetras();
		txtnome.requestFocus();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);
		txtnome.setBounds(102, 42, 354, 30);
		contentPane.add(txtnome);
		
		int t = CTPaciente.getId();
		txtcod = new JTextField(String.format("%010d", t+1));
		txtcod.setHorizontalAlignment(SwingConstants.CENTER);
		txtcod.setEditable(false);
		txtcod.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtcod.setForeground(Color.GREEN);
		txtcod.setBounds(16, 42, 82, 30);
		contentPane.add(txtcod);
		
		
		

		JLabel lblS = new JLabel("Protuario:");
		lblS.setForeground(Color.BLACK);
		lblS.setFont(new Font("Masque", Font.PLAIN, 10));
		lblS.setBounds(16, 26, 97, 16);
		contentPane.add(lblS);

		foto = new JLabel("");
		foto.setIcon(new ImageIcon("icon/fundfoto.png"));
		foto.setBounds(604, 43, 100, 120);
		foto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 102, 51), new Color(0, 102, 51)));
		contentPane.add(foto);

		JButton botdeletar = new JButton("");
		botdeletar.setIcon(new ImageIcon("icon/del.png"));
		botdeletar.setBounds(672, 166, 32, 28);
		contentPane.add(botdeletar);
		botdeletar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				foto.setIcon(new ImageIcon("icon\\fundfoto.png"));
				local="";
			}
		});

		JLabel lblCpf = new JLabel("++CPF:");
		lblCpf.setForeground(Color.BLACK);
		lblCpf.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCpf.setBounds(67, 79, 115, 16);
		contentPane.add(lblCpf);

		// ;
		txtsexo = new JComboBox<String>();
		txtsexo.setModel(new DefaultComboBoxModel<String>(new String[] { "-", "M", "F" }));
		txtsexo.setFont(new Font("Masque", Font.PLAIN, 11));
		txtsexo.setBounds(16, 97, 44, 30);
		contentPane.add(txtsexo);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setForeground(Color.BLACK);
		lblSexo.setFont(new Font("Masque", Font.PLAIN, 10));
		lblSexo.setBounds(17, 82, 32, 16);
		contentPane.add(lblSexo);

		txtrg = new JTextFieldSoNumero();
		txtrg.setForeground(Color.BLACK);
		txtrg.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtrg.setColumns(10);
		txtrg.setBounds(189, 96, 126, 30);
		contentPane.add(txtrg);
		

		JLabel lblRg = new JLabel("RG:");
		lblRg.setForeground(Color.BLACK);
		lblRg.setFont(new Font("Masque", Font.PLAIN, 10));
		lblRg.setBounds(194, 79, 86, 16);
		contentPane.add(lblRg);

		JLabel lblDataNasc = new JLabel("Data Nasc:");
		lblDataNasc.setForeground(Color.BLACK);
		lblDataNasc.setFont(new Font("Masque", Font.PLAIN, 10));
		lblDataNasc.setBounds(464, 26, 78, 16);
		contentPane.add(lblDataNasc);

		try {
			fone = new MaskFormatter("(##)*****-****");
			fone.setValidCharacters("0123456789");
			fonefix = new MaskFormatter("(##)####-####");
			fonefix.setValidCharacters("0123456789");
			cpf = new MaskFormatter("***.***.***-**");
			cpf.setValidCharacters("0123456789");
			cep = new MaskFormatter("#####-###");
			cep.setValidCharacters("0123456789");
			telefone = new MaskFormatter("(##)#####-####");
			telefone.setValidCharacters("0123456789");

			at = new MaskFormatter("#.##");
			
			pe = new MaskFormatter("##.##");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtcpf = new JFormattedTextField(cpf);
		txtcpf.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtcpf.setBounds(61, 96, 126, 30);
		contentPane.add(txtcpf);

		txtDatanasc = new JDateChooser("dd/MM/yyyy", "##/##/#####", ' ');
		txtDatanasc.getCalendarButton().setIcon(new ImageIcon("icon\\calend.png"));
		txtDatanasc.setBounds(464, 42, 128, 30);
		contentPane.add(txtDatanasc);
		txtDatanasc.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
			
				if(txtDatanasc.getDate()!=null) {
					int nasc = txtDatanasc.getDate().getYear();
					Date hoje = new Date();
					int h = hoje.getYear();
					int res = h - nasc;
					txtidade.setText("" + res);
				
		}
		}
				
		});

		txtcor = new JComboBox<String>();
		txtcor.setModel(new DefaultComboBoxModel<String>(new String[] {"-", "BRANCO(A)", "PARDO(A)", "NEGRO(A)", "MULATO(A)"}));
		txtcor.setFont(new Font("Tahoma", Font.BOLD, 9));
		txtcor.setBounds(16, 148, 106, 30);
		contentPane.add(txtcor);

		JLabel lblCor = new JLabel("Cor:");
		lblCor.setForeground(Color.BLACK);
		lblCor.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCor.setBounds(17, 133, 81, 16);
		contentPane.add(lblCor);

		txtaltura = new JFormattedTextField(at);
		txtaltura.setText("0.00");
		txtaltura.setForeground(Color.BLACK);
		txtaltura.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtaltura.setColumns(10);
		txtaltura.setBounds(125, 148, 81, 30);
		contentPane.add(txtaltura);

		JLabel lblAltura = new JLabel("ALTURA:");
		lblAltura.setForeground(Color.BLACK);
		lblAltura.setFont(new Font("Masque", Font.PLAIN, 10));
		lblAltura.setBounds(125, 133, 81, 16);
		contentPane.add(lblAltura);

		txtpeso = new JFormattedTextField(pe);
		txtpeso.setText("00.00");
		txtpeso.setForeground(Color.BLACK);
		txtpeso.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtpeso.setColumns(10);
		txtpeso.setBounds(213, 148, 81, 30);
		contentPane.add(txtpeso);

		JLabel lblPeso = new JLabel("PESO:");
		lblPeso.setForeground(Color.BLACK);
		lblPeso.setFont(new Font("Masque", Font.PLAIN, 10));
		lblPeso.setBounds(213, 133, 81, 16);
		contentPane.add(lblPeso);

		JLabel lblGrupoSanguinio = new JLabel("GRUPO SANGU\u00CDNEO:");
		lblGrupoSanguinio.setForeground(Color.BLACK);
		lblGrupoSanguinio.setFont(new Font("Masque", Font.PLAIN, 10));
		lblGrupoSanguinio.setBounds(301, 133, 123, 16);
		contentPane.add(lblGrupoSanguinio);

		cbxsangue = new JComboBox<String>();
		cbxsangue.setModel(new DefaultComboBoxModel<String>(new String[] { "-", "O- POSITIVO", "O-NEGATIVO", "A-POSITIVO",
				"A-NEGATIVO", "B-POSITIVO", "B-NEGATIVO", "AB-POSITIVO", "AB-NEGATIVO" }));
		cbxsangue.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbxsangue.setBounds(300, 148, 124, 30);
		contentPane.add(cbxsangue);

		txtprof = new JTextFieldSoLetras();
		txtprof.setForeground(Color.BLACK);
		txtprof.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtprof.setColumns(10);
		txtprof.setBounds(425, 148, 178, 30);
		contentPane.add(txtprof);
	

		JLabel lblProfissao = new JLabel("PROFISSAO:");
		lblProfissao.setForeground(Color.BLACK);
		lblProfissao.setFont(new Font("Masque", Font.PLAIN, 10));
		lblProfissao.setBounds(425, 133, 119, 16);
		contentPane.add(lblProfissao);

		txtcivil = new JComboBox<String>();
		txtcivil.setModel(new DefaultComboBoxModel<String>(new String[] {"-", "SOLTEIRO(A)", "CASADO(A)", "DIVORCIADO(A)"}));
		txtcivil.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtcivil.setBounds(394, 97, 163, 30);
		contentPane.add(txtcivil);

		JLabel lblEstcivil = new JLabel("ESTADO CIVIL:");
		lblEstcivil.setForeground(Color.BLACK);
		lblEstcivil.setFont(new Font("Masque", Font.PLAIN, 10));
		lblEstcivil.setBounds(400, 82, 119, 16);
		contentPane.add(lblEstcivil);
		
		cbxuf = new JComboBox<String>();
		cbxuf.setModel(new DefaultComboBoxModel<String>(new String[] {"-","AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbxuf.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbxuf.setBounds(520, 310, 57, 25);
		contentPane.add(cbxuf);

		txtcep = new JFormattedTextField(cep);
		txtcep.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtcep.setBounds(16, 264, 92, 30);
		contentPane.add(txtcep);
		txtcep.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
		
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String g= txtcep.getText().replaceAll(" ", "");
				if(g.length()==9) {
				CEP cep = null;
				cep=CTCEP.getCod(txtcep.getText());
				if(cep.getCod()!=null ) {
				txtrua.setText(cep.getRua());
				txtbairro.setText(cep.getBairro());
				txtcidade.setText(cep.getCidade());
				cbxuf.setSelectedItem(cep.getUF());	
				}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		JLabel lblCep = new JLabel("++CEP:");
		lblCep.setForeground(Color.BLACK);
		lblCep.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCep.setBounds(16, 247, 86, 16);
		contentPane.add(lblCep);

		JLabel lblEndereo = new JLabel("ENDERE\u00C7O");
		lblEndereo.setForeground(Color.ORANGE);
		lblEndereo.setFont(new Font("Masque", Font.PLAIN, 13));
		lblEndereo.setBounds(6, 232, 92, 16);
		contentPane.add(lblEndereo);

		txtrua = new JTextField();
		txtrua.setForeground(Color.BLACK);
		txtrua.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtrua.setColumns(10);
		txtrua.setBounds(145, 263, 296, 30);
		contentPane.add(txtrua);
		

		JLabel lblLagadouro = new JLabel("++LAGADOURO:");
		lblLagadouro.setForeground(Color.BLACK);
		lblLagadouro.setFont(new Font("Masque", Font.PLAIN, 10));
		lblLagadouro.setBounds(150, 247, 296, 16);
		contentPane.add(lblLagadouro);

		txtcomp = new JTextField();
		txtcomp.setForeground(Color.BLACK);
		txtcomp.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcomp.setColumns(10);
		txtcomp.setBounds(499, 264, 205, 30);
		contentPane.add(txtcomp);
		

		JLabel lblComplemento = new JLabel("COMPLEMENTO:");
		lblComplemento.setForeground(Color.BLACK);
		lblComplemento.setFont(new Font("Masque", Font.PLAIN, 10));
		lblComplemento.setBounds(503, 248, 179, 16);
		contentPane.add(lblComplemento);

		txtnum = new JTextField();
		txtnum.setForeground(Color.BLACK);
		txtnum.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnum.setColumns(10);
		txtnum.setBounds(449, 264, 40, 30);
		contentPane.add(txtnum);

		JLabel lblNum = new JLabel("NUM:");
		lblNum.setForeground(Color.BLACK);
		lblNum.setFont(new Font("Masque", Font.PLAIN, 10));
		lblNum.setBounds(453, 247, 38, 16);
		contentPane.add(lblNum);

		txtbairro = new JTextField();
		txtbairro.setForeground(Color.BLACK);
		txtbairro.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtbairro.setColumns(10);
		txtbairro.setBounds(16, 310, 242, 30);
		contentPane.add(txtbairro);
		

		JLabel lblBairro = new JLabel("++BAIRRO:");
		lblBairro.setForeground(Color.BLACK);
		lblBairro.setFont(new Font("Masque", Font.PLAIN, 10));
		lblBairro.setBounds(18, 294, 97, 16);
		contentPane.add(lblBairro);

		txtcidade = new JTextField();
		txtcidade.setForeground(Color.BLACK);
		txtcidade.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcidade.setColumns(10);
		txtcidade.setBounds(268, 310, 242, 30);
		contentPane.add(txtcidade);
		

		JLabel lblCidade = new JLabel("++CIDADE:");
		lblCidade.setForeground(Color.BLACK);
		lblCidade.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCidade.setBounds(272, 294, 97, 16);
		contentPane.add(lblCidade);

		

		JLabel lblUf = new JLabel("++UF:");
		lblUf.setForeground(Color.BLACK);
		lblUf.setFont(new Font("Masque", Font.PLAIN, 10));
		lblUf.setBounds(525, 295, 32, 16);
		contentPane.add(lblUf);

		JLabel lblContato = new JLabel("CONTATO");
		lblContato.setForeground(Color.ORANGE);
		lblContato.setFont(new Font("Masque", Font.PLAIN, 13));
		lblContato.setBounds(6, 344, 186, 16);
		contentPane.add(lblContato);

		JLabel lblCelular = new JLabel("Whatsapp:");
		lblCelular.setIcon(new ImageIcon("icon\\zapp.png"));
		lblCelular.setForeground(Color.BLACK);
		lblCelular.setFont(new Font("Masque", Font.PLAIN, 10));
		lblCelular.setBounds(150, 366, 126, 16);
		contentPane.add(lblCelular);

		txtzap = new JTextFieldSoNumero();
		txtzap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtzap.setBounds(150, 383, 126, 30);
		contentPane.add(txtzap);

		txtfone = new JFormattedTextField(fonefix);
		txtfone.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfone.setBounds(286, 383, 126, 30);
		contentPane.add(txtfone);

		JLabel lblTelresidencial = new JLabel("TEL.RESIDENCIAL:");
		lblTelresidencial.setForeground(Color.BLACK);
		lblTelresidencial.setFont(new Font("Masque", Font.PLAIN, 10));
		lblTelresidencial.setBounds(286, 366, 126, 16);
		contentPane.add(lblTelresidencial);

		txtrecado = new JFormattedTextField(fone);
		txtrecado.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrecado.setBounds(16, 384, 126, 30);
		contentPane.add(txtrecado);

		JLabel lblTelrecado = new JLabel("Celular:");
		lblTelrecado.setForeground(Color.BLACK);
		lblTelrecado.setFont(new Font("Masque", Font.PLAIN, 10));
		lblTelrecado.setBounds(16, 367, 126, 16);
		contentPane.add(lblTelrecado);

		txtemail = new JTextField();
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtemail.setColumns(10);
		txtemail.setBounds(422, 384, 282, 30);
		contentPane.add(txtemail);

		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Masque", Font.PLAIN, 10));
		lblEmail.setBounds(422, 368, 229, 16);
		contentPane.add(lblEmail);

		JLabel lblObersvaes = new JLabel("OBERSVA\u00C7\u00D5ES:");
		lblObersvaes.setForeground(Color.ORANGE);
		lblObersvaes.setFont(new Font("Masque", Font.PLAIN, 13));
		lblObersvaes.setBounds(6, 439, 186, 16);
		contentPane.add(lblObersvaes);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.ORANGE);
		separator.setBounds(91, 237, 463, 8);
		contentPane.add(separator);

		txtobs = new JTextArea();
		txtobs.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtobs.setBounds(16, 461, 398, 79);
		contentPane.add(txtobs);

		JButton btnlupa = new JButton("");
		btnlupa.setIcon(new ImageIcon("icon\\lp.png"));
		btnlupa.setBounds(112, 264, 30, 30);
		contentPane.add(btnlupa);
		btnlupa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblFoto = new JLabel("Foto 100x120");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBackground(new Color(0, 102, 51));
		lblFoto.setForeground(new Color(0, 0, 0));
		lblFoto.setFont(new Font("Rockwell", Font.BOLD, 15));
		lblFoto.setBounds(604, 28, 100, 16);
		lblFoto.setOpaque(true);
		contentPane.add(lblFoto);

		txtidade = new JTextField();
		txtidade.setEditable(false);
		txtidade.setForeground(Color.BLACK);
		txtidade.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtidade.setColumns(10);
		txtidade.setBounds(320, 97, 73, 30);
		contentPane.add(txtidade);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setForeground(Color.BLACK);
		lblIdade.setFont(new Font("Masque", Font.PLAIN, 10));
		lblIdade.setBounds(324, 80, 57, 16);
		contentPane.add(lblIdade);

		JButton btnsalvra = new JButton("salvar");
		btnsalvra.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvra.setIcon(new ImageIcon("icon/sal.png"));
		btnsalvra.setFont(new Font("Masque", Font.BOLD, 11));
		btnsalvra.setBounds(423, 486, 113, 39);
		contentPane.add(btnsalvra);
		btnsalvra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                     
				/// salvar paciente
				
					if(id==0) {
					con= new Paciente();
					}
					con.setId(Integer.parseInt(txtcod.getText()));
					con.setNome(txtnome.getText().toUpperCase());
					con.setSexo(""+txtsexo.getSelectedItem());
					con.setCPF( txtcpf.getText());
					con.setRg(txtrg.getText());
					con.setDataNasc(txtDatanasc.getDate());
				    if(!txtidade.getText().equals("")) {
					con.setIdade(Integer.parseInt(txtidade.getText()));
				    }
					con.setEstadoCivil(""+txtcivil.getSelectedItem());
					con.setCor("" + txtcor.getSelectedItem());
					con.setAltura(Float.parseFloat(txtaltura.getText()));
					con.setPeso(Float.parseFloat(txtpeso.getText()));
					con.setTipoSangue("" + cbxsangue.getSelectedItem());
					con.setConvenio(""+cbxconvenio.getSelectedItem());
					con.setNumCartao(txtcartao.getText());
					con.setValidadeCrad( txtvalidade.getDate());
					if(!txtprof.getText().equals("")) {
					con.setProfissao(txtprof.getText().toUpperCase());
					}
					con.setCep(txtcep.getText());
					con.setRua(txtrua.getText().toUpperCase());
				    con.setComplemento(txtcomp.getText());
					con.setNum(txtnum.getText());
					con.setBairro(txtbairro.getText().toUpperCase());
					con.setCidade(txtcidade.getText().toUpperCase());
					con.setUf( "" + cbxuf.getSelectedItem());
					con.setCelular1(txtzap.getText());
					con.setTelFixo(txtfone.getText());
					con.setCelular2( txtrecado.getText());
					con.setEmil(txtemail.getText());
					con.setFoto(local);
					con.setObs(txtobs.getText().toUpperCase());
					
					if(!txtnome.getText().equals("") && !txtcpf.getText().equals("   .   .   -  ") &&!txtcep.getText().equals("     -   ")
					   &&!txtbairro.getText().equals("")&&!txtcidade.getText().equals("")&&!cbxuf.getSelectedItem().equals("-") ){
					try {	
					if(id==0) {
					///test de  botao;
						
					if(!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText())==false) {
						JOptionPane.showMessageDialog(null, "Email inválido","Erro de Email",JOptionPane.ERROR_MESSAGE);
					}
					else {
					CTPaciente.insert(con);
					CTCEP.insert(txtcep.getText(), txtrua.getText(), txtbairro.getText(), txtcidade.getText(), "" + cbxuf.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
					TelaGerPaciente.addTable(con);
					con=null;
					dispose();
					}
					}
					else {
						if(!txtemail.getText().equals("") && JavaMail.validaEmail(txtemail.getText())==false) {
							JOptionPane.showMessageDialog(null, "Email inválido","Erro de Email",JOptionPane.ERROR_MESSAGE);
							
						}
						else {
							CTPaciente.update(con);
							CTCEP.insert(txtcep.getText(), txtrua.getText(), txtbairro.getText(), txtcidade.getText(), "" + cbxuf.getSelectedItem());
							JOptionPane.showMessageDialog(null, "Paciente Alterado com sucesso!");
							dispose();
							new TelaGerPaciente();
							}
						
						
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				else
					JOptionPane.showMessageDialog(null, "Erro de campo em branco","Erro",JOptionPane.ERROR_MESSAGE);

			}
		});

		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setIcon(new ImageIcon("icon\\del.png"));
		btnCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelar.setFont(new Font("Masque", Font.BOLD, 11));
		btnCancelar.setBounds(539, 486, 130, 39);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JLabel lblConvenio = new JLabel("Convenio:");
		lblConvenio.setForeground(Color.BLACK);
		lblConvenio.setFont(new Font("Masque", Font.PLAIN, 10));
		lblConvenio.setBounds(18, 185, 144, 16);
		contentPane.add(lblConvenio);

		cbxconvenio = new JComboBox<String>();
		cbxconvenio.setModel(new DefaultComboBoxModel<String>(CTConvenio.getListaNomes()));
		cbxconvenio.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbxconvenio.setBounds(16, 202, 157, 30);
		contentPane.add(cbxconvenio);

		JLabel lblNDoCartao = new JLabel("N\u00BA do cartao do convenio:");
		lblNDoCartao.setForeground(Color.BLACK);
		lblNDoCartao.setFont(new Font("Masque", Font.PLAIN, 10));
		lblNDoCartao.setBounds(189, 185, 180, 16);
		contentPane.add(lblNDoCartao);

		txtcartao = new JTextFieldSoNumero();
		txtcartao.setForeground(Color.BLACK);
		txtcartao.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcartao.setColumns(10);
		txtcartao.setBounds(189, 201, 173, 30);
		contentPane.add(txtcartao);

		txtvalidade = new JDateChooser("dd/MM/yyyy", "##/##/#####", ' ');
		txtvalidade.setBounds(381, 201, 128, 28);
		contentPane.add(txtvalidade);
		

		JLabel lblValidadeDoCard = new JLabel("Validade do card:");
		lblValidadeDoCard.setForeground(Color.BLACK);
		lblValidadeDoCard.setFont(new Font("Masque", Font.PLAIN, 10));
		lblValidadeDoCard.setBounds(381, 185, 126, 16);
		contentPane.add(lblValidadeDoCard);
		
				JButton botcarregar = new JButton("");
				botcarregar.setIcon(new ImageIcon("icon\\clip.png"));
				botcarregar.setBounds(637, 166, 32, 28);
				contentPane.add(botcarregar);
				botcarregar.addActionListener(new ActionListener() {

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
								foto.setIcon(new ImageIcon(corte.setCorte(imagem)));// seta no jlabel
								local = fileChooser.getSelectedFile().getPath();
							} catch (IOException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage());
							}
						}
					}
				});
		if(id!=0) {		
		preencherCampo(id);
	    }
		setVisible(true);
		txtnome.requestFocus();
		repaint();

	}
	public void preencherCampo(int id) {
		con=CTPaciente.get(id);
	    txtcod.setText(String.format("%010d",con.getId()));
		txtnome.setText(con.getNome());
		txtsexo.setSelectedItem(con.getSexo());
	    txtcpf.setText(con.getCPF());
		txtrg.setText(con.getRg());
		txtDatanasc.setDate(con.getDataNasc());
		txtidade.setText(""+con.getIdade());
		txtcivil.setSelectedItem(con.getEstadoCivil());
		txtcor.setSelectedItem(con.getCor());
	    txtaltura.setText(""+con.getAltura());
	    txtpeso.setText(""+con.getPeso());
		cbxsangue.setSelectedItem(con.getTipoSangue());
		cbxconvenio.setSelectedItem(con.getConvenio());
		txtcartao.setText(con.getNumCartao());
		txtvalidade.setDate(con.getValidadeCrad());
        txtprof.setText(con.getProfissao());
        txtcep.setText(con.getCep());
        txtrua.setText(	con.getRua());
        txtcomp.setText( con.getComplemento());
        txtnum.setText(	con.getNum());
		txtbairro.setText(con.getBairro());
		txtcidade.setText(con.getCidade());
	    cbxuf.setSelectedItem(con.getUf());
		txtzap.setText(con.getCelular1());
		txtfone.setText(con.getTelFixo());
		txtrecado.setText(con.getCelular2( ));
		txtemail.setText(	con.getEmil());
		if(!con.getFoto().equals("")) {
		CortaImagem corte = new CortaImagem();
		File arquivo =new File(con.getFoto());// arquivo
		try {
			imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
			foto.setIcon(new ImageIcon(corte.setCorte(imagem)));
			local=con.getFoto();// seta no jlabel
		} catch (IOException ex) {
			
		}
		}
        txtobs.setText(con.getObs());
		
	}
}
