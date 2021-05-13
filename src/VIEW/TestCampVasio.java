package VIEW;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TestCampVasio {
	
	public static boolean test(JTextField txt) {
		if(txt.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo em branco, por vafor preencher!");
			return false;
		}
		return true;
	}

}
