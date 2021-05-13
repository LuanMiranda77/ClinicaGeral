package VIEW;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;


public class Principal extends JFrame {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Principal() {
		pegarResolucao();
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon\\icone.png"));
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setTitle("Principal");
		setVisible(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		//blackgroud(altura, largura);
		
		
		
	}
	public void blackgroud(int a,int l) {
		JLabel contabil = new JLabel(new ImageIcon("icon/fundo.jpg"));
		contabil.setBounds(1,1,a,l);
		add(contabil);
		
	}
	public void pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		@SuppressWarnings("unused")
		Dimension dimensao = t.getScreenSize();
		this.setSize(1278, 730);

	}


	
}
