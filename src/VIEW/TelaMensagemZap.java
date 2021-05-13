package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import CONTROL.CTPaciente;
import DAO.ContatosTxt;
import VIEW.tabela.ModeloTabela;

public class TelaMensagemZap extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtMens;

	public TelaMensagemZap() {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Mensagema automatica zap");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 500, 312);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 21, 186, 16);
		contentPane.add(label);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.BLACK);
		lblCliente.setFont(new Font("Masque", Font.PLAIN, 11));
		lblCliente.setBounds(14, 38, 328, 16);
		contentPane.add(lblCliente);
		
		JComboBox<String> cbxcCliente = new JComboBox<String>();
		cbxcCliente.setModel(new DefaultComboBoxModel<String>(CTPaciente.getListaMensagemZap()));
		cbxcCliente.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxcCliente.setBounds(10, 53, 378, 32);
		contentPane.add(cbxcCliente);
		
		JButton btnPesq = new JButton("");
		btnPesq.setIcon(new ImageIcon("icon\\pesquisaP.png"));
		btnPesq.setBounds(389, 53, 32, 32);
		contentPane.add(btnPesq);
		btnPesq.setVisible(false);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Masque", Font.PLAIN, 11));
		lblTitulo.setBounds(15, 90, 80, 16);
		contentPane.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setForeground(Color.BLACK);
		txtTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(14, 107, 374, 32);
		contentPane.add(txtTitulo);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setForeground(Color.BLACK);
		lblMensagem.setFont(new Font("Masque", Font.PLAIN, 11));
		lblMensagem.setBounds(15, 149, 101, 16);
		contentPane.add(lblMensagem);
		
		txtMens = new JTextField();
		txtMens.setForeground(Color.BLACK);
		txtMens.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMens.setColumns(10);
		txtMens.setBounds(14, 166, 462, 41);
		contentPane.add(txtMens);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setSelectedIcon(new ImageIcon("icon\\enviar2.png"));
		btnEnviar.setIcon(new ImageIcon("icon\\zapp.png"));
		btnEnviar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEnviar.setFont(new Font("Masque", Font.BOLD, 11));
		btnEnviar.setBounds(232, 219, 113, 39);
		contentPane.add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ContatosTxt cox = new ContatosTxt();
				new TelaLoanding();
			   cox.enviarMensagemZap((String) cbxcCliente.getSelectedItem(), txtTitulo.getText(), txtMens.getText());
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("icon\\del.png"));
		btnCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelar.setFont(new Font("Masque", Font.BOLD, 11));
		btnCancelar.setBounds(346, 219, 130, 39);
		contentPane.add(btnCancelar);
		setVisible(true);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}
}
