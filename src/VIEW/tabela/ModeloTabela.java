package VIEW.tabela;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ModeloTabela {
	private static String temaAtivado="Nimbus";
	private static String temaDesativado="Metal";
	
	
	public static void modelogeral(JTable tabela) {
    try {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (temaAtivado.equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
				
			}
		}
	} catch (ClassNotFoundException ex) {
		ex.printStackTrace();
	} catch (InstantiationException ex) {
		ex.printStackTrace();
	} catch (IllegalAccessException ex) {
		ex.printStackTrace();
				
	} catch (UnsupportedLookAndFeelException ex) {
		ex.printStackTrace();
	}
	
	java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
			tabela.getClass();
		}
	});
	}
	public static void destivar(JTable tabela) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (temaDesativado.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
					
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
					
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				tabela.getClass();
			}
		});
		
		
		
		
	}
	public static void destivar(JFrame tela) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if (temaDesativado.equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
					
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
					
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				tela.getClass();
			}
		});
	}
	public static void ativarModeloTela(JFrame tela) {
	    try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if (temaAtivado.equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
					
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
					
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				tela.getClass();
			}
		});
		}
	public static void destivar(WindowAdapter tela) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if (temaDesativado.equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
					
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
					
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				tela.getClass();
			}
		});
		
	}
	

}
