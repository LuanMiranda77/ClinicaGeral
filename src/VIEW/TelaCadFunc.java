package VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;
import CONTROL.CTFuncionario;
import CONTROL.CTProfissional;
import MODEL.Funcionario;
import VIEW.tabela.ModeloTabela;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class TelaCadFunc extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private MaskFormatter cel1 = null;
	private JPasswordField txtsenha1;
	private JPasswordField txtsenha2;
	private Funcionario	n;

	public TelaCadFunc(int id,String tela) {
		setTitle("Cadastro - Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setBounds(500, 200, 560, 228);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ModeloTabela.ativarModeloTela(this);

		try {
			cel1 = new MaskFormatter("(##)#.####-####");
			cel1.setValidCharacters("0123456789");
		} catch (ParseException e1) {
			new TelaErroLog(e1.getMessage(), "erro na", getTitle());
		}
		
		JLabel lblMedico = new JLabel("Medico:");
		lblMedico.setForeground(Color.BLACK);
		lblMedico.setFont(new Font("Masque", Font.PLAIN, 11));
		lblMedico.setBounds(298, 94, 80, 16);
		lblMedico.setVisible(false);
		
		JComboBox<String> cbxmedico = new JComboBox<String>();
		cbxmedico.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxmedico.setBounds(294, 111, 247, 30);
		cbxmedico.setVisible(false);
		contentPane.add(cbxmedico);

		JComboBox<String> txtCargo = new JComboBox<String>();
		txtCargo.setFont(new Font("SansSerif", Font.BOLD, 13));
		txtCargo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "GERENTE", "MEDICO", "ATENDENTE", "SECRETARIA", "AUX.GERAL" }));
		txtCargo.setBounds(294, 64, 126, 30);
		contentPane.add(txtCargo);
		txtCargo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtCargo.getSelectedItem().equals("MEDICO")) {
					cbxmedico.setModel(new DefaultComboBoxModel<String>(CTProfissional.getListaNomes()));
					cbxmedico.setVisible(true);
					lblMedico.setVisible(true);
				}
				else {
					cbxmedico.setVisible(false);
					lblMedico.setVisible(false);
					
				}
				
			}
		});

		JButton btSalvar = new JButton("SALVAR");
		btSalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btSalvar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btSalvar.setBounds(109, 153, 99, 28);
		contentPane.add(btSalvar);
		btSalvar.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(id==0) {
				n = new Funcionario();
				n.setId(CTFuncionario.getId()+1);
				}
				n.setNome(txtnome.getText().toUpperCase());
				n.setCargo(txtCargo.getSelectedItem().toString());
				n.setSenha(txtsenha1.getText());
				n.setNomeProf((String) cbxmedico.getSelectedItem());

				if (txtsenha1.getText().equals(txtsenha2.getText())) {
					if (id==0) {
						if(txtCargo.getSelectedItem().equals("MEDICO")&&cbxmedico.getSelectedItem().equals("-")) {
							JOptionPane.showMessageDialog(null, "Escolha o nome do Medico!");
						}
						else {
						CTFuncionario.insert(n);
						JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
						if(!tela.equals("")) {
						TelaGerFunc.addLinha(n);
						}
						dispose();
						}
					} else {
						CTFuncionario.update(n);
						JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
						dispose();
						new TelaGerFunc();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Senhas não compativeis!");
				}
			}
		});

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon("icon\\del.png"));
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCancelar.setBounds(228, 153, 114, 28);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 18, 186, 16);
		contentPane.add(label);

		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);
		txtnome.setBounds(10, 63, 268, 30);
		contentPane.add(txtnome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Masque", Font.PLAIN, 11));
		lblNome.setBounds(12, 47, 145, 16);
		contentPane.add(lblNome);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setForeground(Color.BLACK);
		lblCargo.setFont(new Font("Masque", Font.PLAIN, 11));
		lblCargo.setBounds(298, 47, 80, 16);
		contentPane.add(lblCargo);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Masque", Font.PLAIN, 11));
		lblSenha.setBounds(12, 95, 145, 16);
		contentPane.add(lblSenha);

		JLabel lblSenhaNovamente = new JLabel("Senha novamente:");
		lblSenhaNovamente.setForeground(Color.BLACK);
		lblSenhaNovamente.setFont(new Font("Masque", Font.PLAIN, 11));
		lblSenhaNovamente.setBounds(150, 95, 145, 16);
		contentPane.add(lblSenhaNovamente);

		txtsenha1 = new JPasswordField();
		txtsenha1.setHorizontalAlignment(SwingConstants.LEFT);
		txtsenha1.setFont(new Font("SansSerif", Font.BOLD, 17));
		txtsenha1.setBounds(10, 111, 126, 30);
		contentPane.add(txtsenha1);

		txtsenha2 = new JPasswordField();
		txtsenha2.setFont(new Font("SansSerif", Font.BOLD, 17));
		txtsenha2.setHorizontalAlignment(SwingConstants.LEFT);
		txtsenha2.setBounds(146, 111, 132, 30);
		contentPane.add(txtsenha2);
		
		
		
		
		contentPane.add(lblMedico);
		if (id != 0) {
			n=CTFuncionario.get(id);			
			txtnome.setText(n.getNome());
			txtCargo.setSelectedItem(n.getCargo());
			txtsenha1.setText(n.getSenha());
			txtsenha2.setText(n.getSenha());
		}
		setVisible(true);

	}
}
