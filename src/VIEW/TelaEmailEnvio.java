package VIEW;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import org.apache.commons.mail.EmailException;
import CONTROL.CTPaciente;
import CONTROL.JavaMail;
import VIEW.tabela.ModeloTabela;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TelaEmailEnvio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JLabel  carregar = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaEmailEnvio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEmailEnvio() {
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.DARK_GRAY);
		setTitle("Enviar -  Email");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 500, 464);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDadosDoEmail = new JLabel("Dados do email");
		lblDadosDoEmail.setForeground(new Color(0, 102, 51));
		lblDadosDoEmail.setFont(new Font("Masque", Font.PLAIN, 13));
		lblDadosDoEmail.setBounds(19, 20, 266, 22);
		contentPane.add(lblDadosDoEmail);
		
		JLabel lblDestinatario = new JLabel("Destinatario:");
		lblDestinatario.setForeground(Color.BLACK);
		lblDestinatario.setFont(new Font("Masque", Font.PLAIN, 11));
		lblDestinatario.setBounds(22, 47, 247, 16);
		contentPane.add(lblDestinatario);
		
		JComboBox<String> cbxdest = new JComboBox<String>();
		cbxdest.setModel(new DefaultComboBoxModel<String>(CTPaciente.getListaNomesEmail()));
		cbxdest.setFont(new Font("Verdana", Font.BOLD, 12));
		cbxdest.setBounds(19, 65, 319, 35);
		contentPane.add(cbxdest);
		
		JTextField txttitulo = new JTextField();
		txttitulo.setForeground(Color.BLACK);
		txttitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		txttitulo.setColumns(10);
		txttitulo.setBounds(19, 129, 448, 40);
		contentPane.add(txttitulo);
		
		JLabel lblTituloDoEmail = new JLabel("Titulo do Email:");
		lblTituloDoEmail.setForeground(Color.BLACK);
		lblTituloDoEmail.setFont(new Font("Masque", Font.PLAIN, 12));
		lblTituloDoEmail.setBounds(22, 112, 186, 16);
		contentPane.add(lblTituloDoEmail);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setForeground(Color.BLACK);
		lblMensagem.setFont(new Font("Masque", Font.PLAIN, 12));
		lblMensagem.setBounds(19, 173, 102, 16);
		contentPane.add(lblMensagem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 196, 448, 163);
		contentPane.add(scrollPane);
		
		JTextArea txtmensagem = new JTextArea();
		txtmensagem.setFont(new Font("SansSerif", Font.BOLD, 13));
		scrollPane.setColumnHeaderView(txtmensagem);
		
		
		JButton btnenviar = new JButton("Enviar");
		btnenviar.setSelectedIcon(new ImageIcon("icon\\enviar2.png"));
		btnenviar.setIcon(new ImageIcon("icon\\emailp.png"));
		btnenviar.setFont(new Font("Marcellus SC", Font.BOLD, 13));
		btnenviar.setBounds(227, 371, 120, 42);
		contentPane.add(btnenviar);
		btnenviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaLoanding();
				
				
				String dest=CTPaciente.get(""+cbxdest.getSelectedItem()).getEmil();
				String tema=txttitulo.getText();
				String text =txtmensagem.getText();
			
				try {
					if(tema.equals("")) {
						JOptionPane.showMessageDialog(null, "Campo assunto vazio", "Alerta", JOptionPane.ERROR_MESSAGE);
						txttitulo.requestFocus();
					}
					else if(text.equals("")) {
						JOptionPane.showMessageDialog(null, "Campo da messagem vazio", "Alerta", JOptionPane.ERROR_MESSAGE);
						txtmensagem.requestFocus();
					}
					else {
						JavaMail enviar=new JavaMail();
						cbxdest.requestFocus();
						enviar.enviarEmail(dest, tema, text);
						JOptionPane.showMessageDialog(null,"Email enviado com sucesso!");
					}
				} catch (EmailException e1) {
					JOptionPane.showMessageDialog(null, "Email Não encontrado", "Alerta", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JButton btncan = new JButton("Cancelar");
		btncan.setIcon(new ImageIcon("icon\\del.png"));
		btncan.setFont(new Font("Marcellus SC", Font.BOLD, 14));
		btncan.setBounds(347, 371, 120, 42);
		btncan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btncan);
		
		
		carregar.setBounds(6, 413, 459, 16);
		contentPane.add(carregar);
		setVisible(true);
	}
	
}
