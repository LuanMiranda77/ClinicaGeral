package VIEW.tabela;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorTable {
	
	private DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
	// Alinhamento da coluna
	       public DefaultTableCellRenderer getAlinhamentoColumn(String tipo){
	    
	            cellRender.setHorizontalAlignment(getAlinhamentoColum(tipo));
	    
	                return cellRender;
	       }

	      private int getAlinhamentoColum(String alinhamento){
	    
	                switch(alinhamento){
	                    case "centro": return SwingConstants.CENTER;
	                    case "direita": return SwingConstants.RIGHT;
	                    default: return SwingConstants.LEFT;
	               }
	      }
	      
	      // Mudar a cor da coluna
	      public DefaultTableCellRenderer getCorFundo(){
	    
	           cellRender.setBackground(Color.gray);
	    
	           return cellRender;
	      }
	      public DefaultTableCellRenderer getCorLetra(){
	  	    
	           cellRender.setForeground(Color.ORANGE);
	    
	           return cellRender;
	      }
	    
	    

}
