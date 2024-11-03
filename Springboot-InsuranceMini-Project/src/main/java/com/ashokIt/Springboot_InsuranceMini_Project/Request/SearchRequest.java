package com.ashokIt.Springboot_InsuranceMini_Project.Request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class SearchRequest {
    private String planName;
    private String planStatus;
    private String gender;
    private String startDate;
    private String endDate;
}
