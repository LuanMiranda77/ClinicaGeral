package VIEW;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelGeral extends JLabel{
	private String fonte="Century Gothic";
	private int tamanho=13;
	private static final long serialVersionUID = 1L;
	
	public String getFonte() {
		return fonte;
	}
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public LabelGeral(String text) {
		setText(text);
		setFont(new Font(fonte, Font.BOLD, tamanho));
		
	}
	public void corLetra(int r, int g, int d) {
		setForeground(new Color(r, g,d ));
	}

}
