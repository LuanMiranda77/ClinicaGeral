package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import MODEL.Funcionario;

public class GerenciaCad extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3468535952328399683L;
	private BotoesGeral conv;
	private BotoesGeral usu;
	private BotoesGeral prof, paciente, btnremedio;

	public GerenciaCad(Funcionario func) {
		//ModeloTabela.destivar(this);
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("Gerenciamento de Cadastro");
		setResizable(false);//seuJFrame
		setType(java.awt.Window.Type.UTILITY);//nao minimizar
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		botton(func.getCargo());
		blackgroud();
		setVisible(true);
		repaint();
		
	
	}
	public void botton(String cargo) {
		paciente = new BotoesGeral("CLIENTE",new ImageIcon("Icon/cliente.png"),10,50,120,120);
		paciente.setText("PACIENTE");
		paciente.setToolTipText("cadastrar novo paciente");
		paciente.setFont(new Font("Century Gothic", Font.BOLD, 13));
		paciente.setForeground(Color.DARK_GRAY);
		paciente.setTamanho(15);
		paciente.setVisible(false);
		getContentPane().add(paciente);
		paciente.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				paciente.setIcon(new ImageIcon("Icon/cliente.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				paciente.setIcon(new ImageIcon("Icon/cliente2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaGerPaciente();
				dispose();
				
			}
		});
		
		
		btnremedio = new BotoesGeral("PREÇO",new ImageIcon("Icon/remedio.png"),10,150,120,120);
		btnremedio.setText("<html>MEDICAMENTO<html>");
		btnremedio.setLocation(174, 200);
		btnremedio.setToolTipText("Cadastro de medicamento");
		btnremedio.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnremedio.setForeground(Color.DARK_GRAY);
		btnremedio.setTamanho(15);
		getContentPane().add(btnremedio);
		btnremedio.setVisible(false);
		btnremedio.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnremedio.setIcon(new ImageIcon("Icon/remedio.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnremedio.setIcon(new ImageIcon("Icon/remedio2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaCadMedicamento();
				dispose();
				
			}
		});
		
		

		conv = new BotoesGeral("<html>FORNECEDOR<html>",new ImageIcon("Icon/fornec.png"),10,250,120,120);
		conv.setIcon(new ImageIcon("icon\\convenio.png"));
		conv.setText("<html>CONVENIO<html>");
		conv.setLocation(174, 50);
		conv.setToolTipText("cadastrar novo convenio");
		conv.setFont(new Font("Century Gothic", Font.BOLD, 13));
		conv.setForeground(Color.DARK_GRAY);
		conv.setTamanho(15);
		getContentPane().add(conv);
		conv.setVisible(false);
		conv.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				conv.setIcon(new ImageIcon("Icon/convenio.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				conv.setIcon(new ImageIcon("Icon/convenio2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaCadConvenio();
				dispose();
			}
		});
		

		usu = new BotoesGeral("<html>FUNCIONARIO<html>",new ImageIcon("Icon/func.png"),10,350,120,120);
		usu.setText("USUARIO");
		usu.setLocation(336, 51);
		usu.setToolTipText("cadastrar novo usuario do sistema");
		usu.setFont(new Font("Century Gothic", Font.BOLD, 13));
		usu.setForeground(Color.DARK_GRAY);
		usu.setTamanho(15);
		usu.setVisible(false);
		getContentPane().add(usu);
		usu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				usu.setIcon(new ImageIcon("Icon/func.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				usu.setIcon(new ImageIcon("Icon/func2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new TelaGerFunc();
				
			}
		});
		
		
		prof = new BotoesGeral("",new ImageIcon("Icon/etiqueta.png"),100,150,120,120);
		prof.setIcon(new ImageIcon("icon\\medico.png"));
		prof.setText("<html>PROFISSONAL<html>");
		prof.setLocation(10, 200);
		prof.setToolTipText("cadastrar novo profissional");
		prof.setFont(new Font("Century Gothic", Font.BOLD, 13));
		prof.setForeground(Color.DARK_GRAY);
		prof.setTamanho(15);
		prof.setVisible(false);
		getContentPane().add(prof);
		prof.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				prof.setIcon(new ImageIcon("Icon/medico.png"));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				prof.setIcon(new ImageIcon("Icon/medico2.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaGerProf();
				dispose();
				
			}
		});
		testCargo(cargo);
	}
	
	public void blackgroud() {
		JLabel contabil = new JLabel(new ImageIcon("icon/fundoCad.jpg"));
		contabil.setBounds(1,1,500,400);
		getContentPane().add(contabil);
		
		
		
	}
private void testCargo(String cargo) {
		
		if(cargo.equals("SECRETARIA")||cargo.equals("ATENDENTE")){
			paciente.setVisible(true);
		    
		}
		else if(cargo.equals("MEDICO")||cargo.equals("GERENTE")){
			paciente.setVisible(true);
			prof.setVisible(true);
			conv.setVisible(true);
			usu.setVisible(true);
			btnremedio.setVisible(true);
			
		}
			
		
	}
}
