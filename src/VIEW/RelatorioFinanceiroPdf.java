package VIEW;

import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Random;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import CONTROL.CTEmitente;

public class RelatorioFinanceiroPdf {
	private static DecimalFormat convertMoeda = new DecimalFormat("#0.00");

	public static void gerarHistorico(ResultSet res,String inicio,String fim)throws DocumentException, ParseException, IOException {
		double total=0;
		int quant=0;
		Document boleto = new Document();
		 Random gerador = new Random();
		 int id=gerador.nextInt();
		String arquivoPdf = "tmp//Historico-Paciente-"+id+".pdf";

		PdfWriter.getInstance(boleto, new FileOutputStream(arquivoPdf));
		boleto.open();

		Paragraph p = new Paragraph("");
		p.setAlignment(0);
		boleto.add(p);

		Image logo = Image.getInstance(CTEmitente.getEmitente().getLogo());
		logo.scaleToFit(100, 100);
		logo.setAlignment(Element.PARAGRAPH);
		boleto.add(logo);

		Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
		Paragraph emp = new Paragraph(new Phrase(20F,"\n"+CTEmitente.getEmitente().getNomeFatasia(), fonte1));
		emp.setAlignment(Element.ALIGN_CENTER);
		boleto.add(emp);

		Font f = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
		Paragraph emp1 = new Paragraph(new Phrase(20F,
				CTEmitente.getEmitente().getRua() + ", " + CTEmitente.getEmitente().getNum() + " - "
						+ CTEmitente.getEmitente().getBairro() + " - " + CTEmitente.getEmitente().getCidade() + "-"
						+ CTEmitente.getEmitente().getUF() + "\n Celular:" + CTEmitente.getEmitente().getCel1()
						+ " - Fone Fixo:" + CTEmitente.getEmitente().getFone() + " - Email:"
						+ CTEmitente.getEmitente().getEmail() + "\n CNPJ:" + CTEmitente.getEmitente().getCNPJ()
						+ " - Inst.Estadual:" + CTEmitente.getEmitente().getInscEst(),
				f));
		emp1.setAlignment(Element.ALIGN_CENTER);
		boleto.add(emp1);
		p = new Paragraph("\n\n");
		boleto.add(p);

		// Titulo do documento
		Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
		Paragraph titulo1 = new Paragraph(new Phrase(20F, "Relatório das Consultas Finalizadas", fonte));
		titulo1.setAlignment(Element.ALIGN_CENTER);
		boleto.add(titulo1);
		p = new Paragraph("\n");
		boleto.add(p);

		// adata do pedido
		String hoje="até";
		
		

		
		Font f4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
		Font fontCliente = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
		
		if(fim.equals("")) {
			hoje="";
		}


		// tabela

		PdfPTable tabela = new PdfPTable(1);
		PdfPCell celula1 = new PdfPCell(new Phrase(20F,"Consultas Realizadas "+inicio+" "+hoje+" "+fim  ,fontCliente));
		celula1.setBackgroundColor(new BaseColor(139, 137, 137));
		celula1.setBorder(5);
		celula1.setHorizontalAlignment(1);
		tabela.addCell(celula1);
		boleto.add(tabela);

		PdfPTable tabela2 = new PdfPTable(6);
		float[] tams = { 0.10f, 0.25f, 0.15f, 0.40f, 0.40f, 0.30f };
		tabela2.setWidths(tams);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Cod",f4))).setHorizontalAlignment(1);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Data",f4))).setHorizontalAlignment(1);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Hora",f4))).setHorizontalAlignment(1);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Paciente",f4))).setHorizontalAlignment(1);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Procedimento",f4))).setHorizontalAlignment(1);
		tabela2.addCell(new PdfPCell(new Phrase(20F,"Valor",f4))).setHorizontalAlignment(1);
		tabela2.setHorizontalAlignment(1);
		boleto.add(tabela2);

		try {
			
			while(res.next()) {
				PdfPTable tabela3 = new PdfPTable(6);
				tabela3.setWidths(tams);
				tabela3.addCell(new PdfPCell(new Paragraph("" + res.getInt(1))));
				String h= DateFormat.getDateInstance(DateFormat.MEDIUM).format(res.getDate(2));
				tabela3.addCell(new PdfPCell(new Paragraph(h)));
				tabela3.addCell(new PdfPCell(new Paragraph("" + res.getString(3))));
				tabela3.addCell(new PdfPCell(new Paragraph("" + res.getString(4))));
				tabela3.addCell(new PdfPCell(new Paragraph("" + res.getString(5))));
				tabela3.addCell(new PdfPCell(new Paragraph("" + convertMoeda.format(res.getDouble(6)))));
				total+=res.getDouble(6);
				quant++;
				boleto.add(tabela3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(quant==0){
			Paragraph cliente = new Paragraph(new Phrase(20F, "\n* Não existem Consultas *\n", fontCliente));
			cliente.setAlignment(Element.ALIGN_CENTER);
			boleto.add(cliente);
			
		}
		
		// totais
				Paragraph cliente = new Paragraph(new Phrase(20F, "\nQUANTIDADE CONSULTAS: "+quant+"\nTOTAL GERAL: "+convertMoeda.format(total), fontCliente));
				cliente.setAlignment(Element.ALIGN_LEFT);
				boleto.add(cliente);

		boleto.close();
		Desktop.getDesktop().open(new File("tmp//Historico-Paciente-"+id+".pdf"));

	}
}
