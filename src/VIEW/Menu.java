package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import CONTROL.CTAgendaConsulta;
import CONTROL.CTConfig;
import CONTROL.CTEmitente;
import CONTROL.CTProfissional;
import CONTROL.ControlCentral;
import DAO.ContatosTxt;
import DAO.MensagensTxt;
import MODEL.Funcionario;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;


public class Menu extends Principal {
	
 

	private JMenuItem item,item1,item2,item3,item4,item5,item6;
	private BotoesGeral btncadastro, btnconfg, btnrelatorio, btnproced,btnhistorico,btnagenda,btnConsulta,btnDia, precad;
	private int l,a;
	private BufferedImage imagem = null;
	@SuppressWarnings("unused")
	private JLabel emitente;

	private static final long serialVersionUID = 1L;
	
	public Menu(Funcionario func) {
		getContentPane().setForeground(new Color(0, 0, 0));

		pegarResolucao();
	   setTitle("MENU PRINCIPAL");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   getContentPane().setLayout(null);
	   setLocationRelativeTo(null);
	   setResizable(false);
	   this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				CTAgendaConsulta c = new CTAgendaConsulta();
				c.testDataListaOrdem();
				MensagensTxt ms = new MensagensTxt();
				ContatosTxt ct = new ContatosTxt();
				ms.limparArquivo();
				ct.limparArquivo();
			    } 
			    
			   
			});
	   addMenuBar();
	   adicionarlogo(this,func);
	   bcl();
	   blackgroud(l,a-205,func);
	   testCargo(func.getCargo());
	   ModeloTabela.destivar(this);
	   setVisible(true);
	   ContatosTxt ct;
	   if(func.getCargo().equals("SECRETARIA") || func.getCargo().equals("ATENDENTE")) {
		   ct= new ContatosTxt();
		   ct.TestAgendaMarcDia();
		   
	   }
	   repaint();
	   ct=null;
	
	}
	public void bcl() {
		
		
		
	}
	
	public void blackgroud(int lag,int alt, Funcionario func) {
		CortaImagem corte = new CortaImagem();
		File arquivo =new File("icon/fundo.jpg");// arquivo
		
		try {
			imagem = ImageIO.read(arquivo); // carrega a imagem real num buffer
			
			
			JLabel user=  new JLabel("Usuario Logado: "+func.getNome());
			user.setBounds(10, alt+50, 459, 38);
			getContentPane().add(user);
			user.setFont(new Font("Masque",Font.BOLD,20));
			user.setForeground(Color.BLACK);
			
			
			JLabel fantasia=  new JLabel(CTEmitente.getEmitente().getNomeFatasia());
			fantasia.setBounds(34, 166, 495, 32);
			getContentPane().add(fantasia);
			fantasia.setFont(new Font("Masque", Font.BOLD, 25));
			fantasia.setForeground(new Color(0, 102, 51));
			fantasia.setVisible(true);
			
			JLabel razao=  new JLabel(CTEmitente.getEmitente().getRazao());
			razao.setBounds(39, 141, 419, 20);
			razao.setFont(new Font("Century Gothic", Font.BOLD, 12));
			razao.setForeground(Color.BLACK);
			getContentPane().add(razao);
			
			
			JLabel user1=  new JLabel("Cargo: "+func.getCargo());
			user1.setBounds(12, alt+70, 418, 32);
			getContentPane().add(user1);
			user1.setFont(new Font("Century Gothic", Font.BOLD, 20));
			user1.setForeground(Color.BLACK);
			
			
			
			JLabel fundo = new JLabel();
			fundo.setBounds(0, 131, lag, alt-5);
			getContentPane().add(fundo);
			fundo.setForeground(Color.WHITE);
			fundo.setOpaque(true);
			fundo.setIcon(new ImageIcon(corte.setCorteFundo(imagem,lag,alt-5)));
			
			JPanel panelBattom = new JPanel();
			panelBattom.setBackground(Color.DARK_GRAY);
			panelBattom.setBounds(1, alt+125, lag-5, 21);
			getContentPane().add(panelBattom);
			
			JLabel lblNewLabel = new JLabel("Vers\u00E3o: "+CTConfig.getConfig().getVersao());
			lblNewLabel.setForeground(Color.ORANGE);
			lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
			
			JLabel lblCopyright = new JLabel("\u00A9 COPYRIGHT 2020, GRA\u00C7ASOFT  BRASIL S.A.");
			lblCopyright.setForeground(Color.GRAY);
			lblCopyright.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
			
			JLabel lblSuporte = new JLabel("Tipo: "+CTConfig.getConfig().getTipoInstalacao());
			lblSuporte.setForeground(Color.GRAY);
			lblSuporte.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
			
			JLabel sep = new JLabel("");
			sep.setBackground(Color.LIGHT_GRAY);
			sep.setForeground(Color.BLACK);
			sep.setFont(new Font("Consolas", Font.BOLD, 14));
			sep.setOpaque(true);
			
			JLabel label = new JLabel("");
			label.setOpaque(true);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Consolas", Font.BOLD, 14));
			label.setBackground(Color.LIGHT_GRAY);
			
			JLabel label_1 = new JLabel("Suporte:(83)9.9638-6694");
			label_1.setForeground(Color.LIGHT_GRAY);
			label_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
			
			JLabel label_2 = new JLabel("");
			label_2.setOpaque(true);
			label_2.setForeground(Color.BLACK);
			label_2.setFont(new Font("Consolas", Font.BOLD, 14));
			label_2.setBackground(Color.LIGHT_GRAY);
			GroupLayout gl_panelBattom = new GroupLayout(panelBattom);
			gl_panelBattom.setHorizontalGroup(
				gl_panelBattom.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelBattom.createSequentialGroup()
						.addGap(420)
						.addGroup(gl_panelBattom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelBattom.createSequentialGroup()
								.addGap(195)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE))
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
						.addGap(13)
						.addComponent(lblSuporte, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(sep, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_panelBattom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelBattom.createSequentialGroup()
								.addGap(329)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblCopyright, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addGap(32))
			);
			gl_panelBattom.setVerticalGroup(
				gl_panelBattom.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelBattom.createSequentialGroup()
						.addGap(4)
						.addGroup(gl_panelBattom.createParallelGroup(Alignment.LEADING)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1)))
					.addGroup(gl_panelBattom.createSequentialGroup()
						.addGap(4)
						.addComponent(lblSuporte))
					.addGroup(gl_panelBattom.createSequentialGroup()
						.addGap(4)
						.addComponent(sep, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelBattom.createSequentialGroup()
						.addGap(2)
						.addGroup(gl_panelBattom.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelBattom.createSequentialGroup()
								.addGap(2)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelBattom.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCopyright)
								.addComponent(lblNewLabel))))
			);
			panelBattom.setLayout(gl_panelBattom);
			
		} catch (IOException ex) {
			
		}
		
		
		
	}
	public void addMenuBar() {
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		JMenu menu = new JMenu("Operacional");
		barraMenu.add(menu);
		
		JMenu cad = new JMenu("Cadastro");
		barraMenu.add(cad);
		
		JMenu rel = new JMenu("Relatorio");
		barraMenu.add(rel);
		
		item = new JMenuItem("Fazer Backup");
		menu.add(item);
		item.setVisible(false);
	
		
		item5 = new JMenuItem("Consultar Agenda");
		menu.add(item5);
		item5.setVisible(false);
		
		 item1 = new JMenuItem("Cadastar Paciente");
		cad.add(item1);
		item1.setVisible(false);
		
		item2 = new JMenuItem("Cadastar Profissional");
		cad.add(item2);
		item2.setVisible(false);
		
		item3 = new JMenuItem("Cadastar Funcionario");
		cad.add(item3);
		item3.setVisible(false);
		
		item4 = new JMenuItem("Cadastar Convenio");
		cad.add(item4);
		item4.setVisible(false);
		
		item6 = new JMenuItem("");
		rel.add(item6);
		item6.setVisible(false);
		
		
		ouvinteLinter ouvi = new ouvinteLinter();
		item.addActionListener(ouvi);
		item1.addActionListener(ouvi);
		item2.addActionListener(ouvi);
		item3.addActionListener(ouvi);
		item4.addActionListener(ouvi);
		item5.addActionListener(ouvi);
		item6.addActionListener(ouvi);
		
	}
	public class ouvinteLinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		 String op = e.getActionCommand();
		 
		 if(op.equals("Fazer Backup")) {
			try {
				ControlCentral.geraBackup();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		 }
		 else if(op.equals("Consultar Agenda")) {
			new TelaGerAgenda();
			
		}
		 else if(op.equals("Cadastar Paciente")) {
			 new TelaCadPaciente(0);
		}
		 
		 else if(op.equals("Cadastar Funcionario")) {
			 new TelaCadFunc(0,"");
		}
		 else if(op.equals("Cadastar Profisional")) {
			new TelaCadProf("");
			}
		 else if(op.equals("Consultar Convenio")) {
			 new TelaCadConvenio();
		}
		 else if(op.equals("Dividas de Clientes")) {
			
		}
		
	}
	}
	public void adicionarlogo(JFrame frame,Funcionario func) {
		
		
		 Date d = new Date();
	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
	  //  Timer timer = new Timer(1000, new hora());
	    //timer.start();
	    
		JLabel data=  new JLabel("Data: "+hoje);
		data.setBounds(1100,580,400,100);
		data.setFont(new Font("Magneto",Font.BOLD,25));
		data.setForeground(Color.WHITE);
		
		JPanel panelBarra = new JPanel();
		panelBarra.setBackground(Color.DARK_GRAY);
		panelBarra.setBounds(1, 1, 1364, 129);
		getContentPane().add(panelBarra);
		
				
				BotoesGeral btnsair = new BotoesGeral("<html>Sair<html>",new ImageIcon("Icon/sair.png"),730,20,100,100);
				btnsair.setForeground(Color.WHITE);
				
				btnconfg = new BotoesGeral("<html>Config<html>",new ImageIcon("Icon/config.png"),625,20,100,100);
				btnconfg.setTamanho(10);
				btnconfg.setToolTipText("configura\u00E7\u00E3o");
				btnconfg.setForeground(Color.WHITE);
				btnconfg.setVisible(false);
				
				
			    btnrelatorio = new BotoesGeral("<html>Relatorio<html>",new ImageIcon("Icon/rela2.png"),515,20,100,100);
				btnrelatorio.setToolTipText("relatorio geral");
				btnrelatorio.setForeground(Color.WHITE);
				btnrelatorio.setVisible(false);
				
				
				btncadastro = new BotoesGeral("Cadastro",new ImageIcon("Icon/cad.png"), 415,20,100,100);
				btncadastro.setToolTipText("cadastro geral");
				btncadastro.setText("Cadastro");
				btncadastro.setForeground(Color.WHITE);
				btncadastro.setVisible(false);
				
				btnproced = new BotoesGeral("<html>Estoque<html>",new ImageIcon("Icon/proced.png"),313,20,100,100);
				btnproced.setTamanho(10);
				btnproced.setText("<html>Procedimento<html>");
				btnproced.setToolTipText("Criar procedimento");
				btnproced.setForeground(Color.WHITE);
				btnproced.setVisible(false);
				btnproced.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnproced.setIcon(new ImageIcon("Icon/proced.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnproced.setIcon(new ImageIcon("Icon/proced2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						new TelaCadProcedimento();
						
					}
				});
				
				BotoesGeral email = new BotoesGeral("<html>Estoque<html>", new ImageIcon("Icon/xml.png"), 313, 20, 100, 100);
				email.setSelectedIcon(new ImageIcon("icon\\gmail2.png"));
				email.setIcon(new ImageIcon("icon\\gmail1.png"));
				email.setVisible(true);
				email.setText("Email");
				email.setToolTipText("Enviar Email");
				email.setForeground(Color.WHITE);
				
				
				precad = new BotoesGeral("",new ImageIcon("Icon/cliente.png"), 202,20,100,100);
				precad.setText("<html>Pre-Paciente<html>");
				precad.setForeground(Color.WHITE);
				precad.setToolTipText("Pre-Cadastro de Cliente");
				precad.setVisible(false);
				precad.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						precad.setIcon(new ImageIcon("Icon/cliente.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						precad.setIcon(new ImageIcon("Icon/cliente2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						new TelaPrePaciente();
						
						
					}
				});
				
				btnhistorico = new BotoesGeral("<html>NF-e<html>",new ImageIcon("Icon/h.png"),101,20,100,100);
				btnhistorico.setText("Historico");
				btnhistorico.setFont(new Font("Century Gothic", Font.BOLD, 13));
				btnhistorico.setForeground(Color.WHITE);
				btnhistorico.setToolTipText("Historico");
				btnhistorico.setVisible(false);
				
				// botao menu
				
				btnagenda = new BotoesGeral("<html>PDV-NFC-e<html>",new ImageIcon("Icon/agenda.png"),2,20,100,100);
				btnagenda.setToolTipText("Cadastro de Agenda");
				btnagenda.setText("Agenda");
				btnagenda.setFont(new Font("Century Gothic", Font.BOLD, 13));
				btnagenda.setForeground(Color.WHITE);
				btnagenda.setTamanho(15);
				btnagenda.setVisible(false);
				
				btnConsulta = new BotoesGeral("<html>PDV-NFC-e<html>", (ImageIcon) null, 2, 20, 100, 100);
				btnConsulta.setIcon(new ImageIcon("icon\\consulta.png"));
				btnConsulta.setToolTipText("Cadastrar consulta");
				btnConsulta.setText("Consulta");
				btnConsulta.setTamanho(15);
				btnConsulta.setForeground(Color.WHITE);
				btnConsulta.setFont(new Font("Century Gothic", Font.BOLD, 13));
				btnConsulta.setVisible(false);
				btnConsulta.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnConsulta.setIcon(new ImageIcon("Icon/consulta.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnConsulta.setIcon(new ImageIcon("Icon/consulta2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Profissional prof = CTProfissional.get(func.getNomeProf());
						new TelaConsulta(prof);
						
					}
				});
				
			
				btnDia= new BotoesGeral("<html>Agenda-Dia<html>",new ImageIcon("Icon/dia.png"),625,20,100,100);
				btnDia.setTamanho(10);
				btnDia.setToolTipText("configura\u00E7\u00E3o");
				btnDia.setForeground(Color.WHITE);
				btnDia.setVisible(false);
				btnDia.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnDia.setIcon(new ImageIcon("Icon/dia.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnDia.setIcon(new ImageIcon("Icon/dia2.png"));
						
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						
						new TelaEscolhaMed();
						
						
					}
				});
				
				BotoesGeral btnzap = new BotoesGeral("<html>Sair<html>", (ImageIcon) null, 730, 20, 100, 100);
				btnzap.setIcon(new ImageIcon("icon\\zap.PNG"));
				btnzap.setSelectedIcon(new ImageIcon("icon\\zap2.png"));
				btnzap.setToolTipText("<html>enviar mensagem pelo zap<html>");
				btnzap.setText("<html>Whatsapp<html>");
				btnzap.setForeground(Color.WHITE);
                btnzap.addMouseListener(new MouseListener() {				
					@Override
					public void mouseReleased(MouseEvent e) {				
					}
					
					@Override
					public void mousePressed(MouseEvent e) {					
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						  btnzap.setIcon(new ImageIcon("icon\\zap.PNG"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						  btnzap.setIcon(new ImageIcon("icon\\zap2.png"));				
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
							//Runtime.getRuntime().exec("C:\\System Clinica\\execZap.bat");
						new TelaMensagemZap();
							
						
					}
				});
				GroupLayout gl_panelBarra = new GroupLayout(panelBarra);
				gl_panelBarra.setHorizontalGroup(
					gl_panelBarra.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBarra.createSequentialGroup()
							.addGap(10)
							.addComponent(btnConsulta, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnDia, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnagenda, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnhistorico, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(precad, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnproced, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btncadastro, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(email, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnzap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnrelatorio, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnconfg, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(92))
				);
				gl_panelBarra.setVerticalGroup(
					gl_panelBarra.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBarra.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_panelBarra.createParallelGroup(Alignment.LEADING)
								.addComponent(btnConsulta, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDia, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnagenda, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnhistorico, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelBarra.createParallelGroup(Alignment.BASELINE)
									.addComponent(precad, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnproced, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btncadastro, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(email, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnzap, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnrelatorio, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnconfg, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnsair, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))))
				);
				panelBarra.setLayout(gl_panelBarra);
				btnagenda.repaint();
				btnagenda.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnagenda.setIcon(new ImageIcon("Icon/agenda.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnagenda.setIcon(new ImageIcon("Icon/agenda_2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						new TelaGerAgenda();
						
					}
				});
				btnhistorico.repaint();
				btnhistorico.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnhistorico.setIcon(new ImageIcon("Icon/h.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnhistorico.setIcon(new ImageIcon("Icon/h2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
					    new TelaHistorico();   
						
					}
				});
				
				email.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						email.setIcon(new ImageIcon("Icon/gmail1.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						email.setIcon(new ImageIcon("Icon/gmail2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if(CTConfig.getConfig().getEmail().equals("")) {
							JOptionPane.showMessageDialog(null, "Email não configurado!", "Erro de configuração",JOptionPane.ERROR_MESSAGE);
						}else {
						new TelaEmailEnvio();
						}
						
					}
				});
				btncadastro.repaint();
				btncadastro.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btncadastro.setIcon(new ImageIcon("Icon/cad.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btncadastro.setIcon(new ImageIcon("Icon/cad2.png"));
						
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						new GerenciaCad(func);
						
						
					}
				});
				btnrelatorio.repaint();
				btnrelatorio.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnrelatorio.setIcon(new ImageIcon("Icon/rela2.png"));
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					btnrelatorio.setIcon(new ImageIcon("Icon/rela.png"));
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					new TelaGeraRelatorio();
					
				}
		});
				btnconfg.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnconfg.setIcon(new ImageIcon("Icon/config.png"));
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					btnconfg.setIcon(new ImageIcon("Icon/config2.png"));
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					String [] lista = {"Config Emitente","Config Geral"};
					String op =(String)JOptionPane.showInputDialog(null,"Escolha a tela de configuração","Tela de escolha",JOptionPane.QUESTION_MESSAGE,null, lista,lista[0]);
					try {
					switch (op) {
					case "Config Emitente":
						new TelaConfig();
						break;

					default:
						new TelaConfigGeral(0);
						break;
					}
					}catch(NullPointerException ei){
						ei.printStackTrace();
					}
				}
				
		});
				btnsair.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnsair.setIcon(new ImageIcon("Icon/sair.png"));
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						btnsair.setIcon(new ImageIcon("Icon/sair2.png"));
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						new TelaLogin();
						
					}
				});
		
		
		
	}
	
	public void pegarResolucao() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dimensao = t.getScreenSize();
         l=(dimensao.width +5);
         a=(dimensao.height - 30);
         this.setSize(l,a );

 }
	public void menuSup() {
		
	}
	public static void main(String[] args) {
		Funcionario gerente = new Funcionario();
		 gerente.setId(1);
		 gerente.setNome("lmb");
		 gerente.setCargo("Administrador");
		 gerente.setSenha("1");
		new Menu(gerente);
	}
	private void testCargo(String cargo) {
		
		if(cargo.equals("SECRETARIA")||cargo.equals("ATENDENTE")){
			btnagenda.setVisible(true);
			btnhistorico.setVisible(true);
			btncadastro.setVisible(true);
			btnrelatorio.setVisible(true);
			btnDia.setVisible(true);
			item5.setVisible(true);
			item1.setVisible(true);
			item.setVisible(true);
			precad.setVisible(true);
			
		    
		}
		else if(cargo.equals("MEDICO")||cargo.equals("GERENTE")){
			btnagenda.setVisible(true);
			btnhistorico.setVisible(true);
			btncadastro.setVisible(true);
			btnrelatorio.setVisible(true);
			btnConsulta.setVisible(true);
			btnproced.setVisible(true);
			btnconfg.setVisible(true);
			item.setVisible(true);
			item5.setVisible(true);
			item1.setVisible(true);
			item2.setVisible(true);
			item3.setVisible(true);
			item4.setVisible(true);
			item6.setVisible(true);
		}
		else {
			btnconfg.setVisible(true);
		}
	
			
		
	}
}
