package VIEW;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotoesGeral extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6510709916206563652L;
	private String fonte="Century Gothic";
	private int tamanho=13;
	
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

	public BotoesGeral(String n,ImageIcon ic,int x,int y, int w, int h) {
	setBounds(x, y	, w, h);
	setIcon(ic);
	setText(n);
	setToolTipText(n);
	setVerticalTextPosition(SwingConstants.BOTTOM);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setFont(new Font(fonte,Font.BOLD,tamanho));
	setOpaque(false);
	setBorderPainted(false);
    setBackground(null);
		
	}
	public BotoesGeral(String n,ImageIcon ic) {
		setIcon(ic);
		setText(n);
		setToolTipText(n);
		setOpaque(false);
		setBorderPainted(false);
	    setBackground(new Color(1,1,1));
			
		}

}
