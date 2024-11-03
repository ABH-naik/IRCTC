package com.ashokIt.Springboot_InsuranceMini_Project.Util;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import com.ashokIt.Springboot_InsuranceMini_Project.Repository.CitizenPlanRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {




    public void generator(HttpServletResponse response, List<CitizenPlan> all, File file) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Paragraph p = new Paragraph("Citizen Plans Info");
        document.add(p);
        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.addCell("ID");
        pdfPTable.addCell("Citizen Name");
        pdfPTable.addCell("Plan Name");
        pdfPTable.addCell("Plan Status");
        document.add(pdfPTable);
        document.close();
    }
}
