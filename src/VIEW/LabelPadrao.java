package VIEW;

/**
 * @author Agemiro
 */

import java.awt.Color;

import javax.swing.JLabel;

public class LabelPadrao extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabelPadrao(String texto, int x, int y, int w, int z) {
		
		setText(texto);
		setBounds(x, y, w, z);
		setForeground(Color.BLACK);
		
	}

}
