package VIEW;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ComandText {

	public static void limpar(JTextField txt) {
		txt.setText("");
	}

	public static double Soma(JTextField valor1, JTextField valor2) {
		double q = Double.parseDouble(valor1.getText());
		double p = Double.parseDouble(valor2.getText());
		double total = q + p;
		return total;
	}

	public static double mutiplica(JTextField quant, JTextField preco) {
		double q = Double.parseDouble(quant.getText());
		double p = Double.parseDouble(preco.getText());
		double total = q * p;
		return total;
	}

	public static double diminuir(JTextField valor1, JTextField valor2) {
		double q = Double.parseDouble(valor1.getText());
		double p = Double.parseDouble(valor2.getText());
		double total = q - p;
		return total;
	}

	public static double diminuir(JLabel valor1, JTextField valor2) {
		double q = Double.parseDouble(valor1.getText());
		double p = Double.parseDouble(valor2.getText());
		double total = q - p;
		return total;
	}

	public static double troco(JLabel valor1, JTextField valor2) {
		double q = Double.parseDouble(valor1.getText());
		double p = Double.parseDouble(valor2.getText());
		double total = p - q;
		return total;
	}

	public static double porcentagem(JTextField valor2, JLabel valor1) {
		double valor = Double.parseDouble(valor1.getText());
		double porc = Double.parseDouble(valor2.getText());
		double p = (valor * porc) / 100;
		double t = valor - p;
		return t;
	}

	public static boolean dataValida(JTextField data) {
		boolean band = true;
		String [] lista = data.getText().split("/");
		int dia=Integer.parseInt(lista[0]);
		int mes=Integer.parseInt(lista[1]);
		if(dia>31) {
			band=false;
		}
		else if(mes>12) {
			band=false;
		}
		
		
		return band;
	}
}
