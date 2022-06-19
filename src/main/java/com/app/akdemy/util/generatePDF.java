package com.app.akdemy.util;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.firebase.FirebaseInitialize;
import com.app.akdemy.service.DifusionService;
import com.google.cloud.firestore.DocumentSnapshot;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.ImageLoader;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.ImageProvider;
import com.lowagie.text.pdf.PdfWriter;

public class generatePDF {
    
    public void generateStudentCertificate(HttpServletResponse response, Estudiante estudiante, Map<String, Object> schoolData ) throws DocumentException, IOException {
        
        //Create document objects
        Document document = new Document(PageSize.LETTER, 70, 70, 50, 50);

        //Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        //Open document to modify it
        document.open();

        //School Logo
        Image logo = ImageLoader.getJpegImage(new URL((String) schoolData.get("logoURL")));
        logo.scalePercent(8);
        logo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(logo);

        //School Name =================================================
        Font fontSchoolTitle = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("\n" + ((String) schoolData.get("name")),fontSchoolTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);

        //Title=================================================
        fontSchoolTitle.setSize(14);
        Paragraph certificate = new Paragraph("\n\n\nCERTIFICA\n\n\n\n\n", fontSchoolTitle);
        certificate.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(certificate);


        //Body====================================================
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph paragraph = new Paragraph("Que " + estudiante.getNombres().toUpperCase()
        + " " + estudiante.getApellidos().toUpperCase() + " identificada(o) con " +
        estudiante.getTipoDocumento().getDescripcion() + " número " + estudiante.getDocumento() + ", se encuentra actualmente matriculado en el curso "
        + estudiante.getCursoActual().getNombre_Curso() +  ". Se entrega el presente certificado para acreditar que el estudiante pertenece al establecimiento educativo.", fontBody);
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph);


        //Expedition=================================================
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        Paragraph expedition = new Paragraph("\n\n\n\nSe expide a los " + cal.get(Calendar.DAY_OF_MONTH)  +
        " días del mes " + (cal.get(Calendar.MONTH) + 1) + " del año " + cal.get(Calendar.YEAR) 
        + " en la ciudad de " + schoolData.get("city")+ "." + "\n\n\n\n\n", fontBody);
        expedition.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(expedition);

        //Sign========================================================
        Image imgSign = ImageLoader.getJpegImage(new URL((String) schoolData.get("leaderSign")));
        imgSign.scalePercent(35);
        imgSign.setBorder(0);
        imgSign.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(imgSign);

        Font fontName = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        Paragraph name = new Paragraph((String) schoolData.get("leaderName"), fontName);
        name.setLeading(0);
        name.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(name);

        Paragraph role = new Paragraph((String) schoolData.get("leaderRole"), fontBody);
        role.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(role);

        document.close();

        
    }
}
