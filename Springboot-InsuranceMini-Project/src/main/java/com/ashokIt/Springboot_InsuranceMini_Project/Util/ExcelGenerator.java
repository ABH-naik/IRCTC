package com.ashokIt.Springboot_InsuranceMini_Project.Util;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import com.ashokIt.Springboot_InsuranceMini_Project.Repository.CitizenPlanRepository;
import com.ashokIt.Springboot_InsuranceMini_Project.Service.IReportService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Component
public class ExcelGenerator {




    public void generate(HttpServletResponse response, List<CitizenPlan> all, File file) throws IOException {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet("plans-data");
        Row headerRow=sheet.createRow(0);
        //This all are Headers

        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("CITIZEN NAME");
        headerRow.createCell(2).setCellValue("PLAN NAME");
        headerRow.createCell(3).setCellValue("PLAN STATUS");
        headerRow.createCell(4).setCellValue("PLAN START DATE");
        headerRow.createCell(5).setCellValue("PLAN END DATE");
        headerRow.createCell(6).setCellValue("BENEFIT AMT");
        //****//

        int dataRowIndex=1;
        for(CitizenPlan plan:all)
        {
            Row dataRow=sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(plan.getCitizenName());
            dataRow.createCell(1).setCellValue(plan.getId());
            dataRow.createCell(2).setCellValue(plan.getStartDate());
            dataRow.createCell(3).setCellValue(plan.getEndDate());
            dataRow.createCell(4).setCellValue(plan.getPlanName());
            if(null!=plan.getBenefitedAmount())
            {
                dataRow.createCell(5).setCellValue(plan.getBenefitedAmount());
            }
            else{
                dataRow.createCell(5).setCellValue("N/A");
            }
            dataRowIndex++;
        }
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();



    }

}
