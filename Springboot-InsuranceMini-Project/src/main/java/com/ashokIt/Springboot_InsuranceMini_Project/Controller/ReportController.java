package com.ashokIt.Springboot_InsuranceMini_Project.Controller;

import com.ashokIt.Springboot_InsuranceMini_Project.Request.SearchRequest;
import com.ashokIt.Springboot_InsuranceMini_Project.Service.IReportService;
import com.ashokIt.Springboot_InsuranceMini_Project.Service.ReportServiceImpl;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;


@Controller
public class ReportController {
    @Autowired
    private IReportService reportService;

    @GetMapping("/pdfexport")
    public void pdfExport(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","attachment;filename=plans.pdf");
        reportService.pdf(response);
    }





    @PostMapping("/search")
    public String handleSearch(@ModelAttribute("search") SearchRequest request, Model model)
    {
        init(model);
        model.addAttribute("citizenPlan",reportService.search(request));
        return "index";
    }
    @GetMapping("/excel")
    public void excelDocument(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition","attachment-filename=plans.xls");
        reportService.excel(response);
    }


    @GetMapping("/")
    public String indexPage(Model model)
    {
        SearchRequest request=new SearchRequest();
        init(model);
        model.addAttribute("search", request);
        return "index";
    }
    public void init(Model model)
    {
        model.addAttribute("planName",reportService.getAllPlanName());
        model.addAttribute("planStatuses",reportService.getAllPlanStatuses());

    }
}
