package VIEW;

import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import CONTROL.CTPaciente;
import MODEL.AgendaConsulta;
import MODEL.Medicamento;


public class ReceitaPdf {
	

	public static void gerarHistorico(AgendaConsulta consulta, ArrayList<Medicamento> listaMed)
			throws DocumentException, ParseException, IOException {

		Document doc = new Document();
		String arquivoPdf = "tmp//Receita-Paciente"+consulta.getId()+".pdf";

		PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
		doc.open();

		Paragraph p = new Paragraph("");
		p.setAlignment(0);
		doc.add(p);
		p = new Paragraph("\n\n\n\n");
		doc.add(p);

		// Titulo Cliente
		Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD, BaseColor.BLACK);
		Paragraph celCliente = new Paragraph(new Phrase(20F,"",fonte));
		celCliente.setAlignment(Element.ALIGN_CENTER);
		doc.add(celCliente);
		p = new Paragraph("\n\n");
		doc.add(p);

		// adata do pedido
		Date d = new Date();
		String hoje = DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);

		

		// Nome da Empresa CNPJ

		// Nome da cliente
		Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
		Paragraph tabCliente = new Paragraph("Paciente: " + CTPaciente.get(consulta.getIdPaciente()).getNome(),f1);

		p.setAlignment(0);
		doc.add(tabCliente);
		doc.add(p);
		

		// tabela
		Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
		Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
		
	    Medicamento med; 
        
	    int cont=0;
		for (Medicamento ped : listaMed) {
			
			if(cont==0) {
				med = listaMed.get(cont);
			}
			else {
				med = listaMed.get(cont-1);
			}
		
		    if(!ped.getVia().equals(med.getVia()) || cont==0) {
			Paragraph uso = new Paragraph(new Phrase(20F,""+ped.getVia(),fonte1));
			uso.setAlignment(Element.ALIGN_CENTER);
			doc.add(uso);
		    }
			
			
			Paragraph rem = new Paragraph(new Phrase(20F,(" \n"+ ped.getDesc() + " " + (int)ped.getmg()+"mg" + "                           " + ped.getQuant()
			+"\n"+ped.getModoUso()),fonte2));
			rem.setAlignment(Element.ALIGN_LEFT);
			doc.add(rem);
			p = new Paragraph("\n");
			doc.add(p);
			
			cont++;
			
	
		}
		
		// Titulo
				Paragraph data = new Paragraph("\n\n\n\n\n\n" + hoje);
				data.setAlignment(Element.ALIGN_RIGHT);
				doc.add(data);
				p = new Paragraph("\n");
				doc.add(p);

		doc.close();
		Desktop.getDesktop().open(new File("tmp//Receita-Paciente"+consulta.getId()+".pdf"));

	}
}
