package MODEL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopiarArquivo {
	
	public static void fazer(String arq) throws IOException {
		  File origem = new File(arq);
		  File destino = new File("Documentos");
        

        @SuppressWarnings("resource")
		FileChannel sourceFileChannel = new FileInputStream(origem).getChannel();
        @SuppressWarnings("resource")
		FileChannel destinationFileChannel = new FileOutputStream(destino).getChannel();

        long size = sourceFileChannel.size();
        sourceFileChannel.transferTo(0, size, destinationFileChannel);

    }
	public static void main(String[] args) {
		try {
			CopiarArquivo.fazer("xml/CEP.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
