package com.app.akdemy.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.api.Page;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class generatePDF {
    
    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        //Create document objects
        Document document = new Document(PageSize.LETTER);

        //Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        //Open document to modify it\
        document.open();

        //Fonts
        //Setting font and size to paragraph
        Font fontBody = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontBody.setSize(12);

        //Create body text
        Paragraph paragraph = new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa nulla, pellentesque porttitor vestibulum non, egestas vel augue. Donec fringilla aliquet hendrerit. Phasellus vulputate maximus augue, quis condimentum elit commodo vitae. Nulla auctor tincidunt elit elementum ornare. Fusce mollis leo at odio ullamcorper imperdiet. Nam cursus lectus leo, pretium fermentum justo convallis id. Mauris ac nibh ut justo lobortis vehicula"
        , fontBody);

        //Align the text
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);

        //Adding text to document
        document.add(paragraph);

        document.close();

        
    }
}
