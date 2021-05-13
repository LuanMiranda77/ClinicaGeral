



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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTEmitente;
import CONTROL.CTFuncionario;
import DAO.ContatosTxt;
import DAO.MensagensTxt;
import MODEL.Central;
import MODEL.Funcionario;
import VIEW.tabela.ModeloTabela;

public class TelaLogin extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField txtSenha;
	private JTextField txtNome;
	private JButton botLogar;


	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		ModeloTabela.destivar(this);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		setTitle("Login");
		setBounds(100, 100, 426, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				CTAgendaConsulta c = new CTAgendaConsulta();
				c.testDataListaOrdem();
				MensagensTxt ms = new MensagensTxt();
				ContatosTxt ct = new ContatosTxt();
				ms.limparArquivo();
				ct.limparArquivo();
			    } 
			    
			   
			});

		txtNome = new JTextField();
		txtNome.setBounds(111, 200, 197, 27);
		txtNome.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtNome.setColumns(10);
		txtNome.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				proximo(e);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		txtSenha = new JPasswordField();
		txtSenha.setBounds(111, 261, 195, 27);
		txtSenha.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 18));
		txtSenha.setEchoChar('*');
		txtSenha.setColumns(10);
		txtSenha.setBackground(new Color(193, 212, 244));
		txtSenha.setToolTipText("senha");
		txtSenha.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loga();
				}

			}
		});

		botLogar = new JButton();
		botLogar.setBounds(148, 318, 121, 40);
		botLogar.setIcon(new ImageIcon("icon/botLogin.png"));
		botLogar.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 15));
		botLogar.setOpaque(false);
		botLogar.setBorderPainted(false);
		botLogar.setBackground(new Color(1, 1, 1));
		botLogar.requestFocus();
		botLogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loga();
			}
		});

		JLabel info = new JLabel(new ImageIcon("icon/info.png"));
		info.setBounds(337, 308, 50, 50);

		JLabel lbLogo = new JLabel("");
		lbLogo.setBounds(63, 11, 279, 146);
		
		CortaImagem corte = new CortaImagem();
		File arquivo =new File(CTEmitente.getEmitente().getLogo());// arquivo
		BufferedImage imagem;
		try {
			imagem = ImageIO.read(arquivo); 
			lbLogo.setIcon(new ImageIcon(corte.setCorteFundo(imagem,279,146)));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

		JLabel lblLogin = new JLabel("Nome");
		lblLogin.setBounds(111, 168, 62, 26);
		lblLogin.setForeground(new Color(0, 102, 51));
		lblLogin.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 20));

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(111, 238, 80, 22);
		lblSenha.setForeground(new Color(0, 102, 51));
		lblSenha.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 20));
		getContentPane().setLayout(null);
		getContentPane().add(lblLogin);
		getContentPane().add(txtNome);
		getContentPane().add(lblSenha);
		getContentPane().add(txtSenha);
		getContentPane().add(botLogar);
		getContentPane().add(info);
		getContentPane().add(lbLogo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 420, 371);
		getContentPane().add(panel);
		panel.setLayout(null);
		info.addMouseListener(new MouseListener() {

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
				JOptionPane.showMessageDialog(null,
						"Digite seu usuario e senha para\n" + " acessar o sistema corretamente!", "Informação",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		setVisible(true);

	}

	@SuppressWarnings("deprecation")
	public void loga() {
		Funcionario func = CTFuncionario.get(txtNome.getText().toUpperCase());
		if (func != null) {
			if (txtSenha.getText().equals(func.getSenha())) {
				dispose();
				new Menu(func);
				Central.setLogado(func);
			}
			else {
				JOptionPane.showMessageDialog(null, "Erro Senha ou Usuario errado", "Erro de login",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Erro Senha ou Usuario errado", "Erro de login",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void proximo(KeyEvent even) {

		if (even.getKeyCode() == KeyEvent.VK_ENTER) {
			String nome=CTFuncionario.get(txtNome.getText().toUpperCase()).getNome();
			if(nome==null) {
				JOptionPane.showMessageDialog(null, "Usuario não cadastrado", "Erro de login",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				txtSenha.requestFocus();
			}
			

		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
