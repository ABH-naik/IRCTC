package com.ashokIt.Springboot_InsuranceMini_Project.Service;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import com.ashokIt.Springboot_InsuranceMini_Project.Request.SearchRequest;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface IReportService {
    public List<String> getAllPlanName();
    public List<String> getAllPlanStatuses();
    public List<CitizenPlan> search(SearchRequest request);
    public boolean excel(HttpServletResponse response) throws IOException;
    public boolean pdf(HttpServletResponse response) throws IOException, DocumentException;


}
