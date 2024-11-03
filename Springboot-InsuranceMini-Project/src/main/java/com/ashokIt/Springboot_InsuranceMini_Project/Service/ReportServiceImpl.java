package com.ashokIt.Springboot_InsuranceMini_Project.Service;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import com.ashokIt.Springboot_InsuranceMini_Project.Repository.CitizenPlanRepository;
import com.ashokIt.Springboot_InsuranceMini_Project.Request.SearchRequest;
import com.ashokIt.Springboot_InsuranceMini_Project.Util.EmailUtils;
import com.ashokIt.Springboot_InsuranceMini_Project.Util.ExcelGenerator;
import com.ashokIt.Springboot_InsuranceMini_Project.Util.PdfGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class ReportServiceImpl implements IReportService{
    @Autowired
    private CitizenPlanRepository repository;
    @Autowired
    private ExcelGenerator excelGenerator;
    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    PdfGenerator pdfGenerator;
    @Override
    public List<String> getAllPlanName() {
        return repository.getAllPlanName();
    }

    @Override
    public List<String> getAllPlanStatuses() {
        return repository.getAllPlanStatus();
    }
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CitizenPlan> citizenPlan;
    @Override
    public List<CitizenPlan> search(SearchRequest request) {

        CitizenPlan entity=new CitizenPlan();
        if(null!=request.getPlanName() && !"".equals(request.getPlanName()))//null check and empty check
            entity.setPlanName(request.getPlanName());
        if(null!=request.getPlanStatus()&& !"".equals(request.getPlanStatus()))
            entity.setPlanStatus(request.getPlanStatus());
        if(null!=request.getGender()&&!"".equals((request.getGender())))
            entity.setGender(request.getGender());
        if(null!=request.getStartDate()&&"".equals(((request.getStartDate())))) {
            String s=request.getStartDate();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM d, yyy", Locale.ENGLISH);
            LocalDate date= LocalDate.parse(s,formatter);
            entity.setStartDate(request.getStartDate());
        }
        if(null!=request.getEndDate()&&"".equals(((request.getEndDate())))) {
            String s=request.getEndDate();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM d, yyy", Locale.ENGLISH);
            LocalDate date= LocalDate.parse(s,formatter);
            entity.setStartDate(request.getEndDate());
        }

        BeanUtils.copyProperties(request,entity);
        return repository.findAll(Example.of(entity));
    }

    @Override
    public boolean excel(HttpServletResponse response) throws IOException {
        File f=new File("Plans.xls");//file name
        List<CitizenPlan> all=repository.findAll();//getting all the details
        excelGenerator.generate(response,all,f);

        //mail implementation
        String subject="Test Email Subject";
        String body="<h1>Test Email body</h1>";
        String to="ashokit.classes@gmail.com";
        emailUtils.sendEmail(subject,body,to);

        f.delete();


        return true;
    }

    @Override
    public boolean pdf(HttpServletResponse response) throws IOException, DocumentException {
        File f=new File("Plans.pdf");
        List<CitizenPlan> all=repository.findAll();
        pdfGenerator.generator(response,all,f);
        String subject="Test Email Subject";
        String body="<h1>Test Email body</h1>";
        String to="ashokit.classes@gmail.com";
        emailUtils.sendEmail(subject,body,to);

        f.delete();




        return true;
    }
}
