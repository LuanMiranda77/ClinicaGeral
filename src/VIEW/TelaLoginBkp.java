package VIEW;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import VIEW.tabela.ModeloTabela;

public class TelaLoginBkp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar barra = new JProgressBar();
	private int mlSegundos;

	public TelaLoginBkp() {
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.DARK_GRAY);
		setTitle("Processando seu Pedido....");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 586, 132);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		 
		mlSegundos=1000;
		
		barra.setMaximum(mlSegundos);
		barra.setStringPainted(true);
		barra.setBounds(22, 52, 527, 34);
		new Temporizador().start();
		contentPane.add(barra);
		
		JLabel lblNewLabel = new JLabel("Criando o Bacukp aguarde...");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 523, 40);
		contentPane.add(lblNewLabel);
		setVisible(true);
		
	
		
		
	}
	
	
	public class Temporizador extends Thread{
		public void run() {
			while(barra.getValue()<mlSegundos) {
				try {
					sleep(10);
					barra.setValue(barra.getValue()+10);
					if(barra.getValue()==mlSegundos) {
						dispose();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
