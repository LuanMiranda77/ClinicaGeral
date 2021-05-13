package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import CONTROL.CTConvenio;
import CONTROL.JavaMail;
import MODEL.Convenio;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;

public class TelaCadConvenio extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtemail;
	private JTable table;
	private static DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private Convenio novo;
	private JComboBox<String> cbx;
	private JFormattedTextField txtcel;
	private JFormattedTextField txtfixo;
	private MaskFormatter fone = null;
	private MaskFormatter fonefix = null;

	public TelaCadConvenio() {
		contentPane = new JPanel();
		setBackground(Color.DARK_GRAY);
		setTitle("Cadastro - Convenio");

		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		ModeloTabela.ativarModeloTela(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 200, 910, 404);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(11, 4, 6, 4, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setBounds(30, 19, 186, 16);
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));

		JLabel lblNomeDaConvenio = new JLabel("Nome da Convenio:");
		lblNomeDaConvenio.setBounds(43, 47, 139, 16);
		lblNomeDaConvenio.setForeground(Color.BLACK);
		lblNomeDaConvenio.setFont(new Font("Masque", Font.PLAIN, 10));

		txtnome = new JTextField();
		txtnome.setBounds(40, 63, 234, 25);
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtnome.setColumns(10);

		JLabel lblTabela = new JLabel("Tabela:");
		lblTabela.setBounds(287, 47, 138, 16);
		lblTabela.setForeground(Color.BLACK);
		lblTabela.setFont(new Font("Masque", Font.PLAIN, 11));

		cbx = new JComboBox<String>();
		cbx.setBounds(286, 62, 121, 26);
		cbx.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-", "AMB", "CIEFAS", "CBHPM 2003", "CBHPM 2004", "CBHPM 2005", "TUSS 1.02" }));
		cbx.setFont(new Font("Meiryo UI", Font.BOLD, 12));
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(lblNomeDaConvenio);
		contentPane.add(txtnome);
		contentPane.add(cbx);
		contentPane.add(lblTabela);

		JLabel label_1 = new JLabel("CONTATO");
		label_1.setForeground(new Color(0, 102, 51));
		label_1.setFont(new Font("Masque", Font.PLAIN, 13));
		label_1.setBounds(413, 19, 186, 16);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("TEL.CELULAR:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Masque", Font.PLAIN, 11));
		label_2.setBounds(419, 47, 126, 16);
		contentPane.add(label_2);

		try {
			fone = new MaskFormatter("(##)#.####-####");
			fone.setValidCharacters("0123456789");
			fonefix = new MaskFormatter("(##)####-####");
			fonefix.setValidCharacters("0123456789");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtcel = new JFormattedTextField(fone);
		txtcel.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcel.setBounds(419, 64, 126, 25);
		contentPane.add(txtcel);

		txtfixo = new JFormattedTextField(fonefix);
		txtfixo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfixo.setBounds(562, 64, 126, 25);
		contentPane.add(txtfixo);

		JLabel label_3 = new JLabel("TEL.RESIDENCIAL:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Masque", Font.PLAIN, 11));
		label_3.setBounds(562, 47, 126, 16);
		contentPane.add(label_3);

		JLabel label_5 = new JLabel("e-mail:");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Masque", Font.PLAIN, 11));
		label_5.setBounds(700, 48, 149, 16);
		contentPane.add(label_5);

		txtemail = new JTextField();
		txtemail.setForeground(Color.BLACK);
		txtemail.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtemail.setColumns(10);
		txtemail.setBounds(700, 64, 186, 25);
		contentPane.add(txtemail);

		JButton btnsalvar = new JButton("salvar");
		btnsalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btnsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvar.setFont(new Font("Masque", Font.BOLD, 11));
		btnsalvar.setBounds(314, 124, 113, 32);
		contentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtnome.getText().equals("") && !cbx.getSelectedItem().equals("-") && !txtemail.getText().equals("")) {
						if (JavaMail.validaEmail(txtemail.getText()) == false) {
							JOptionPane.showMessageDialog(null, "Email inválido", "Erro de Email",
									JOptionPane.ERROR_MESSAGE);
						}else {
						JOptionPane.showMessageDialog(null, "Erro campo em branco", "Erro cadastro",
								JOptionPane.ERROR_MESSAGE);
						salvar();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Erro de campo em branco","Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setIcon(new ImageIcon("icon\\del.png"));
		btnexcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnexcluir.setFont(new Font("Masque", Font.BOLD, 11));
		btnexcluir.setBounds(432, 124, 113, 32);
		contentPane.add(btnexcluir);
		btnexcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um item para ser excluido");
				} else {
					CTConvenio.remove((int) table.getValueAt(row, 0));
					modelo.removeRow(row);
				}

			}
		});
		table();
		setVisible(true);
		repaint();
	}

	public void table() {

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(19, 168, 867, 195);
		contentPane.add(scrollPane);

		modelo.addColumn("COD"); // coluna 0
		modelo.addColumn("DESCRIÇÃO"); // coluna 1
		modelo.addColumn("TABELA");
		modelo.addColumn("CELULAR"); // coluna 0
		modelo.addColumn("FIXO"); // coluna 1
		modelo.addColumn("EMAIL");
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);

		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());

		CTConvenio.preencherLista();

	}

	public static void addLinha(Convenio novo) {
		modelo.addRow(new Object[] { novo.getId(), novo.getNome(), novo.getTabela(), novo.getCelular(), novo.getFixo(),
				novo.getEmail() });
	}

	public void salvar() {
		novo = new Convenio();
		novo.setId(CTConvenio.getId() + 1);
		novo.setNome(txtnome.getText().toUpperCase());
		novo.setTabela("" + cbx.getSelectedItem());
		novo.setCelular(txtcel.getText());
		novo.setFixo(txtfixo.getText());
		novo.setEmail(txtemail.getText());
		CTConvenio.insert(novo);
		addLinha(novo);
		txtnome.setText("");
		cbx.setSelectedItem("-");
		txtcel.setText("");
		txtfixo.setText("");
		txtemail.setText("");
		txtnome.setText("");
		txtnome.requestFocus();

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaCadConvenio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
