package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class TelaPesquisaPreco extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1542021923091551205L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private NumberFormat convertMoeda = NumberFormat.getCurrencyInstance();
	private JLabel preco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPesquisaPreco frame = new TelaPesquisaPreco();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPesquisaPreco() {
		setBackground(Color.DARK_GRAY);
		setTitle("Consulta Preço");
		pegarResolucao();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setType(java.awt.Window.Type.UTILITY);//nao minimizar
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 componetes();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
       
		setVisible(true);
	}
	public void pegarResolucao() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
        this.setSize((dimensao.width -500), (dimensao.height - 150));

 }
	public void componetes() {
		
		txtPesquisa = new JTextField();
		txtPesquisa.setForeground(Color.WHITE);
		txtPesquisa.setBackground(Color.DARK_GRAY);
		txtPesquisa.setFont(new Font("Tahoma", Font.BOLD, 23));
		txtPesquisa.setBounds(10, 118, 354, 55);
		contentPane.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		txtPesquisa.setBorder(new TitledBorder(null, "Codigo Barras", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		//txtPesquisa.setOpaque(false);
		txtPesquisa.setCaretColor(Color.WHITE);
		contentPane.add(txtPesquisa);
		txtPesquisa.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					dispose();
					
				}
				
			}
		});
		
		JLabel nome = new JLabel("ciemnto\r\n");
		nome.setBackground(Color.DARK_GRAY);
		nome.setForeground(Color.WHITE);
		nome.setFont(new Font("Tahoma", Font.PLAIN, 71));
		nome.setBounds(10, 195, 743, 141);
		nome.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nome do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 200, 0)));
		contentPane.add(nome);
		
		preco = new JLabel("0.00");
		preco.setFont(new Font("Tahoma", Font.BOLD, 80));
		preco.setBackground(Color.ORANGE);
		preco.setForeground(Color.DARK_GRAY);
		preco.setBounds(236, 358, 517, 120);
	    preco.setOpaque(true);
		preco.setBorder(new EmptyBorder(0, 0, 0, 0));
		preco.setText(convertMoeda.format(Double.parseDouble(preco.getText())));
		contentPane.add(preco);
		
		JLabel titulo = new JLabel("Consulta Pre\u00E7o");
		titulo.setBackground(Color.ORANGE);
		titulo.setForeground(Color.GRAY);
		titulo.setFont(new Font("Century Schoolbook", Font.PLAIN, 40));
		titulo.setBounds(10, 11, 289, 61);
		contentPane.add(titulo);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o");
		lblPreo.setBackground(Color.DARK_GRAY);
		lblPreo.setForeground(Color.GRAY);
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 71));
		lblPreo.setBounds(34, 347, 216, 141);
		contentPane.add(lblPreo);
		
		JLabel logo = new JLabel(new ImageIcon("icon/logoPequenaPP.png"));
		logo.setBounds(680, 26, 146, 106);
		contentPane.add(logo);
		
		JLabel infor = new JLabel("<HTML>PISTOLE O PRODUTO PARA CONSULTAR O PRE\u00C7O<BR>ESC- SAIR");
		infor.setHorizontalAlignment(SwingConstants.LEFT);
		infor.setForeground(Color.ORANGE);
		infor.setBackground(Color.WHITE);
		infor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		infor.setToolTipText("");
		infor.setBounds(10, 489, 566, 79);
		infor.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		contentPane.add(infor);
		
	}
}
