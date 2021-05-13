package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.text.PlainDocument;
import CONTROL.CTEspecialidade;
import MODEL.Especialidade;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;

public class TelaCadEspecialidade extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtcod;
	private JTable table;
	private static DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private static Especialidade novo;

	public TelaCadEspecialidade() {
		setTitle("Cadastro - Procedimento");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		ModeloTabela.ativarModeloTela(this);
		setType(java.awt.Window.Type.UTILITY);//nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 150, 450, 360);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int op = JOptionPane.showConfirmDialog(null,"Deseja Cadastrar um Médico?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		        
		        
				if (op==JOptionPane.YES_OPTION){
					 new TelaCadProf("");
				}
				else {
					dispose();
			    } 
			}
			    
			   
			});

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setBounds(0, 0, 0, 0);
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		contentPane.add(label);

		JLabel label_1 = new JLabel("Nome da Convenio:");
		label_1.setBounds(0, 0, 0, 0);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 10));
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label_2.setForeground(new Color(0, 102, 51));
		label_2.setFont(new Font("Masque", Font.PLAIN, 13));
		label_2.setBounds(10, 11, 186, 16);
		contentPane.add(label_2);

		JLabel lblNomeDaProcedimento = new JLabel("Nome da Especialidade:");
		lblNomeDaProcedimento.setForeground(Color.BLACK);
		lblNomeDaProcedimento.setFont(new Font("Masque", Font.PLAIN, 12));
		lblNomeDaProcedimento.setBounds(107, 30, 242, 16);
		contentPane.add(lblNomeDaProcedimento);

		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtnome.setColumns(10);
		txtnome.setBounds(104, 46, 318, 27);
		contentPane.add(txtnome);
		txtnome.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					salvar();

				}

			}
		});
		txtcod = new JTextFieldSoNumero();
		txtcod.setHorizontalAlignment(SwingConstants.CENTER);
		txtcod.setDocument(new PlainDocument());
		txtcod.setForeground(Color.GREEN);
		txtcod.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcod.setColumns(10);
		txtcod.setBounds(11, 46, 86, 27);
		contentPane.add(txtcod);
		txtcod.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			  int tam = txtcod.getText().length();
			  if(tam>4) {
				  JOptionPane.showMessageDialog(null, "Limite de campo e 4 numeros");
			  }
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel lblCodigo = new JLabel("CBO:");
		lblCodigo.setForeground(Color.BLACK);
		lblCodigo.setFont(new Font("Masque", Font.PLAIN, 12));
		lblCodigo.setBounds(14, 30, 83, 16);
		contentPane.add(lblCodigo);

		JButton btnsalvar = new JButton("salvar");
		btnsalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btnsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvar.setFont(new Font("Masque", Font.BOLD, 10));
		btnsalvar.setBounds(214, 83, 102, 29);
		contentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				salvar();
			

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon("icon\\del.png"));
		btnExcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnExcluir.setFont(new Font("Masque", Font.BOLD, 10));
		btnExcluir.setBounds(317, 83, 105, 29);
		contentPane.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = (int)table.getValueAt(table.getSelectedRow(),0);
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um item para ser excluido");
				} else {
					CTEspecialidade.remove(row);
					modelo.removeRow(table.getSelectedRow());
				}

			}
		});

		table();
		setVisible(true);
	}

	public void table() {

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 124, 424, 196);
		contentPane.add(scrollPane);

		modelo.addColumn("ID"); // coluna 0
		modelo.addColumn("CBO"); // coluna 1
		modelo.addColumn("Nome");
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);

		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());
		// table.getColumnModel().getColumn(0).setCellRenderer(new
		// ColorTable().getCorLetra());
        CTEspecialidade.preencherLista();
		

	}

	public void addLinha(Especialidade novo) {
		modelo.addRow(new Object[] { novo.getId(), novo.getCbo(), novo.getNome() });
	}
	
	public static void preencherLista(Especialidade novo) {
		modelo.addRow(new Object[] { novo.getId(), novo.getCbo(), novo.getNome() });
	}

	public void salvar() {
		if(!txtcod.getText().equals("") && !txtnome.getText().equals("")) {
		novo = new Especialidade();
		novo.setId(CTEspecialidade.getId()+1);
		novo.setCbo(txtcod.getText());
		novo.setNome(txtnome.getText().toUpperCase());
		CTEspecialidade.insert(novo);
		addLinha(novo);
		txtcod.setText("");
		txtnome.setText("");
		txtcod.requestFocus();
		}
		else {
			JOptionPane.showMessageDialog(null, "Erro Campo em branco","Erro de campo",JOptionPane.ERROR_MESSAGE);
		}

	}
}
