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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;
import CONTROL.CTPaciente;
import MODEL.Paciente;
import VIEW.tabela.ModeloTabela;

public class TelaPrePaciente extends Principal{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4484478029543876770L;
	private JPanel pacientetentPane;
	private JTextField txtnome;
	private JTextField txtid;
	private MaskFormatter telefone = null;
	private Paciente paciente;
	private JFormattedTextField txtcelular;
	private JFormattedTextField txtzap;
	
	public TelaPrePaciente() {
		setTitle("Ficha pré cadastro do Paciente");
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);// seuJFrame
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setBounds(100, 100, 460, 267);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ModeloTabela.ativarModeloTela(this);
		this.setBackground(new Color(255, 255, 255));
		pacientetentPane = new JPanel();
		pacientetentPane.setBackground(new Color(255, 255, 255));
		pacientetentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(pacientetentPane);
		pacientetentPane.setLayout(null);
		
		try {
			telefone = new MaskFormatter("(##)#####-####");
			telefone.setValidCharacters("0123456789");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 22, 186, 16);
		pacientetentPane.add(label);
		
		JLabel label_1 = new JLabel("++Nome :");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 10));
		label_1.setBounds(106, 50, 329, 16);
		pacientetentPane.add(label_1);
		
		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);
		txtnome.setBounds(106, 66, 329, 32);
		pacientetentPane.add(txtnome);
		
		txtcelular = new JFormattedTextField(telefone);
		txtcelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcelular.setBounds(18, 128, 126, 32);
		pacientetentPane.add(txtcelular);
		
		JLabel label_2 = new JLabel("Celular:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Masque", Font.PLAIN, 10));
		label_2.setBounds(18, 111, 126, 16);
		pacientetentPane.add(label_2);
		
	    txtzap	 = new JFormattedTextField();
		txtzap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtzap.setBounds(152, 127, 126, 32);
		pacientetentPane.add(txtzap);
		
		

		
		JLabel label_3 = new JLabel("Whatsapp:");
		label_3.setIcon(new ImageIcon("ipaciente\\zapp.png"));
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Masque", Font.PLAIN, 10));
		label_3.setBounds(152, 110, 126, 16);
		pacientetentPane.add(label_3);
		
		JButton btnsalvar = new JButton("salvar");
		btnsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvar.setFont(new Font("Masque", Font.BOLD, 11));
		btnsalvar.setBounds(322, 182, 113, 39);
		pacientetentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				
			}
		});
		int t = CONTROL.CTPaciente.getId();
		txtid = new JTextField(String.format("%010d", t+1));
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(Color.GREEN);
		txtid.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtid.setEditable(false);
		txtid.setBounds(20, 66, 82, 30);
		pacientetentPane.add(txtid);
		
		JLabel label_4 = new JLabel("Protuario:");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Masque", Font.PLAIN, 10));
		label_4.setBounds(20, 50, 97, 16);
		pacientetentPane.add(label_4);
		setVisible(true);
		txtnome.requestFocus();
	}

	public void salvar() {
		paciente = new Paciente();
		paciente.setId(Integer.parseInt(txtid.getText()));
		paciente.setCPF(txtid.getText());
		paciente.setNome(txtnome.getText().toUpperCase());
		paciente.setCelular1(txtzap.getText());
		paciente.setCelular2(txtcelular.getText());
		paciente.setFoto("");
		paciente.setCor("-");
		paciente.setSexo("-");
		paciente.setEstadoCivil("-");
		paciente.setTipoSangue("-");
		paciente.setRg("");
		paciente.setDataNasc(null);
		paciente.setIdade(0);
		paciente.setAltura(0.00f);
		paciente.setPeso(0.00f);
		paciente.setTipoSangue("-");
		paciente.setConvenio("");
		paciente.setNumCartao("");
		paciente.setValidadeCrad(null);
		paciente.setProfissao("");
		paciente.setCep("");
		paciente.setRua("");
	    paciente.setComplemento("");
		paciente.setNum("");
		paciente.setBairro("");
		paciente.setCidade("");
		paciente.setUf("-");
		
		paciente.setEmil("");
		paciente.setObs("");
		
		if(!txtnome.getText().equals("")){	
				try {
					CTPaciente.insert(paciente);
					JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
					dispose();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
		else {
			JOptionPane.showMessageDialog(null, "Campo Nome em branco","Erro de campo",JOptionPane.ERROR_MESSAGE);
		}
	}
}
