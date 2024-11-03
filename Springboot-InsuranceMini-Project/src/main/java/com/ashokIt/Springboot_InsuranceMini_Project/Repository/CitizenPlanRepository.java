package com.ashokIt.Springboot_InsuranceMini_Project.Repository;

import com.ashokIt.Springboot_InsuranceMini_Project.Entity.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan,Integer> {

    @Query("select distinct c.PlanName from CitizenPlan c")
    public List<String> getAllPlanName();

    @Query("select distinct c.PlanStatus from CitizenPlan c")
    public List<String> getAllPlanStatus();
}
