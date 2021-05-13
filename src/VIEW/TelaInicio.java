package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import CONTROL.CTEmitente;
import MODEL.Conexao.CXChaveSerial;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class TelaInicio  extends JWindow{
	JProgressBar barra = new JProgressBar();
	
	

    /**
 * @autor luan miranda
 */
private static final long serialVersionUID = 1L;
	private int duration;

    public TelaInicio(int d) {
    	GroupLayout groupLayout = new GroupLayout(getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGap(0, 450, Short.MAX_VALUE)
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGap(0, 300, Short.MAX_VALUE)
    	);
    	getContentPane().setLayout(groupLayout);
        duration = d;
    }

// Este é um método simples para mostrar uma tela de apresentção

// no centro da tela durante a quantidade de tempo passada no construtor

    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.WHITE);

        // Configura a posição e o tamanho da janela
        int width = 500;
        int height =400;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        //Constrói o splash screen
       JLabel label = new JLabel(new ImageIcon("icon/logoGraca.png"));
       label.setBounds(35,-50,450, 450);
       add(label,BorderLayout.CENTER);
       
    	
        JLabel load = new JLabel
                ("Carregando...");
        load.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        load.setBounds(180,150, 200, 350);
        add(load);
        
   
        Color oraRed = new Color(20, 128, 128,  20);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));        
        // Torna visível
        barra.setMaximum(4000);
    	barra.setStringPainted(true);
    	content.add(barra);
    	barra.setBounds(50, 350, 400, 34);
    	new Temporizador().start();
        setVisible(true);

        // Espera ate que os recursos estejam carregados
        
        try { Thread.sleep(duration); } catch (Exception e) {}        
        setVisible(false);
    }

    public void showTela() {  
        showSplash();
        
    }
    public class Temporizador extends Thread{
		public void run() {
			while(barra.getValue()<4000) {
				try {
					sleep(10);
					barra.setValue(barra.getValue()+10);
					if(barra.getValue()==4000) {
						dispose();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

    public static void main(String[] args) throws SQLException, ClassNotFoundException {        
        // Mostra uma imagem com o título da aplicação 
    	
     
    	TelaInicio splash = new TelaInicio(4000);
        splash.showTela();
        if(CTEmitente.getEmitente()==null) {
        	new TelaConfig();
        	
        }
        else {
       CXChaveSerial test = new CXChaveSerial();
      /* if(test.testChave()) {
    	   JOptionPane.showMessageDialog(null, "Serial= "+CXChaveSerial.getSerial()+" Espirou por favor \n "
    	   		+ "contate  o suporte (83) 9.9638-6694", "Erro de serial",JOptionPane.ERROR_MESSAGE);
       }
       else {*/
       new TelaLogin();
     //  }
        }

       
		
			
    }

}
