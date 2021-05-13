package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import org.apache.commons.mail.EmailException;
import CONTROL.JavaMail;
import VIEW.tabela.ModeloTabela;

public class TelaErroLog extends Principal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public TelaErroLog(String log,String nome,String tela) {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Tela log de erro");
		setResizable(false);//seuJFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 519, 378);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(255, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 493, 222);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea(log);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 18));
		textArea.setForeground(new Color(204, 0, 0));
		textArea.setEditable(false);
		scrollPane.setColumnHeaderView(textArea);
		
		
		
		
		JLabel lbErroTitulo = new JLabel(nome+" - "+tela);
		lbErroTitulo.setBackground(new Color(0, 0, 0));
		lbErroTitulo.setForeground(new Color(204, 0, 0));
		lbErroTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbErroTitulo.setBounds(10, 21, 493, 35);
		lbErroTitulo.setOpaque(true);
		contentPane.add(lbErroTitulo);
		
		JButton btnenviar = new JButton("Enviar Email");
		btnenviar.setFont(new Font("Marcellus SC", Font.BOLD, 13));
		btnenviar.setBounds(196, 290, 120, 42);
		contentPane.add(btnenviar);
		btnenviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			try {
				JavaMail f = new JavaMail();
				f.enviarEmail("luanprof30@gmail.com", lbErroTitulo.getText(),textArea.getText());
				dispose();
			} catch (EmailException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Tela de log-erro", JOptionPane.ERROR_MESSAGE);
			}
				
			}
		});
		setVisible(true);
	}
}
