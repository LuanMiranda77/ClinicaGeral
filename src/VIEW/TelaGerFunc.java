package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import CONTROL.CTFuncionario;
import MODEL.Funcionario;
import VIEW.tabela.ColorTable;
import VIEW.tabela.ModeloTabela;

public class TelaGerFunc extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BotoesGeral apagar;
	private BotoesGeral alterar;
	private BotoesGeral pesq;
	//private BotoesGeral relator;
	private static DefaultTableModel modelo;
	private JTable table;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new TelaGerFunc();

	}

	/**
	 * Create the frame.
	 */
	public TelaGerFunc() {
		setTitle("Gerenciamento de funcionario");
		getContentPane().setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		setForeground(Color.GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);// seuJFrame
		setBounds(100, 100, 750, 350);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		botton();
		ModeloTabela.ativarModeloTela(this);
		bcl();
		table();
		
		// blackgroud();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				ModeloTabela.destivar(table);

			}

		});

		setVisible(true);
		repaint();

	}

	public void botton() {
		BotoesGeral novo = new BotoesGeral("NOVO", new ImageIcon("Icon/novo.png"), 8, -5, 100, 100);
		novo.setLocation(11, 0);
		novo.setToolTipText("cadastrar novo produto");
		novo.setFont(new Font("Century Gothic", Font.BOLD, 13));
		novo.setForeground(Color.WHITE);
		novo.setTamanho(15);
		getContentPane().add(novo);
		novo.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				novo.setIcon(new ImageIcon("Icon/novo.png"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				novo.setIcon(new ImageIcon("Icon/novo2.png"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaCadFunc(0,"TelaGerFunc");

			}
		});

		apagar = new BotoesGeral("EXCLUIR", new ImageIcon("Icon/apagar.png"), 120, -5, 100, 100);
		apagar.setLocation(118, -5);
		apagar.setToolTipText("excluir o produto");
		apagar.setFont(new Font("Century Gothic", Font.BOLD, 13));
		apagar.setForeground(Color.WHITE);
		apagar.setTamanho(15);
		getContentPane().add(apagar);
		apagar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				apagar.setIcon(new ImageIcon("Icon/apagar.png"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				apagar.setIcon(new ImageIcon("Icon/apagar2.png"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um item para ser excluido");
				} else {
					int op=JOptionPane.showConfirmDialog(null, "Deseja realmente Excluir?","",JOptionPane.YES_NO_OPTION);
					if(op==0) {
					CTFuncionario.remove((int) table.getValueAt(table.getSelectedRow(),0));
					modelo.removeRow(table.getSelectedRow());
					}
				}

			}
		});

		alterar = new BotoesGeral("NOVO", new ImageIcon("Icon/editar.png"), 8, -5, 100, 100);
		alterar.setLocation(338, -5);
		alterar.setText("ALTERAR");
		alterar.setToolTipText("cadastrar novo produto");
		alterar.setFont(new Font("Century Gothic", Font.BOLD, 13));
		alterar.setForeground(Color.WHITE);
		alterar.setTamanho(15);
		alterar.repaint();
		getContentPane().add(alterar);
		alterar.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				alterar.setIcon(new ImageIcon("Icon/editar.png"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				alterar.setIcon(new ImageIcon("Icon/editar2.png"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null, "Selecione um item a ser alterado");
				}
				else {
				dispose();	
				new TelaCadFunc((int) table.getValueAt(row, 0),"TelaGerFunc");
				}

			}
		});

		pesq = new BotoesGeral("<html>PESQUISAR<html>", new ImageIcon("Icon/pesquisa.png"), 8, -5, 100, 100);
		pesq.setLocation(228, -5);
		pesq.setToolTipText("pesquisar o produto");
		pesq.setFont(new Font("Century Gothic", Font.BOLD, 13));
		pesq.setForeground(Color.WHITE);
		pesq.setTamanho(15);
		pesq.repaint();
		getContentPane().add(pesq);
		pesq.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				pesq.setIcon(new ImageIcon("Icon/pesquisa.png"));

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pesq.setIcon(new ImageIcon("Icon/pesquisa2.png"));

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = JOptionPane.showInputDialog(null, "Digite o nome Paciente?");
				boolean band = false;
				for(int i=0;i<table.getRowCount();i++) {
					String nome2=(String)table.getValueAt(i, 1);
					if(nome2.contains(nome.toUpperCase())) {
						band= true;
						table.setRowSelectionInterval(i, i);
						table.requestFocus();
					}
				}
				if(band==false) {
					JOptionPane.showMessageDialog(null, "Funcionario não cadastrado");
				}

			}
		});

		/*
		 * relator = new BotoesGeral("", new ImageIcon("Icon/impressora.png"), 8, -5,
		 * 100, 100); relator.setText("<html>RELATORIO<html>"); relator.setLocation(458,
		 * -5); relator.setToolTipText("cadastrar novo produto"); relator.setFont(new
		 * Font("Century Gothic", Font.BOLD, 13)); relator.setForeground(Color.WHITE);
		 * relator.setTamanho(15); relator.repaint(); getContentPane().add(relator);
		 * relator.addMouseListener(new MouseListener() {
		 * 
		 * @Override public void mouseReleased(MouseEvent e) {
		 * 
		 * }
		 * 
		 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void mouseExited(MouseEvent e) { relator.setIcon(new
		 * ImageIcon("Icon/impressora.png"));
		 * 
		 * }
		 * 
		 * @Override public void mouseEntered(MouseEvent e) { relator.setIcon(new
		 * ImageIcon("Icon/impressora2.png"));
		 * 
		 * }
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { // TODO Auto-generated
		 * method stub
		 * 
		 * } });
		 */
	}

	// barra da tela
	public void bcl() {
		JLabel barra = new JLabel();
		barra.setBounds(1, -20, 1200, 110);
		barra.setBackground(Color.DARK_GRAY);
		barra.setOpaque(isBackgroundSet());
		getContentPane().add(barra);
	}

	// fundo da tela
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("icon/produtoFundo.jpg"));
		contabil.setBounds(1, 1, 1200, 600);
		getContentPane().add(contabil);

	}

	// butoes
	@SuppressWarnings("serial")
	public void table() {
		modelo = new DefaultTableModel() {
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		table = new JTable(modelo);
		ModeloTabela.modelogeral(table);

		table.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 110, 700, 200);

		modelo.addColumn("COD"); // coluna 0
		modelo.addColumn("Nome"); // coluna 1
		modelo.addColumn("Celular");
		modelo.addColumn("Cargo");
		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);

		table.getColumnModel().getColumn(0).setCellRenderer(new ColorTable().getCorFundo());
		// table.getColumnModel().getColumn(0).setCellRenderer(new
		// ColorTable().getCorLetra());
        CTFuncionario.preencherLista();
		add(scrollPane);

	}

	public static void addLinha(Funcionario novo) {
		modelo.addRow(new Object[] {novo.getId(), novo.getNome(),novo.getCelular1() ,novo.getCargo()
			,novo.getSalario()  });
	}

}
