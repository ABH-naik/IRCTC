package com.ashokIt.Springboot_InsuranceMini_Project.Runner;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import com.ashokIt.Springboot_InsuranceMini_Project.Repository.CitizenPlanRepository;
import com.ashokIt.Springboot_InsuranceMini_Project.Service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DataLoader implements CommandLineRunner {
    @Autowired
     CitizenPlanRepository repository;


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        CitizenPlan c1=new CitizenPlan();
        c1.setGender("Male");
        c1.setCitizenName("Harry");
        c1.setPlanName("Cash");
        c1.setPlanStatus("Approved");
        c1.setStartDate("");
        c1.setEndDate("");
        c1.setBenefitedAmount(2500L);


        CitizenPlan c2=new CitizenPlan();
        c2.setGender("Male");
        c2.setCitizenName("Peter");
        c2.setPlanName("Food");
        c2.setPlanStatus("Approved");
        c2.setStartDate("");
        c2.setEndDate("");
        c2.setBenefitedAmount(1555l);
        List<CitizenPlan> list = Arrays.asList(c1, c2);
        repository.saveAll(list);


    }


}
