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

import CONTROL.CTPaciente;
import MODEL.Paciente;
import VIEW.tabela.ModeloTabela;
import VIEW.tabela.TabelaCliente;



public class TelaGerPaciente extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TabelaCliente tabela= new TabelaCliente();;
	private BotoesGeral apagar;
	private BotoesGeral alterar;
	private BotoesGeral pesq;
	//private BotoesGeral relator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					new TelaGerPaciente();
				
	}

	/**
	 * Create the frame.
	 */
	public TelaGerPaciente() {
		setTitle("Ficha de cliente");
		getContentPane().setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		setForeground(Color.GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);//seuJFrame
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		botton();
		bcl();
		table(this);
	//	blackgroud();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				//ModeloTabela.destivar(tabela);
			    
			    } 
			    
			   
			});
	

		
		setVisible(true);
		repaint();
		
	}
	public void botton() {
		BotoesGeral novo = new BotoesGeral("NOVO",new ImageIcon("Icon/novo.png"),8,-5,100,100);
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
				new TelaCadPaciente(0);
				
				
			}
		});
		
		apagar = new BotoesGeral("EXCLUIR",new ImageIcon("Icon/apagar.png"),120,-5,100,100);
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
				int row = tabela.selecionarLinha();
				
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um item para ser excluido");
				} else {
					int op = JOptionPane.showConfirmDialog(null,
							"Desejá realmente apagar ?",
							"Tela de confirmação", JOptionPane.YES_NO_OPTION);
					if(op==0) {
					CTPaciente.remove((int)tabela.retornarId());
					tabela.limparTabela(row);
					}
				}


				
			}
		});

		alterar = new BotoesGeral("NOVO",new ImageIcon("Icon/editar.png"),8,-5,100,100);
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
				int linha = tabela.selecionarLinha();
				if(linha<0) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha para ser alterada!","Erro de seleção de linha",JOptionPane.ERROR_MESSAGE);
				}
				else {
				dispose();
				new TelaCadPaciente(tabela.retornarId());
				}
				
			}
		});
		
		pesq = new BotoesGeral("<html>PESQUISAR<html>",new ImageIcon("Icon/pesquisa.png"),8,-5,100,100);
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
				if(tabela.selecionarLinha(nome.toUpperCase())!=true){
					JOptionPane.showMessageDialog(null, "Paciente nao cadastrado","Erro de cadastro",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		/*
		 * relator = new BotoesGeral("",new
		 * ImageIcon("Icon/impressora.png"),8,-5,100,100);
		 * relator.setText("<html>RELATORIO<html>"); relator.setLocation(458, -5);
		 * relator.setToolTipText("cadastrar novo produto"); relator.setFont(new
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

	//barra da tela 
	public void bcl() {
		JLabel barra = new JLabel();
		barra.setBounds(1,-20,1200,110);
		barra.setBackground(Color.DARK_GRAY);
		barra.setOpaque(isBackgroundSet());
		getContentPane().add(barra);
	}
	
	//fundo da tela
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("icon/produtoFundo.jpg"));
		contabil.setBounds(1,1,1200,600);
		getContentPane().add(contabil);
		
	}
	
	//butoes 
    public void table(JFrame janela) {
    	
    	ModeloTabela.modelogeral(tabela);
		tabela.gerarTabela(janela);
		tabela.repaint();
		janela.getContentPane().add(tabela);
		
		
	}
    @SuppressWarnings("static-access")
	public static void addTable(Paciente novo) {
    	tabela.adcionarLinha(novo);
    }
}
