package VIEW;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JTextArea;

import CONTROL.CTAgendaConsulta;
import CONTROL.CTConvenio;

import CONTROL.CTPaciente;
import CONTROL.CTProcedimento;
import CONTROL.CTProfissional;
import MODEL.AgendaConsulta;
import MODEL.Paciente;
import MODEL.Procedimento;
import MODEL.Profissional;
import VIEW.tabela.ModeloTabela;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TelaCadAgenda extends Principal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtid;
	private JLabel label_2;
	private JTextField txtData;
	private JLabel lblData;
	private JLabel lblHora;
	private JTextField txtHora;
	private JComboBox<String> cbxStatus;
	private JLabel lblSituao;
	private JTextField txtMedico;
	private JLabel lblPacientefPesquisar;
	private static JComboBox<String> cbxPaciente;
	private JComboBox<String> cbxProced;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JTextField txtValorDesc;
	private JLabel label_7;
	private JTextField txtValorConsulta;
	private JButton btnPes;
	private DecimalFormat convertMoeda = new DecimalFormat("#0.00");
	private float total;
	private JTextField txtConv;
	private AgendaConsulta consulta;
	private Procedimento proced;
    private JTextArea txtObs;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Paciente des;
    private Profissional medico;
    
    public static void main(String[] args) {
	
	}

	public TelaCadAgenda(AgendaConsulta c, int cod) {
		ModeloTabela.ativarModeloTela(this);
		setTitle("Cadastro - Agendamento de Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(450, 50, 502, 566);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 102, 51));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(10, 5, 5, 5, (Color) new Color(0, 102, 51)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		medico=CTProfissional.getMedico(c.getMatMedico());

		JLabel label = new JLabel("Informa\u00E7\u00F5es B\u00E1sicas");
		label.setBackground(new Color(0, 102, 51));
		label.setForeground(new Color(0, 102, 51));
		label.setFont(new Font("Masque", Font.PLAIN, 13));
		label.setBounds(10, 13, 186, 16);
		contentPane.add(label);

		txtid = new JTextField();
		
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(new Color(0, 204, 51));
		txtid.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(28, 52, 108, 32);
		contentPane.add(txtid);

		JLabel label_1 = new JLabel("N\u00BA Consulta:");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Masque", Font.PLAIN, 11));
		label_1.setBounds(29, 35, 125, 16);
		contentPane.add(label_1);

		label_2 = new JLabel("Medico Especialista:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Masque", Font.PLAIN, 11));
		label_2.setBounds(152, 35, 150, 16);
		contentPane.add(label_2);
		
		txtData = new JTextField(formato.format(c.getData()));
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setForeground(Color.BLUE);
		txtData.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(28, 107, 108, 32);
		contentPane.add(txtData);

		lblData = new JLabel("Data:");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Masque", Font.PLAIN, 11));
		lblData.setBounds(29, 90, 125, 16);
		contentPane.add(lblData);

		lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Masque", Font.PLAIN, 11));
		lblHora.setBounds(152, 90, 77, 16);
		contentPane.add(lblHora);

		txtHora = new JTextField(c.getHora());
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setForeground(Color.BLUE);
		txtHora.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtHora.setEditable(false);
		txtHora.setColumns(10);
		txtHora.setBounds(146, 107, 108, 32);
		contentPane.add(txtHora);

		cbxStatus = new JComboBox<String>();
		cbxStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"MARCADA", "RETORNO", "CANCELADA"}));
		cbxStatus.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxStatus.setBounds(259, 106, 200, 32);
		contentPane.add(cbxStatus);

		lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setForeground(Color.BLACK);
		lblSituao.setFont(new Font("Masque", Font.PLAIN, 11));
		lblSituao.setBounds(259, 88, 157, 16);
		contentPane.add(lblSituao);

		txtMedico = new JTextField(medico.getNome());
		txtMedico.setEditable(false);
		txtMedico.setHorizontalAlignment(SwingConstants.LEFT);
		txtMedico.setForeground(new Color(0, 153, 51));
		txtMedico.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtMedico.setColumns(10);
		txtMedico.setBounds(146, 52, 313, 32);
		contentPane.add(txtMedico);

		lblPacientefPesquisar = new JLabel("Paciente: (F3 Pesquisar)");
		lblPacientefPesquisar.setForeground(new Color(0, 0, 0));
		lblPacientefPesquisar.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		lblPacientefPesquisar.setBounds(31, 143, 299, 16);
		contentPane.add(lblPacientefPesquisar);

		cbxPaciente = new JComboBox<String>();
		cbxPaciente.setModel(new DefaultComboBoxModel<String>(CTPaciente.getListaNomes()));
		cbxPaciente.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxPaciente.setBounds(28, 161, 388, 32);
		contentPane.add(cbxPaciente);
		cbxPaciente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cbxPaciente.getSelectedItem().equals("-")) {
					des = CTPaciente.get("" + cbxPaciente.getSelectedItem());
					txtConv.setText(CTConvenio.getConvId(des.getId()).getNome());
				}
				
			}
		});
		cbxPaciente.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F3) {
					new TelaEsolhaPaciente();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		cbxProced = new JComboBox<String>();
		cbxProced.setModel(new DefaultComboBoxModel<String>(CTProfissional.getProced(c.getMatMedico())));
		System.out.println(c.getMatMedico());
		cbxProced.setFont(new Font("Masque", Font.PLAIN, 11));
		cbxProced.setBounds(30, 218, 429, 32);
		contentPane.add(cbxProced);
		cbxProced.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				proced = CTProcedimento.getProcedimento("" + cbxProced.getSelectedItem());
				if (!cbxProced.getSelectedItem().equals("-")) {
					txtValorConsulta.setText(convertMoeda.format(proced.getValor()));
				} else
					txtValorConsulta.setText("");

			}
		});

		label_4 = new JLabel("Procedimento");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("Masque", Font.PLAIN, 11));
		label_4.setBounds(32, 202, 148, 16);
		contentPane.add(label_4);

		label_5 = new JLabel("Convenio:");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("Masque", Font.PLAIN, 11));
		label_5.setBounds(31, 271, 165, 16);
		contentPane.add(label_5);

		label_6 = new JLabel("Desc.Convenio:");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Masque", Font.PLAIN, 11));
		label_6.setBounds(238, 271, 107, 16);
		contentPane.add(label_6);

		txtValorDesc = new JTextFieldReal(new DecimalFormat("0.00"));
		txtValorDesc.setHorizontalAlignment(SwingConstants.TRAILING);
		txtValorDesc.setForeground(new Color(51, 102, 255));
		txtValorDesc.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtValorDesc.setColumns(10);
		txtValorDesc.setBounds(237, 288, 107, 28);
		contentPane.add(txtValorDesc);
		txtValorDesc.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				float t = 0;
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String v = "" + txtValorConsulta.getText();
					t = Float.parseFloat(v.replaceAll(",", "."));
					total = t;
					String v1 = "" + txtValorDesc.getText();
					float desc = Float.parseFloat(v1.replaceAll(",", "."));
					float porc = (total * 0.40f);
					if (porc > desc) {
						total -= desc;
						txtValorConsulta.setText(convertMoeda.format(total));
						txtObs.requestFocus();
					
					}
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (txtValorDesc.getText().equals("0,00")) {
						txtValorConsulta.setText(""+t);
						cbxProced.setSelectedItem("-");
					}

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		label_7 = new JLabel("Valor Consulta:");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Masque", Font.PLAIN, 11));
		label_7.setBounds(351, 272, 125, 16);
		contentPane.add(label_7);

		txtValorConsulta = new JTextFieldReal(new DecimalFormat("0.00"));
		txtValorConsulta.setHorizontalAlignment(SwingConstants.TRAILING);
		txtValorConsulta.setForeground(new Color(204, 0, 0));
		txtValorConsulta.setFont(new Font("SansSerif", Font.BOLD, 15));
		txtValorConsulta.setEditable(false);
		txtValorConsulta.setColumns(10);
		txtValorConsulta.setBounds(350, 289, 119, 28);
		contentPane.add(txtValorConsulta);

		btnPes = new JButton("");
		btnPes.setIcon(new ImageIcon("icon\\lp.png"));
		btnPes.setBounds(427, 161, 32, 32);
		contentPane.add(btnPes);
		btnPes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			 new TelaEsolhaPaciente();
				
			}
		});

		txtObs = new JTextArea();
		txtObs.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtObs.setBounds(28, 365, 440, 106);
		contentPane.add(txtObs);

		JLabel lblObservaesSobreA = new JLabel("Observa\u00E7\u00F5es sobre a consulta:");
		lblObservaesSobreA.setForeground(new Color(0, 102, 51));
		lblObservaesSobreA.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		lblObservaesSobreA.setBounds(31, 346, 299, 16);
		contentPane.add(lblObservaesSobreA);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon("icon\\sal.png"));
		btnSalvar.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalvar.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		btnSalvar.setBounds(243, 483, 102, 32);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					salvar(cod);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("icon\\del.png"));
		btnCancelar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelar.setFont(new Font("Marcellus SC", Font.BOLD, 12));
		btnCancelar.setBounds(351, 483, 118, 32);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		txtConv = new JTextField("");
		txtConv.setBackground(new Color(204, 204, 153));
		txtConv.setHorizontalAlignment(SwingConstants.LEFT);
		txtConv.setForeground(new Color(0, 153, 51));
		txtConv.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		txtConv.setEditable(false);
		txtConv.setColumns(10);
		txtConv.setBounds(28, 289, 201, 28);
		contentPane.add(txtConv);
		
		if(cod==0) {
			txtid.setText(String.format("%010d", CTAgendaConsulta.getId()));
		}
		else {
			txtid.setText(String.format("%010d", c.getId()));
			consulta=c;
			preencherCampo(c);
		}
		
		setVisible(true);
		cbxPaciente.requestFocus();
	}

	
	public void salvar(int cod) throws ParseException {
		if (!cbxPaciente.getSelectedItem().equals("-") && !cbxProced.getSelectedItem().equals("-")) {
			if(cod==0) {
			consulta = new AgendaConsulta();
			}
			Date d1 = new Date();
			
			consulta.setMatMedico(CTProfissional.get(txtMedico.getText()).getMatricula());
			consulta.setHora(txtHora.getText());
			d1=formato.parse(txtData.getText());
			consulta.setData(d1);
			consulta.setIdPaciente(des.getId());
			
			consulta.setIdProce(""+proced.getId());
			consulta.setStatus("" + cbxStatus.getSelectedItem());
			float v = Float.parseFloat(txtValorConsulta.getText().replaceAll(",", "."));
			float d = Float.parseFloat(txtValorDesc.getText().replaceAll(",", "."));
			consulta.setDesc(d);
			consulta.setValor(v);
			consulta.setObs(txtObs.getText());
			if(cod==0) {
				CTAgendaConsulta.insert(consulta);
				JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
				dispose();
				new TelaGerAgenda();
			}
			else {
				CTAgendaConsulta.update(consulta);
				JOptionPane.showMessageDialog(null, "Consulta Alterada com sucesso!");
				dispose();
				new TelaConsultaAg(medico);
				
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Paciente ou Procedimento não informado na consulta", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}
	public void preencherCampo(AgendaConsulta c) {
		txtMedico.setText(medico.getNome());
		cbxPaciente.setSelectedItem(CTPaciente.get(c.getIdPaciente()).getNome());
		cbxProced.setSelectedItem(CTProcedimento.getProcedimento(Integer.parseInt(c.getIdProce())).getDesc());
		txtValorDesc.setText(convertMoeda.format(c.getDesc()));
		txtValorConsulta.setText(convertMoeda.format(c.getValor()));
		txtObs.setText(c.getObs());
		
		
	}
	public static void esolhaPaciente(String nome) {
		cbxPaciente.setSelectedItem(nome);
	}
}
