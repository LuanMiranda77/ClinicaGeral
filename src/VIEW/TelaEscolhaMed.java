package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import CONTROL.CTProfissional;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;

@SuppressWarnings("serial")
public class TelaEscolhaMed extends Principal {

	private JPanel contentPane;
	private JComboBox<String> cbxmedico;
	private Profissional medico;

	
	public TelaEscolhaMed() {
		setTitle("Selecione - Medico");
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 370, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);

		JLabel label = new JLabel("Medico Especialista:");
		label.setBounds(15, 38, 314, 16);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Masque", Font.PLAIN, 11));

		cbxmedico = new JComboBox<String>();
		cbxmedico.setBounds(15, 56, 336, 35);
		cbxmedico.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxmedico.setModel(new DefaultComboBoxModel<String>(CTProfissional.getListaNomes()));
		cbxmedico.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxmedico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cbxmedico.getSelectedItem().equals("-")) {
					medico = CTProfissional.get("" + cbxmedico.getSelectedItem());

				}
			}
		});

		JLabel label_1 = new JLabel("Selecione o M\u00E9dico");
		label_1.setBounds(5, 16, 186, 16);
		label_1.setForeground(new Color(0, 102, 51));
		label_1.setFont(new Font("Masque", Font.PLAIN, 13));

		JButton btnok = new JButton("OK");
		btnok.setBounds(146, 117, 81, 29);
		btnok.setIcon(new ImageIcon("icon\\sal.png"));
		btnok.setHorizontalAlignment(SwingConstants.LEFT);
		btnok.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		btnok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cbxmedico.getSelectedItem().equals("-")) {
					dispose();
					new TelaConsultaAg(medico);
				} else {
					JOptionPane.showMessageDialog(null, "Escolha um médico !");
				}
			}
		});

		JButton btncan = new JButton("Cancelar");
		btncan.setBounds(233, 117, 118, 29);
		btncan.setIcon(new ImageIcon("icon\\del.png"));
		btncan.setHorizontalAlignment(SwingConstants.LEFT);
		btncan.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		btncan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(cbxmedico);
		contentPane.add(label);
		contentPane.add(label_1);
		contentPane.add(btnok);
		contentPane.add(btncan);
		setVisible(true);
	}
}
