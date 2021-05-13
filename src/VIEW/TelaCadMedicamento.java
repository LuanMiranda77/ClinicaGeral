package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import CONTROL.CTMedicamento;
import MODEL.Medicamento;
import VIEW.tabela.ModeloTabela;

public class TelaCadMedicamento extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5715354017234458468L;
	private JPanel contentPane;
	private JTextField txtcod;
	private JTextField txtnome;
	private JTextField txtvalor;
	private JTable table;
	private static DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private Medicamento novo;
	private int op=0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadMedicamento frame = new TelaCadMedicamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadMedicamento() {
		ModeloTabela.ativarModeloTela(this);
		setBackground(Color.DARK_GRAY);
		setTitle("Cadastro - Medicamento");
		setResizable(false);// seuJFrame
		setLocationRelativeTo(null);
		setType(java.awt.Window.Type.UTILITY);// nao minimizar
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 150, 487, 397);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(20, 20, 217, 22);
		contentPane.add(label);

		txtcod = new JTextField();
		int id =CTMedicamento.getId()+1;
		txtcod.setText(""+id);
		txtcod.setHorizontalAlignment(SwingConstants.CENTER);
		txtcod.setForeground(Color.GREEN);
		txtcod.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtcod.setEditable(false);
		txtcod.setColumns(10);
		txtcod.setBounds(20, 70, 74, 27);
		contentPane.add(txtcod);

		txtnome = new JTextField();
		txtnome.setForeground(Color.BLACK);
		txtnome.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtnome.setColumns(10);
		txtnome.setBounds(104, 70, 276, 27);
		contentPane.add(txtnome);
		txtnome.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F3) {
					String nome = JOptionPane.showInputDialog(null, "Digite para pesquisar");
					//limpartable();
					if(pesquisa(nome.toUpperCase())!=true) {
						JOptionPane.showMessageDialog(null, "Item não cadastrado!");
					}
				}

			}
		});

		txtvalor = new JTextFieldSoNumero();
		txtvalor.setForeground(Color.BLACK);
		txtvalor.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtvalor.setColumns(10);
		txtvalor.setBounds(390, 70, 74, 27);
		contentPane.add(txtvalor);
		txtvalor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtnome.getText().equals("") || txtvalor.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Erro campo em branco", "Erro cadastro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					salvar();
				}

			}
		});

		JButton btnsalvar = new JButton("salvar");
		btnsalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btnsalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnsalvar.setFont(new Font("Masque", Font.BOLD, 10));
		btnsalvar.setBounds(244, 108, 102, 30);
		contentPane.add(btnsalvar);
		btnsalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtnome.getText().equals("") || txtvalor.getText().equals("0,00")) {
					JOptionPane.showMessageDialog(null, "Erro campo em branco", "Erro cadastro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					salvar();
				}

			}
		});

		JButton btnexcluir = new JButton("Excluir");
		btnexcluir.setIcon(new ImageIcon("icon\\del.png"));
		btnexcluir.setHorizontalAlignment(SwingConstants.LEFT);
		btnexcluir.setFont(new Font("Masque", Font.BOLD, 10));
		btnexcluir.setBounds(356, 108, 108, 30);
		contentPane.add(btnexcluir);
		btnexcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row =table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um item para ser excluido");
				} else {
					op=1;
					//CTMedicamento.remove( (int) table.getValueAt(table.getSelectedRow(), 0));
					modelo.removeRow(row);
					
				}

			}
		});

		JLabel label_1 = new JLabel("Codigo:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 12));
		label_1.setBounds(23, 53, 83, 16);
		contentPane.add(label_1);

		JLabel lblNomeDoMedicamento = new JLabel("Nome do Medicamento:");
		lblNomeDoMedicamento.setForeground(Color.BLACK);
		lblNomeDoMedicamento.setFont(new Font("Masque", Font.PLAIN, 12));
		lblNomeDoMedicamento.setBounds(107, 53, 199, 16);
		contentPane.add(lblNomeDoMedicamento);

		JLabel lblMg = new JLabel("Mg:");
		lblMg.setForeground(Color.BLACK);
		lblMg.setFont(new Font("Masque", Font.PLAIN, 12));
		lblMg.setBounds(393, 53, 71, 16);
		contentPane.add(lblMg);
		table();
		JLabel lblTeclasDeAtlho = new JLabel("F3-pesquisa");
		lblTeclasDeAtlho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeclasDeAtlho.setForeground(new Color(0, 102, 51));
		lblTeclasDeAtlho.setFont(new Font("Maiandra GD", Font.BOLD, 10));
		lblTeclasDeAtlho.setBounds(23, 335, 403, 22);
		
		contentPane.add(lblTeclasDeAtlho);
		setVisible(true);
		txtnome.requestFocus();
	    updateTable();
		

	}

	public void table() {

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 149, 444, 185);
		contentPane.add(scrollPane);

		modelo.addColumn("COD"); // coluna 0
		modelo.addColumn("NOME"); // coluna 1
		modelo.addColumn("MG");
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_F3) {
					String nome = JOptionPane.showInputDialog(null, "Digite para pesquisar");
					//limpartable();
					if(pesquisa(nome.toUpperCase())!=true) {
						JOptionPane.showMessageDialog(null, "Item não cadastrado!");
					}
					
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		table.getColumnModel().getColumn(0).setCellRenderer(new VIEW.tabela.ColorTable().getCorFundo());
		// table.getColumnModel().getColumn(0).setCellRenderer(new
		// ColorTable().getCorLetra());

		CTMedicamento.preencherLista();
	}

	public static void addLinha(Medicamento novo) {
		modelo.addRow(new Object[] { novo.getId(), novo.getDesc(),novo.getmg()});
	}

	public void salvar() {
		novo = new Medicamento();
		novo.setId(Integer.parseInt(txtcod.getText()));
		novo.setDesc(txtnome.getText().toUpperCase());
		float v = Float.parseFloat(txtvalor.getText().replaceAll(",", "."));
		novo.setmg(v);
		CTMedicamento.insert(novo);
		addLinha(novo);
		int id = Integer.parseInt(txtcod.getText())+1;
		txtcod.setText(""+id);
		txtnome.setText("");
		txtvalor.setText("");
		txtnome.requestFocus();

	}

	public boolean pesquisa(String nome) {
		boolean band=false;
		   String d=nome.toUpperCase();
		    for(int i=0;i<table.getRowCount();i++) {
		    	String n = (String) table.getValueAt(i, 1);
		    if(n.contains(d)) {
		    	band=true;
		    	table.setRowSelectionInterval(i, i);
		    	table.requestFocus();
		    }
		    }
			return band;
	}

	public void updateTable() {
		modelo.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(txtnome.getText().equals("") && txtvalor.getText().equals("") && op==0) {
				int id = Integer.parseInt(""+table.getValueAt(table.getSelectedRow(),0));
				novo = new Medicamento();
				novo.setId(id);
				String des = "" + table.getValueAt(table.getSelectedRow(), 1);
				novo.setDesc(des.toUpperCase());
				String v = ""+table.getValueAt(table.getSelectedRow(), 2);
				novo.setmg(Float.parseFloat(v));
				CTMedicamento.update(novo);
				dispose();
				new TelaCadMedicamento();
			}
			}
		});
	}
	public void limpartable() {
		while(table.getModel().getRowCount()>0) {
					modelo.removeRow(0);
				
		}

	}

}
