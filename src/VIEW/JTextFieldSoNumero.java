package VIEW;
/**
*@author luanp
*/
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JTextFieldSoNumero extends JTextField {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		// como  valor normal de um textfield sem limite de caracters
		public JTextFieldSoNumero() {
		        super();
		        addKeyListener(new java.awt.event.KeyAdapter() {
		        
		            @Override
		    public void keyTyped(java.awt.event.KeyEvent evt) {
		        jTextFieldKeyTyped(evt);}});
		    }
		
		
		        private void jTextFieldKeyTyped(KeyEvent evt) {
		       
		String caracteres="0987654321.";// lista de caracters que devem ser aceitos
		if(!caracteres.contains(evt.getKeyChar()+"")){// se o caracter que gerou o 

		evt.consume();//aciona esse propriedade para eliminar a ação do evento
		}
		        }

}
