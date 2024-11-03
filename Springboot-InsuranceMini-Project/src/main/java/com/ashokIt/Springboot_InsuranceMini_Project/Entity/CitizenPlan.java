package com.ashokIt.Springboot_InsuranceMini_Project.Entity;

import jakarta.persistence.*;
import lombok.Data;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Table(name="CITIZEN_PLANS_INFO")
@Data
@Entity
public class CitizenPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CITIZEN_ID")
    private Integer Id;
    private String CitizenName;
    private String PlanName;
    private String PlanStatus;
    private String Gender;
    private String StartDate;
    private String EndDate;
    private Long BenefitedAmount;
    private String DeniedReason;
    private String TerminatedReason;
    private LocalDate TerminatedEndDate;

}
