package print;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


public class Prescription {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
         
        
        print("Tomar paracetamol de 8 em 8 horas.", "Dr. Gustavo");
        

	}
	
	public static void print(String message, String nameDoctor) {
		Document document = new Document(PageSize.A4, 72, 72, 72, 72);
		File file = new File("PDFTeste.pdf");
        
        try {
            PdfWriter.getInstance(document, new 
             FileOutputStream(file));
             
            document.open();
            Paragraph paragrafo = new Paragraph(Font.BOLD, "Senhor God, bless para que esse teste funcione!");
            paragrafo.setAlignment("center");
            paragrafo.setFont(new Font(34, 36, 00100, new Color(100, 100, 100)));//Não tá funcionando
            paragrafo.setSpacingAfter(100);
            document.add(paragrafo);
            
            paragrafo = new Paragraph(message);
            paragrafo.setAlignment("Justify");
            paragrafo.setIndentationLeft(3);
            paragrafo.setSpacingAfter(100);
            
            document.add(paragrafo);
            
            
            paragrafo = new Paragraph(nameDoctor);
            paragrafo.setAlignment("center");
            
            document.add(paragrafo);
            
            document.close();
            
            
            
            Desktop.getDesktop().print(file);
             
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
	}

}
