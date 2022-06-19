package com.app.akdemy.util;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import com.app.akdemy.dto.CalificacionesEstDTO;
import com.app.akdemy.dto.EstudianteCalificacionDTO;
import com.app.akdemy.dto.MateriaCalificacionDTO;
import com.app.akdemy.entity.Estudiante;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.ImageLoader;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class generatePDF {

    

    private String manageNull(Object data){
        return data != null ? String.valueOf(data) : "No Disponible";
    }

    private String manageGrade(EstudianteCalificacionDTO calificacion){
        return calificacion.getCerrada() ? String.valueOf(calificacion.getNota()) + " DEFINITIVA" : String.valueOf(calificacion.getNota());
    }

    private boolean isNullList(List<EstudianteCalificacionDTO> list){

        for(EstudianteCalificacionDTO o : list){
            if(o != null){
                return false;
            }
        }

        return true;

    }

    private Calendar getCurrentDate(){
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

        return cal;
    }

    
    public void generateStudentCertificate(HttpServletResponse response, Estudiante estudiante, Map<String, Object> schoolData ) throws DocumentException, IOException {
        
        Calendar currentDateCalendar = getCurrentDate();

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
        Paragraph expedition = new Paragraph("\n\n\n\nSe expide a los " + currentDateCalendar.get(Calendar.DAY_OF_MONTH)  +
        " días del mes " + (currentDateCalendar.get(Calendar.MONTH) + 1) + " del año " + currentDateCalendar.get(Calendar.YEAR) 
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

    public void generateStudentGradesCertificate(HttpServletResponse response, CalificacionesEstDTO calificaciones, Map<String, Object> schoolData ) throws DocumentException, IOException {
        
        Calendar currentDateCalendar = getCurrentDate();

        //Create document objects
        Document document = new Document(PageSize.LETTER, 70, 70, 50, 50);

        //Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        //Open document to modify it
        document.open();

        //School Logo
        Image logo = ImageLoader.getJpegImage(new URL((String) schoolData.get("logoURL")));
        logo.scalePercent(5);
        logo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(logo);

        //School Name =================================================
        Font fontSchoolTitle = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph((String) schoolData.get("name"),fontSchoolTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);

        //Title=================================================
        fontSchoolTitle.setSize(14);
        Paragraph certificate = new Paragraph("\nReporte de Calificaciones Año " + currentDateCalendar.get(Calendar.YEAR) + "\n\n", fontSchoolTitle);
        certificate.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(certificate);


        //Student Info====================================================
        fontSchoolTitle.setSize(12);
        Paragraph studentInfo = new Paragraph(calificaciones.getNombreEstudiante().toUpperCase() 
        + "\nCurso: "+ calificaciones.getCurso()
        + "\nPromedio: " + manageNull(calificaciones.getPromedioGeneral()), fontSchoolTitle);
        document.add(studentInfo);


        //Body==============================================================
        List<MateriaCalificacionDTO> materias = calificaciones.getMaterias();
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 11);
        Font fontBodyBold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        for(MateriaCalificacionDTO materia: materias){
            //Data Materia
            Paragraph nameMateria = new Paragraph("\n" + materia.getNombre(), fontBodyBold);
            document.add(nameMateria);
            Paragraph dataMateria = new Paragraph(materia.getNombreProfesor() + "\nPromedio: " + manageNull(materia.getPromedio()), fontBody);
            document.add(dataMateria);

            //Grades
            List<EstudianteCalificacionDTO> calificacionesList = materia.getCalificaciones();

            if(!isNullList(calificacionesList)){
                Font fontBodyBoldTitleGrades = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
                Paragraph titleGrades = new Paragraph("\nCalificaciones",fontBodyBoldTitleGrades);
                titleGrades.setIndentationLeft(20);
                document.add(titleGrades);
                int index = 1;
                for(EstudianteCalificacionDTO calificacion : calificacionesList){
                    if(calificacion != null){
                        Paragraph p = new Paragraph("Periodo " + index + ": " + manageGrade(calificacion), fontBody);
                        p.setIndentationLeft(30);
                        document.add(p);
                    }
                    index += 1;
                }
            }else{
                Font fontBodyBoldalertGrades = FontFactory.getFont(FontFactory.HELVETICA, 11);
                Paragraph alertGrades = new Paragraph("\nNo se han informado calificaciones",fontBodyBoldalertGrades);
                alertGrades.setIndentationLeft(20);
                document.add(alertGrades);
            }
            
        }
        

        //Expedition=================================================
        Paragraph expedition = new Paragraph("\n\n\n\nSe expide a los " + currentDateCalendar.get(Calendar.DAY_OF_MONTH)  +
        " días del mes " + (currentDateCalendar.get(Calendar.MONTH) + 1) + " del año " + currentDateCalendar.get(Calendar.YEAR) 
        + " en la ciudad de " + schoolData.get("city")+ ".", fontBody);
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
