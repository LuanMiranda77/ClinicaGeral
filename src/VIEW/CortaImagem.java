package VIEW;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CortaImagem {
	public BufferedImage setCorte(BufferedImage imagem) {
	    
	    BufferedImage aux = new BufferedImage(100, 120, imagem.getType());//cria um buffer auxiliar com o tamanho desejado  
        Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
        AffineTransform at = AffineTransform.getScaleInstance((double) 100 / imagem.getWidth(), (double) 120 / imagem.getHeight());//cria a transformacao  
        g.drawRenderedImage(imagem, at);//pinta e transforma a imagem real no auxiliar
		
        return aux; 
}
public BufferedImage setCorte120x200(BufferedImage imagem) {
	    
	    BufferedImage aux = new BufferedImage(200, 120, imagem.getType());//cria um buffer auxiliar com o tamanho desejado  
        Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
        AffineTransform at = AffineTransform.getScaleInstance((double) 200 / imagem.getWidth(), (double) 120 / imagem.getHeight());//cria a transformacao  
        g.drawRenderedImage(imagem, at);//pinta e transforma a imagem real no auxiliar
		
        return aux; 
}
public BufferedImage setCorteFundo(BufferedImage imagem,int l,int a) {
    
    BufferedImage aux = new BufferedImage(l, a, imagem.getType());//cria um buffer auxiliar com o tamanho desejado  
    Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao  
    AffineTransform at = AffineTransform.getScaleInstance((double) l / imagem.getWidth(), (double) a / imagem.getHeight());//cria a transformacao  
    g.drawRenderedImage(imagem, at);//pinta e transforma a imagem real no auxiliar
	
    return aux; 
}

}
